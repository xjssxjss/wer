package com.wer.util;

import com.alibaba.fastjson.JSONObject;
import com.wer.common.BaseObject;
import com.wer.service.wx.WxService;
import com.wer.util.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 对接口进行加密
     * @param noncestr
     * @param jsapi_ticket
     * @param timestamp
     * @param url
     * @return
     */
    public static String getsig(String noncestr,String jsapi_ticket,String timestamp,String url){
        String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket,
                "timestamp=" + timestamp, "nonceStr=" + noncestr, "url=" + url };
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
                .concat("&"+paramArr[3]);
        String gensignature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            gensignature = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将 sha1 加密后的字符串与 signature 进行对比
        if (gensignature != null) {
            return gensignature;// 返回signature
        } else {
            return "false";
        }
    }

    public static String getJsSdkSign(String noncestr,String tsapiTicket,String timestamp,String url){
        String content="jsapi_ticket="+tsapiTicket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
        String ciphertext=getSha1(content);
        return ciphertext;
    }

    public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
