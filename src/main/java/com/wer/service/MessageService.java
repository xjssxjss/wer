package com.wer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wer.service.base.BaseService;
import com.wer.util.StringUtil;
import com.wer.util.WebServiceClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            //发起webservice请求
            Object[] objects = WebServiceClientUtil.getInstance().callService("queryMessageByMsgId", msgId);
            String jsonStr = objects[0].toString();

            //判断你是否匹配到message信息
            if(!StringUtil.isEmpty(jsonStr)){
                JSONObject object = (JSONObject) JSON.parse(jsonStr);
                resultMap.put("stTitle",object.get("stTitle"));
                resultMap.put("clText",object.get("clText"));
            }
        } catch (Exception e){
            logger.info("发生异常:"+e.getMessage());
        }
        return resultMap;
    }
}