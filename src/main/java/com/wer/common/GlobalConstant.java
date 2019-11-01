package com.wer.common;

/**
 * 常量配置
 */
public class GlobalConstant {
    //项目前缀
    public static String KEY_PREFIX = "wer";
    //redis key分割符
    public static String KEY_SPLIT_CHAR = ":";

    //入库单号前缀
    public static String DN_SUFFIX = "SLCCK";

    //文件常用后缀
    public static final String PROP_SUFFIX = ".properties";

    //微信企业号发送的消息类型
    public static final String WX_MSG_TYPE = "MsgType";

    //所有配置文件名称,多个中间以,逗号进行分隔
    public static final String BASE_FILE_NAMES = "wer,webservice";

    //数据访问成功提示
    public static final String SUCCESS_MESSAGE = "数据访问成功!!";

    public static final String FAIL_PRICE_NOT_COMPLATE = "价格表数据不完整，请维护相关表数据!!";

    //数据访问失败提示

    public static final String ERROR_MESSAGE = "数据访问失败!!";

    //附件类型
    public static final String FILE_TYPE = "FILE_TYPE";
}
