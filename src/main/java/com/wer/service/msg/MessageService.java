package com.wer.service.msg;

import com.alibaba.fastjson.JSON;
import com.wer.common.GlobalConstant;
import com.wer.dto.msg.MessageDto;
import com.wer.entity.msg.Message;
import com.wer.entity.sys.Attachment;
import com.wer.entity.sys.DictionaryEntries;
import com.wer.service.base.BaseService;
import com.wer.service.sys.SysSequenceCounterService;
import com.wer.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * @description: 公告信息服务类
 * @package_name: com.wer.service.msg
 * @data: 2019-12-2 10:24
 * @author: Sean
 * @version: V1.0
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService extends BaseService<Message>{

    @Autowired
    private SysSequenceCounterService sequenceCounterService;

    /**
     * 新增公告信息
     * @param jsonParam
     */
    public Map<String,Object>

    saveMessage(String jsonParam){

        //拿到message对象
        Message msg = JSON.parseObject(jsonParam, Message.class);

        //验证实体信息是否合法
        Set<ConstraintViolation<Message>> validate = validator.validate(msg);

        if(validate.size()<=0){
            //判断两个日期是否符合要求
            if(1 == msg.getMsgStartDate().compareTo(msg.getMsgEndDate())){
                //说明日期不对
                success = false;
                message = GlobalConstant.FORM_CHAKED_INVALID;
                data = "<span style='color:red'>生效日期必须在失效日期之前</span>";
            } else {
                //设置主键Id
                msg.setMsgId(sequenceCounterService.generateCode(Message.class.getName(),GlobalConstant.MESSAGE_ID_PREFIX));
                msg.setMsgIsValid(true);
                msg.setMsgPubTime(new Date());
                msg.setMsgIsDelete(false);

                try {
                    insert(msg);
                    success = true;
                } catch (Exception e) {
                    success = false;
                    message = e.getMessage();
                }
            }
        } else {
            success = false;
            message = GlobalConstant.FORM_CHAKED_INVALID;
            data = checkEntityResult(validate);
        }
        return result();
    }

    /**
     * 查询前五条最新公告信息
     * @return
     */
    public Map<String,Object> queryTop5MessageInfo(){
        //查询前五条公告信息
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("currentPage",1);
        pageMap.put("pageSize",3);
        pageMap.put("orderBy","  msg_pub_time desc");

        try {
            //开始分页
            startPage(pageMap);
            List<Message> messageList = queryByParams(new HashMap());

            StringBuffer contentHtml = new StringBuffer("");
            if(null != messageList && messageList.size()>0){
                contentHtml.append("<ul class=\"news_li\">");

                for (Message msg : messageList) {
                    contentHtml.append("<li><div class=\"t_msg\">66</div>");

                    String startDate = DateUtil.parseDateToStr(msg.getMsgStartDate(),DateUtil.DATE_TIME_FORMAT_YYYY年MM月DD日);

                    contentHtml.append("<a href="+resourceMap.get("server_url")+GlobalConstant.APP_PREFIX+"/messageController/queryMessageByMesssgeId?msgId="+msg.getMsgId()+">"+msg.getMsgTitle()+"-"+startDate+"</a></li>");
                }
                contentHtml.append("</ul>");
                contentHtml.append("<ul class=\"swap\"></ul>");
            }
            success = true;
            data = contentHtml.toString();
            message = GlobalConstant.SUCCESS_MESSAGE;
        } catch (Exception e) {
            success = false;
            data = e.getMessage();
            message = e.getMessage();
        }
        return result();
    }

    /**
     * 根据公告信息Id查询公告信息
     * @param msgId
     * @return
     */
    public Map<String,Object> queryMessageByMsgId(String msgId){
        Map<String,Object> param = new HashMap<>();
        param.put("msgId",msgId);

        try {
            List<Message> messages = queryByParams(param);

            if(null != messages && messages.size()>0){
                Message msg = queryByParams(param).get(0);

                MessageDto msgDto = new MessageDto(msg.getMsgId(),msg.getMsgTitle(),msg.getMsgStartDate(),msg.getMsgEndDate(),msg.getMsgContent());
                data = msgDto;
                success = true;
                message = GlobalConstant.SUCCESS_MESSAGE;
            } else {
                data = null;
                message = GlobalConstant.SUCCESS_MESSAGE;
                success = true;
            }
        } catch (Exception e) {
            success = false;
            data = e.getMessage();
            message = GlobalConstant.ERROR_MESSAGE;
        }

        //查询message信息
        return result();
    }
}
