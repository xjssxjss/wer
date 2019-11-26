package com.wer.service.sys;

import com.wer.entity.wx.WxCaptureScreenLog;
import com.wer.service.base.BaseService;
import com.wer.util.WebToolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * @description: TODO
 * @package_name: com.wer.service.sys
 * @data: 2019-11-26 13:57
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class LogService extends BaseService<WxCaptureScreenLog> {

    /**
     * 保存用户截屏事件
     * @param title
     * @param url
     * @param request
     */
    public void onUserCaptureScreen(String title,
                                    String url,
                                    HttpServletRequest request){
        System.out.println("当前截屏人员:"+request.getSession().getAttribute("userId"));
        //实例化对象
        WxCaptureScreenLog wxCaptureScreenLog = new WxCaptureScreenLog();
        wxCaptureScreenLog.setCsTitle(title);
        wxCaptureScreenLog.setCsUrl(URLDecoderString(url));
        wxCaptureScreenLog.setCsAddrIp(WebToolUtils.getIpAddress(request));
        wxCaptureScreenLog.setCsCaptureTime(new Date());
        wxCaptureScreenLog.setCsId(UUID.randomUUID().toString());
        wxCaptureScreenLog.setCsValid(true);

        try {
            insert(wxCaptureScreenLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
