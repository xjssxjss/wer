package com.wer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wer.common.BusinessException;
import com.wer.enums.ResultCode;
import com.wer.service.base.BaseService;
import com.wer.util.DateUtil;
import com.wer.util.StringUtil;
import com.wer.util.WebServiceClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 公告服务类
 * @package_name: com.wer.service
 * @data: 2019-10-22 15:15
 * @author: Sean
 * @version: V1.0
 */
@Service
public class MessageService extends BaseService{

    private static Logger logger = LoggerFactory.getLogger(MessageService.class);

    /**
     * 根据公告Id查询公告详情
     * @param msgId
     * @return
     */
    public Map<String,Object> queryMessageByMsgId(String msgId){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //实例化WebServicClientUtil对象
            WebServiceClientUtil webServiceClientUtil = WebServiceClientUtil.getInstance();
            //发起webservice请求
            Object[] objects = webServiceClientUtil.callService("queryMessageByMsgId", msgId);
            String jsonStr = objects[0].toString();

            //判断你是否匹配到message信息
            if(!StringUtil.isEmpty(jsonStr)){
                JSONObject object = (JSONObject) JSON.parse(jsonStr);
                resultMap.put("stTitle",object.get("stTitle"));
                resultMap.put("clText",object.get("clText"));
                resultMap.put("dtPublish", DateUtil.parseTimestampToStr(new Timestamp(Long.valueOf(object.get("dtPublish").toString())),DateUtil.DATE_FORMAT_YYYY_MM_DD));
                resultMap.put("dtClose", DateUtil.parseTimestampToStr(new Timestamp(Long.valueOf(object.get("dtClose").toString())),DateUtil.DATE_FORMAT_YYYY_MM_DD));
            }
        } catch (Exception e){
            throw new BusinessException(ResultCode.getResult(405));
        }
        return resultMap;
    }
}