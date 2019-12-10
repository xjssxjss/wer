package com.wer.enums;

public enum ResultCode {
    SUCCESS(200,"成功"),               //成功
    FAIL(400,"失败"),                  //失败
    UNAUTHORIZED(401,"签名错误"),          //未认证（签名错误）
    TIMESTAMP_INVALID(402,"时间戳不合法"),
    NOT_FOUND(404,"接口不存在"),             //接口不存在,
    TIMESTAMP_TIME_OUT(407,"时间戳已失效"), //说明时间戳已超过24H
    WEBSERVICE_CONN_FAIL(405,"服务接口连接失败"),
    SESSION_INVALID(406,"session已失效"),
    VISA_COUNTRY_NOT_FOUNT(408,"签证要求国家未匹配,请重新输入"),
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"); //服务器内部错误

    private Integer state;
    private String descript;

    ResultCode(Integer state, String descript) {
        this.state = state;
        this.descript = descript;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * 将操作码转换为具体的操作名称
     *
     * @param code 操作码
     * @return 返回操作名称
     */
    public static String getResult(Integer code) {
        for (ResultCode opcode : ResultCode.values()) {
            if (opcode.getState().equals(code)) {
                return opcode.getDescript();
            }
        }
        return null;
    }
}