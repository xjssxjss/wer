package com.wer.controller.wx;

import com.wer.common.BusinessException;
import com.wer.common.GlobalConstant;
import com.wer.controller.base.BaseController;
import com.wer.enums.ResultCode;
import com.wer.service.sys.LogService;
import com.wer.service.wx.WxService;
import com.wer.service.sys.DictionaryEntriesService;
import com.wer.util.HttpClientUtil;
import com.wer.util.StringUtil;
import com.wer.util.WxUtil;
import org.apache.cxf.helpers.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 微信核心控制层
 * @package_name: com.wer.controller
 * @data: 2019-10-22 9:39
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping(value = "indexController")
public class WxController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private WxService wxService;

    @Autowired
    private LogService logService;

    @Autowired
    private DictionaryEntriesService dictionaryEntriesService;

    @RequestMapping(value = "tabbar")
    public String tabbar(){
        //return GlobalConstant.APEC_ON_LINE;
        return "tabbar/tabbar";
    }

    @RequestMapping(value = "index")
    public String index(){
//        dictionaryEntriesService.queryDictionaryEntriesByCode();
        //return GlobalConstant.APEC_ON_LINE;
        return "index/index";
    }

    @RequestMapping(value = "apecSelect")
    public String apecSelect(){
        //return GlobalConstant.APEC_ON_LINE;
        return "apec/apec_select";
    }

    @RequestMapping(value = "apecOnLine")
    public String apecOnLine(){
        //return GlobalConstant.APEC_ON_LINE;
        return "apec/apec_on_line";
    }

    @RequestMapping(value = "swiper")
    public String swiper(){
        return "common/swiper";
    }

    /**
     * 获取护照签证信息
     * @param model
     * @return
     */
    @RequestMapping(value = "visaClaim")
    public String visaClaim(@RequestParam(name = "countryName")
                            String countryName,
                            Model model){
        logger.info("countryName::"+countryName);

        Map<String,Object> map = GlobalConstant.visaDataMap;

        model.addAttribute("visaInfo",map.get(countryName));

        if(null == map.get(countryName)){
            throw new BusinessException(ResultCode.getResult(408));
        }

        //return GlobalConstant.VISA_CLAIM;
        return "tabbar/tabbar";
    }

    @RequestMapping(value = "signIn")
    public void signIn(HttpServletRequest request,
                       HttpServletResponse response){
        /**
         * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
         timestamp	时间戳
         nonce	随机数
         echostr	随机字符串
         */
        String signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if(!StringUtil.isEmpty(signature) && !StringUtil.isEmpty(timestamp) && !StringUtil.isEmpty(nonce) && !StringUtil.isEmpty(echostr)){
            //接入微信校验
            String echoStr = WxUtil.wxSignInCheck(timestamp,nonce,signature,echostr);
            logger.info("接入结果:"+echoStr);
            //表示接入成功
            if(!"".equals(echoStr)){
                try {
                    //写数据
                    PrintWriter writer = response.getWriter();
                    writer.write(echoStr);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                //从请求中读取整个post数据
                InputStream inputStream = request.getInputStream();
                String postData = IOUtils.toString(inputStream, "UTF-8");
                System.out.println(postData);

                //获取解密之后的字符串
                String decryMsg = WxUtil.decryMsg(timestamp,nonce,signature,postData);
                if(null != decryMsg){
                    //对消息进行处理
                    String writer = wxService.getWxMessage(decryMsg,request,response);

                    //对返回的结果进行加密
                    String respData = WxUtil.encryMsg(nonce,writer);
                    //返回给客户端
                    PrintWriter out = response.getWriter();
                    out.print(respData);
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 企业微信同意协议并授权跳转界面
     * @return
     */
    @GetMapping(value = "agreement")
    public String agreement(){
        return GlobalConstant.AGREEMENT;
    }

    /**
     * 授权访问回调方法
     * @param code
     * @param model
     * @return
     */
    @GetMapping(value = "authorizeResponse")
    public String authorizeResult(@RequestParam(value = "code") String code,
                                  Model model,
                                  HttpServletRequest request){
        Map<String,Object> resultMap = wxService.authorizeResult(code,request);
        //成功
        if((Boolean)resultMap.get("success")){
            return GlobalConstant.AGREEMENT_SUCCESS;
        } else {
            //失败原因
            model.addAttribute("message",resultMap.get("message"));
            return GlobalConstant.AGREEMENT_ERROR;
        }
    }

    /**
     * jd-sdk 获取config信息
     * @param url
     * @return
     */
    @GetMapping(value = "getConfigInfo")
    @ResponseBody
    public String getConfigInfo(@RequestParam(value = "url") String url) {
        return wxService.getConfigInfo(url);
    }

    /**
     * 记录用户截屏相关事件
     * @param title
     * @param url
     * @param request
     */
    @GetMapping(value = "onUserCaptureScreen")
    @ResponseBody
    public void onUserCaptureScreen(@RequestParam(value = "title") String title,
                                    @RequestParam(value = "url") String url,
                                    HttpServletRequest request){
        logService.onUserCaptureScreen(title,url,request);
    }
}
