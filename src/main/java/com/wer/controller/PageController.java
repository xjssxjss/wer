package com.wer.controller;

import com.wer.common.GlobalConstant;
import com.wer.service.WxService;
import com.wer.service.sys.DictionaryEntriesService;
import com.wer.util.StringUtil;
import com.wer.util.WxUtil;
import org.apache.cxf.helpers.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @description: TODO
 * @package_name: com.wer.controller
 * @data: 2019-10-22 9:39
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping(value = "indexController")
public class PageController {

    private static Logger logger = LoggerFactory.getLogger(PageController.class);
    @Autowired
    private WxService wxService;

    @Autowired
    private DictionaryEntriesService dictionaryEntriesService;

    @RequestMapping(value = "index")
    public String index(){
        dictionaryEntriesService.queryDictionaryEntriesByCode();
        return GlobalConstant.GRID;
    }

    /**
     * 获取护照签证信息
     * @param model
     * @return
     */
    @RequestMapping(value = "visaClaim")
    public String visaClaim(@RequestParam(name = "countryName",required = true)
                            String countryName,
                            Model model){
        logger.info("countryName::"+countryName);
        return GlobalConstant.VISA_CLAIM;
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
}
