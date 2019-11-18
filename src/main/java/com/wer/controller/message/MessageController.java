package com.wer.controller.message;

import com.wer.common.GlobalConstant;
import com.wer.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @description: 获取公告信息控制类
 * @package_name: com.wer.controller
 * @data: 2019-10-22 15:14
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping(value = "messageController")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 根据公告id获取公告详情
     * @param model
     * @param msgId
     * @return 跳转公告详情界面
     */
    @GetMapping(value = "queryMessageByMsgId")
    public String queryMessageByMsgId(Model model,
                                      @RequestParam(value = "msgId") String msgId){
        //查询message信息
        model.addAttribute("msg",messageService.queryMessageByMsgId(msgId));
        return GlobalConstant.MSG_DETAIL;
    }
}
