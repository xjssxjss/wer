package com.wer.util;

import com.alibaba.fastjson.JSONObject;
import com.wer.common.BaseObject;
import com.wer.service.WxService;
import com.wer.util.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sean on 2019-8-3.
 */
public class WxUtil extends BaseObject{
    
    private static Logger logger = LoggerFactory.getLogger(WxUtil.class);

    private static String token = null;
    private static String aesKey = null;
    private static String corpId = null;

    static{
        //获取token
        token = PropertiesListenerConfig.getProperty("sign_in_token");
        //获取aesKey
        aesKey = PropertiesListenerConfig.getProperty("aes_key");
        //获取corpId 企业id
        corpId = PropertiesListenerConfig.getProperty("corpid");
    }
    /**
     * 微信接入
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static String wxSignInCheck(String timestamp,String nonce,String signature,String echoStr){
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, corpId);
            //验证接入信息
            echoStr = wxcpt.VerifyURL(signature, timestamp,
                    nonce, echoStr);
            System.out.println("verifyurl echostr: " + echoStr);
            // 验证URL成功，将sEchoStr返回
            return echoStr;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 对消息进行解密
     * @param timestamp
     * @param nonce
     * @param signature
     * @param postData
     * @return
     */
    public static String decryMsg(String timestamp,String nonce,String signature,String postData){
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, corpId);
            //对消息进行解密
            String sMsg = wxcpt.DecryptMsg(signature, timestamp, nonce, postData);
            return sMsg;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对需要返回的消息进行加密
     * @param nonce
     * @param respData
     * @return
     */
    public static String encryMsg(String nonce,String respData){
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, corpId);
            //对消息进行解密
            String sMsg = wxcpt.EncryptMsg(respData, System.currentTimeMillis()/1000+"", nonce);
            return sMsg;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param src
     * @return
     */
    private static String sha1Digest(String src){
        StringBuilder sb = new StringBuilder();
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //进行加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

            //处理加密结果
            for (byte b : digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    public static String getUserInfoByOpenId(String openId){
        StringBuilder builder = new StringBuilder();
        String token = WxService.getAccessToken();
        //获取用户信息基本地址
        String getUserInfoUrl = PropertiesListenerConfig.getProperty("wx_get_user_info_url");
        getUserInfoUrl = getUserInfoUrl.replace("ACCESS_TOKEN",token).replace("OPENID",openId);

        String result = HttpClientUtil.doGet(getUserInfoUrl);
        JSONObject jsonObject = JSONObject.parseObject(result);
        boolean sex = jsonObject.getBooleanValue("sex");
        builder.append("地址："+jsonObject.getString("country")+"-"+jsonObject.getString("province")+"-"+jsonObject.getString("city")+"\n");

        builder.append("性别："+(sex?"男":"女") +"\n");
        builder.append("头像："+jsonObject.getString("headimgurl")+"\n");
        builder.append("备注:"+jsonObject.getString("remark")+"\n");

        logger.info("我的信息:"+result);
        return builder.toString();
    }

    /**
     * 获取sign签名
     * @param timestamp
     * @return
     */
    public static String getSign(String timestamp){
        //获取应用key
        String appKey = resourceMap.get("app_key");
        //获取应用secret
        String appSecret = resourceMap.get("app_secret");
        //获取年月日时分秒
        return MD5Util.getMd5Value(appKey+appSecret+timestamp).substring(8,24).toUpperCase();
    }
}
