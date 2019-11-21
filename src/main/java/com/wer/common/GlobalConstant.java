package com.wer.common;

import com.wer.entity.test.VisaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 常量配置
 */
public class GlobalConstant {
    private static final Logger logger = LoggerFactory.getLogger(GlobalConstant.class);

    public static Map<String,Object> visaDataMap = null;

    static {
        logger.info("放入visaDataMap数据 begin");
        visaDataMap = new HashMap<>();

        VisaInfo visaInfo = new VisaInfo("美国","纽约",
                "东直门外大街3号<br/>\n" +
                        "                        电话:938489304<br/>\n" +
                        "                        传真:9493093020939<br/>\n" +
                        "                        贸易处:东直门外大街5号中旅大厦","周一至周五","7个工作日","自","43434","4343434","3e2323","3232323","备注信息","2019-11-19");

        VisaInfo visaInfo1 = new VisaInfo("俄罗斯","俄罗斯",
                "东直门外大街3号<br/>\n" +
                        "                        电话:938489304<br/>\n" +
                        "                        传真:9493093020939<br/>\n" +
                        "                        贸易处:东直门外大街5号中旅大厦","周一至周五","7个工作日","自","43434","4343434","3e2323","3232323","备注信息","2019-11-19");
        visaDataMap.put("美国",visaInfo);    //美国
        visaDataMap.put("俄罗斯",visaInfo1); //英国

        logger.info("放入visaDataMap数据 end");
    }
    //项目前缀
    public static String KEY_PREFIX = "wer";
    //redis key分割符
    public static String KEY_SPLIT_CHAR = ":";

    //文件常用后缀
    public static final String PROP_SUFFIX = ".properties";

    //微信企业号发送的消息类型
    public static final String WX_MSG_TYPE = "MsgType";

    //所有配置文件名称,多个中间以,逗号进行分隔
    public static final String BASE_FILE_NAMES = "wer,webservice";

    //数据访问成功提示
    public static final String SUCCESS_MESSAGE = "数据访问成功!!";

    public static final String ERROR_MESSAGE = "数据访问失败!!";

    public static final String ALREADTY_AUTHORIZE = "已经认证通过，请勿重新认证";

    //附件类型
    public static final String FILE_TYPE = "FILE_TYPE";

    /**
     * clickKey
     */
    public static final String MSG_QUERY_CLICK = "msg_query_click";

    public static final String VISA_QUERY_CLICK = "visa_query_click";

    public static final String JOIN_QR_CODE_CLICK = "join_qr_code_click";

    public static final String INTER_URIS_STR = "/wer/messageController/queryMessageByMsgId,";


    /**
     * 跳转界面
     */
    //护照签证要求
    public static final String VISA_CLAIM = "visa/visa_claim";
    //
    public static final String GRID = "apec/grid";
    //公告详情界面
    public static final String MSG_DETAIL = "msg/msg_detail";
    //认证界面
    public static final String AUTH_INFO = "auth/authorize";

    //同意协议页面
    public static final String AGREEMENT = "auth/agreement";
    //认证成功页面
    public static final String AGREEMENT_SUCCESS = "auth/success";
    //认证失败页面
    public static final String AGREEMENT_ERROR = "auth/error";

    //已认证用户
    public static final String AUTH_ALREADY = "";

    public static Set<String> userList = new HashSet<>();
}
