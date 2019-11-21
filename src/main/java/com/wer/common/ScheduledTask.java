package com.wer.common;

import com.wer.service.wx.WxService;
import com.wer.util.HttpClientUtil;
import com.wer.util.PropertiesListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 任务调度类
 * @package_name: com.wer.common
 * @data: 2019-10-22 14:33
 * @author: Sean
 * @version: V1.0
 */
@Component
public class ScheduledTask extends BaseObject{
    private static Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    //@Scheduled(cron = "*/10 * * * * ?")
    public void getWebServiceInfo(){
        logger.info("获取WebServiceddddInfo文件>>>>>>>>>>>>>>>>"+ resourceMap.get("apec_data_econciliation_url"));
    }

    /**
     * 获取部门列表
     */
    //@Scheduled(cron = "*/20 * * * * ?")
    public void getPartList(){
        logger.info("获取token>>>>>>>>>>>>>>>>>>>>"+ WxService.getAccessToken());
        logger.info("get_part_list_url"+PropertiesListenerConfig.getProperty("get_part_list_url"));
        String result = HttpClientUtil.doGet(PropertiesListenerConfig.getProperty("get_part_list_url").replace("ACCESS_TOKEN",WxService.getAccessToken()));
    }

    /**
     * 获取部门列表
     */
    //@Scheduled(cron = "*/20 * * * * ?")
    public void getJoinQrCode(){
        logger.info("获取QRCODE::"+ WxService.getAccessToken());
        //String result = HttpClientUtil.doGet(PropertiesListenerConfig.getProperty("get_join_qr_code").replace("ACCESS_TOKEN",WxService.getAccessToken()).replace("SIZE_TYPE","2"));
        //logger.info("获取QRCODE>>>>>>>>>>>>>>>>>:"+result);
    }

    /**
     * 获取部门成员
     */
    //@Scheduled(cron = "*/20 * * * * ?")
    public void getUsersByGroup(){
        logger.info("获取部门成员>>>>>>>>>>>>>"+ WxService.getAccessToken());
        //String result = HttpClientUtil.doGet(PropertiesListenerConfig.getProperty("get_detail_user_by_part").replace("ACCESS_TOKEN",WxService.getAccessToken()).replace("DEPARTMENT_ID","1").replace("FETCH_CHILD","1"));
        //logger.info("获取部门成员>>>>>>>>>>>>>>>>>:"+result);
    }
}
