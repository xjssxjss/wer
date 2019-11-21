package com.wer.service.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.wer.common.BusinessException;
import com.wer.common.GlobalConstant;
import com.wer.entity.AccessToken;
import com.wer.entity.base.BaseMessage;
import com.wer.entity.msg.NewsMessage;
import com.wer.entity.msg.TextMessage;
import com.wer.entity.msg.child.Article;
import com.wer.entity.sys.Attachment;
import com.wer.entity.visa.VisaArticle;
import com.wer.enums.ResultCode;
import com.wer.service.base.BaseService;
import com.wer.service.visa.VisaClaimService;
import com.wer.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

/**
 * @description: 微信服务类
 * @package_name: com.wer.service
 * @data: 2019-10-22 15:22
 * @author: Sean
 * @version: V1.0
 */
@Service
public class WxService extends BaseService{

    private static AccessToken accessToken;

    @Autowired
    private VisaClaimService visaClaimService;

    /**
     * 解析微信公众号发来的消息
     *
     * @param is
     * @return
     */
    private Map<String, String> parseRequest(InputStream is) {
        Map<String, String> parseResultMap = new HashMap<>();
        //声明读取xml的对象
        SAXReader reader = new SAXReader();
        try {
            //1、根据saxReader读取流对象
            Document document = reader.read(is);
            //2、获取根节点
            Element rootElement = document.getRootElement();
            //3、获取根节点的所有子节点
            List<Element> listElement = rootElement.elements();
            //4、循环遍历节点对象
            for (Element element : listElement) {
                parseResultMap.put(element.getName(), element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return parseResultMap;
    }

    /**
     * 把java对象转化为xml
     *
     * @return
     */
    private static String beanToXml(BaseMessage baseMessage) {
        //实例化XStream对象
        XStream stream = new XStream();
        //设置需要转化为xml的对象
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(NewsMessage.class);

        return stream.toXML(baseMessage);
    }

    /**
     * 处理所有事件和消息的回复
     *
     * @param requestMap
     * @return
     */
    private String getResponseXml(Map<String, String> requestMap) {
        BaseMessage baseMessage = null;
        //获取微信企业号发送的消息类型
        String msgType = requestMap.get(GlobalConstant.WX_MSG_TYPE);

        switch (msgType) {
            case "text":
                baseMessage = dealTextMessage(requestMap);
                break;

            case "image":
                //baseMessage = dealImageMessage(requestMap);
                break;

            case "voice":
                //baseMessage = dealVoiceMessage(requestMap);
                break;

            case "music":
                //baseMessage = dealMusicMessage(requestMap);
                break;

            case "video":
                break;

            case "link":
                break;
            case "location":
                //baseMessage = dealLocationMessage(requestMap);
                break;

            case "event":
                //处理事件
                baseMessage = dealEventMessage(requestMap);
                break;
        }
        return beanToXml(baseMessage);
    }

    /**
     * 处理用户事件
     *
     * @param requestMap
     * @return
     */
    private BaseMessage dealEventMessage(Map<String, String> requestMap) {
        BaseMessage baseMessage = null;
        //获取事件类型
        String event = requestMap.get("Event");
        //visa_query_click

        switch (event) {
            case "click":
                String eventKey = requestMap.get("EventKey");
                //获取eventKey
                if(GlobalConstant.MSG_QUERY_CLICK.equals(eventKey)){
                    //公告查询点击
                    baseMessage = new TextMessage(requestMap,"请回复“g公告标题”,例如“g领馆”,注意:根据输入的条件,只可查询最近八条公告信息,需要查询更多请输入详细公告标题名称或者到专管员平台进行查看");
                } else if(GlobalConstant.VISA_QUERY_CLICK.equals(eventKey)){
                    //护照签证查询点击
                    baseMessage = new TextMessage(requestMap,"请回复“v国家名称”,例如“v美国”");
                }
                /*String mediaId = "";
                String result = HttpClientUtil.doGet(PropertiesListenerConfig.getProperty("get_join_qr_code").replace("ACCESS_TOKEN", WxService.getAccessToken()).replace("SIZE_TYPE", "2"));

                logger.info("获取加入企业微信二维码结果:{}" + result);
                //把返回的结果转化为对象
                JSONObject object = (JSONObject) JSON.parse(result);

                //说明访问成功
                if (0 == (Integer) object.get("errcode")) {
                    //获取二维码链接
                    String joinQrcode = (String) object.get("join_qrcode");
                    baseMessage = new TextMessage(requestMap, joinQrcode);
                }*/
                //Image image = new Image("");
                // baseMessage = new ImageMessage(requestMap,image);
                break;
            case "view":

                break;
        }
        return baseMessage;
    }

    /**
     * 回复客户端发送的文本消息
     *
     * @param requestMap
     * @return
     */
    private BaseMessage dealTextMessage(Map<String, String> requestMap) {

        String content = requestMap.get("Content");

        String subContent = content.substring(1,content.length());

        List<Article> articleList = new ArrayList<>();
        Article article = null;
        BaseMessage baseMessage = null;
        String[] visaCountrys = resourceMap.get("visa_countrys").split(",");
        String redirectUrl = null;
        if(!GlobalConstant.userList.contains(requestMap.get("FromUserName"))){
            baseMessage = new TextMessage(requestMap,"您好，请先进行<a href='http://192.168.0.115:9999/wer/indexController/agreement'>认证</a>");
        } else {
            //说明查询的是国家护照签证信息
            if(!StringUtil.isEmpty(content) && content.startsWith("v")){
                //获取文件服务器server uri
                String imageServerUrl = resourceMap.get("image_server_url");
                for (String visaCountry : visaCountrys) {
                    if (visaCountry.equals(subContent)) {
                        //通过公司名称获取VisaArticle对象信息
                        Map<String,Object> visaArticleObject = visaClaimService.queryVisaArticleByCountryName(subContent);

                        //如果访问成功
                        if((Boolean)visaArticleObject.get("success")){
                            Map<String,Object> paramMap = new HashMap<>();
                            //获取VisaArticle对象
                            VisaArticle visaArticle = (VisaArticle)visaArticleObject.get("data");
                            paramMap.put("slipId",visaArticle.getId());
                            String url = visaArticle.getUrl()+content.substring(1,content.length());
                            //根据slip_id查询附件信息
                            Attachment attachment = attachmentMapper.queryByParams(paramMap).get(0);
                            //拼接图片路径
                            String picUrl = imageServerUrl + attachment.getFilePath() + attachment.getFileSaveName();
                            article = new Article(
                                    "签证要求 - "+visaArticle.getTitle()+"("+DateUtil.parseDateToStr(visaArticle.getEffectiveDate(),DateUtil.DATE_FORMAT_YYYY_MM_DD)+")",
                                    null,
//                                    "https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=3483030207,3924941481&fm=202&mola=new&crop=v1",
                                    picUrl,
                                    url
                            );
                        }
                        articleList.add(article);
                        baseMessage = new NewsMessage(requestMap, articleList);
                        break;
                    }
                }
            } else if(!StringUtil.isEmpty(content) && content.startsWith("g")){
                //查询的是公告信息
                try {
                    JSONArray array = WebServiceClientUtil.callWebService("queryMessageList",subContent);

                    //循环数组对象
                    for(int i = 0 ; i< array.size() ; i ++){
                        String sign = WxUtil.getSign(DateUtil.getCurrentTimeStamp());
                        //把返回结果转化成JSONObject对象
                        if(i<=7){
                            JSONObject object = (JSONObject) array.get(i);

                            new Timestamp(Long.valueOf(object.getString("dtPublish")));
                            System.out.println(new Timestamp(Long.valueOf(object.getString("dtPublish"))));
                            //获取发布日期
                            String dtPublish = DateUtil.parseTimestampToStr(new Timestamp(Long.valueOf(object.getString("dtPublish"))),DateUtil.DATE_FORMAT_YYYY_MM_DD);

                            //拼接点击访问链接
                            redirectUrl =
                                    resourceMap.get("server_url")+
                                            resourceMap.get("redirect_to_msg_detail").replace("MSG_ID",
                                                    (String)object.get("stMessageId")).replace("SIGN",sign).
                                                    replace("TIMESTAMP",DateUtil.getCurrentTimeStamp());
                            System.out.println(redirectUrl);
                            article = new Article(
                                    dtPublish+(String)object.get("stTitle"),
                                    null,
                                    "https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=3483030207,3924941481&fm=202&mola=new&crop=v1",
                                    redirectUrl);
                            articleList.add(article);
                        }
                    }
                } catch (Exception e){
                    baseMessage = new TextMessage(requestMap, ResultCode.getResult(405));
                }

                //articleList不为空
                if(null != articleList && articleList.size()>0){
                    baseMessage = new NewsMessage(requestMap,articleList);
                }
            }
        }

        if(null == baseMessage){
            baseMessage = new TextMessage(requestMap, "您输入的信息未匹配,请重新输入");
        }
        return baseMessage;
    }

    /**
     * 把请求解密之后的xml转化为Map对象
     *
     * @param xml
     * @return
     */
    private Map<String, String> parseXmlToMap(String xml) {
        Map<String, String> xmlMap = new HashMap();
        Document document = null;
        try {
            //1、解析xml字符串
            document = DocumentHelper.parseText(xml);
            //2、获取根节点
            Element rootElement = document.getRootElement();
            //3、获取根节点的所有子节点
            List<Element> listElement = rootElement.elements();
            //4、循环遍历节点对象
            for (Element element : listElement) {
                xmlMap.put(element.getName(), element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return xmlMap;
    }

    /**
     * 解析微信发过来的消息
     *
     * @param request
     * @param response
     * @return
     */
    public String getWxMessage(String decryMsg, HttpServletRequest request, HttpServletResponse response) {
        //封装回复的数据包
        String respXml = null;
        try {
            request.setCharacterEncoding("utf8");
            response.setCharacterEncoding("utf8");

            //把xml转化成map表
            Map<String, String> parseXmlMap = parseXmlToMap(decryMsg);
            System.out.println("解析Xml:" + parseXmlMap.toString());

            respXml = getResponseXml(parseXmlMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respXml;
    }

    /**
     * 根据api地址获取token
     */
    private static void getToken() {
        String result = "";
        String getAccessTokenUrl = null;

        String corpid = PropertiesListenerConfig.getProperty("corpid");
        String secret = PropertiesListenerConfig.getProperty("Secret");

        //获取accessToken的url
        getAccessTokenUrl = PropertiesListenerConfig.getProperty("get_qy_token_url");

        String url = getAccessTokenUrl.replace("ID", corpid).replace("SECRET", secret);
        //获取token
        result = HttpClientUtil.doGet(url);

        //对返回的结果转为JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
        String token = (String) jsonObject.get("access_token");
        String expiresIn = jsonObject.get("expires_in").toString();
        //基础accessToken对象
        accessToken = new AccessToken(token, expiresIn);
    }

    /**
     * 向外暴露的方法判断是否已过期
     *
     * @return
     */
    public static String getAccessToken() {
        if (null == accessToken || accessToken.isExpired()) {
            //获取accessToken
            getToken();
        }
        return accessToken.getAccessToken();
    }

    /**
     * 通过用户授权code获取用户信息
     * @param code
     * @return
     */
    public Map<String,Object> authorizeResult(String code){
        //获取用户信息url
        String url = resourceMap.get("get_user_info_url");

        //获取当前生效的token
        String token = getAccessToken();
        try{
            //判断code是否为空
            if(!StringUtil.isEmpty(code)){
                //替换获取用户信息url
                String getVisitInfoUrl = url.replace("ACCESS_TOKEN",token).replace("CODE",code);
                System.out.println(getVisitInfoUrl);

                //发起获取用户信息请求
                String visitInfo = HttpClientUtil.doGet(getVisitInfoUrl);
                //把返回的结果转化为JSONObject对象
                JSONObject object = JSONObject.parseObject(visitInfo);

                //判断访问微信api是否成功
                if(getWxApiResult(object)){
                    String userId = (String)object.get("UserId");
                    //说明已经认证过了
                    if(GlobalConstant.userList.contains(userId)){
                        success = false;
                        message = GlobalConstant.ALREADTY_AUTHORIZE;
                    } else {
                        success = true;
                        message = GlobalConstant.SUCCESS_MESSAGE;
                        data = userId;
                        //把userId加入到集合中
                        GlobalConstant.userList.add(userId);
                    }
                }
            }
        } catch (Exception e){
            success = false;
            message = e.getMessage();
        }
        return result();
    }
}