package com.wer.dto.msg;

import com.wer.util.DateUtil;

import java.util.Date;

/**
 * @description: 公告信息dto
 * @package_name: com.wer.dto.msg
 * @data: 2019-12-4 11:16
 * @author: Sean
 * @version: V1.0
 */
public class MessageDto {
    private String msgId;
    private String msgTitle;
    private Date startDate;
    private Date endDate;
    private String msgContent;

    private String msgStartDate;
    private String msgEndDate;

    public MessageDto(String msgId, String msgTitle,Date startDate, Date endDate, String msgContent) {
        this.msgId = msgId;
        this.msgTitle = msgTitle;
        this.msgStartDate = DateUtil.parseDateToStr(startDate,DateUtil.DATE_TIME_FORMAT_YYYY年MM月DD日);
        this.msgEndDate = DateUtil.parseDateToStr(endDate,DateUtil.DATE_TIME_FORMAT_YYYY年MM月DD日);
        this.msgContent = msgContent;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgStartDate() {
        return msgStartDate;
    }

    public void setMsgStartDate(String msgStartDate) {
        this.msgStartDate = msgStartDate;
    }

    public String getMsgEndDate() {
        return msgEndDate;
    }

    public void setMsgEndDate(String msgEndDate) {
        this.msgEndDate = msgEndDate;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "msgId='" + msgId + '\'' +
                ", msgTitle='" + msgTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", msgContent='" + msgContent + '\'' +
                ", msgStartDate='" + msgStartDate + '\'' +
                ", msgEndDate='" + msgEndDate + '\'' +
                '}';
    }
}
