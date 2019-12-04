package com.wer.controller.message;

import com.wer.common.GlobalConstant;
import com.wer.common.duplicate.DuplicateSubmitToken;
import com.wer.controller.base.BaseController;
import com.wer.service.WxMessageService;
import com.wer.service.msg.MessageService;
import com.wer.util.FTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;

/**
 * @description: 获取公告信息控制类
 * @package_name: com.wer.controller
 * @data: 2019-10-22 15:14
 * @author: Sean
 * @version: V1.0
 */
@Slf4j
@Controller
@RequestMapping(value = "messageController")
public class MessageController extends BaseController{

    @Autowired
    private WxMessageService wxMessageService;

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
        model.addAttribute("msg", wxMessageService.queryMessageByMsgId(msgId));
        return GlobalConstant.MSG_DETAIL;
    }

    /**
     * 新增通知公告信息
     * @param jsonParam
     * @return
     */
    @DuplicateSubmitToken
    @PostMapping(value = "saveMessage")
    @ResponseBody
    public Map<String,Object> saveMessage(@Validated @RequestParam(value = "jsonParam") String jsonParam){
        //查询message信息
        return getResultMap(messageService.saveMessage(jsonParam));
    }

    /**
     * 查询前五条最新的公告信息
     */
    @GetMapping(value = "queryTop5MessageInfo")
    @ResponseBody
    public Map<String,Object> queryTop5MessageInfo(){
        //查询message信息
        return getResultMap(messageService.queryTop5MessageInfo());
    }

    /**
     * 查询前五条最新的公告信息
     */
    @GetMapping(value = "queryMessageByMesssgeId")
    public String queryMessageByMesssgeId(Model model,
                                      @RequestParam(value = "msgId") String msgId){

        //查询message信息
        model.addAttribute("msg", messageService.queryMessageByMsgId(msgId));
        return "msg/msg_detail_01";
    }

    /**
     * 上传通知公告附件
     */
    @PostMapping(value = "uploadMessageImage")
    @ResponseBody
    public Map<String,Object> uploadMessageImage(@RequestParam("imgbase64") String data,
                                     @RequestParam("fileName") String fileName,
                                     @RequestParam("fileSize") String fileSize) throws IOException {
        String base64Data =  null;
        /**
         * 2.解码成字节数组
         */

        // 通过base64来转化图片
        base64Data = data.replaceAll("data:image/jpeg;base64,", "");

        BASE64Decoder decoder = new BASE64Decoder();
        //连接ftp
        FTPUtils instance = FTPUtils.getInstance();

        //将字符串转换为byte数组
        byte[] bytes = new byte[1024];
        try {
            bytes = new BASE64Decoder().decodeBuffer(base64Data.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ext = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        //转化为输入流
        InputStream inputStream = new ByteArrayInputStream(bytes);
        OutputStream os = null;
        instance.storeFile("192.168.0.100","/attachment/images/",UUID.randomUUID().toString()+ext,inputStream);
       // boolean flag = instance.uploadFile("192.168.0.100","/attachment/images/", UUID.randomUUID().toString()+ext,inputStream);
        try {
            os = new FileOutputStream("e:\\"+UUID.randomUUID().toString()+ext);
            byte[] b = new byte[2014];
            int len;

            while(((len = inputStream.read(b)) != -1)){
                os.write(b,0,len);
            }

        }catch (Exception e){

        } finally {
            System.out.println("写出成功");
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream.close();
        }




        Map<String,Object> map = new HashMap<>();
        //说明上传文件成功
        //if(flag){
            map.put("success",true);
        //}
        //查询message信息
        return map;
    }
}
