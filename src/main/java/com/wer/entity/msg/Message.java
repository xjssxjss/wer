package com.wer.entity.msg;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String msgId;

    @NotEmpty(message = "公告标题不允许为空")
    private String msgTitle;

    @NotEmpty(message = "公告正文不允许为空")
    private String msgContent;

    @NotNull(message = "生效日期不可为空")
    private Date msgStartDate;

    @NotNull(message = "失效日期不可为空")
    private Date msgEndDate;

    private Date msgPubTime;

    private String msgPubUserId;

    private Integer msgType;

    private Integer msgAttachmentId;

    private Boolean msgIsValid;

    private Boolean msgIsDelete;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Date getMsgStartDate() {
        return msgStartDate;
    }

    public void setMsgStartDate(Date msgStartDate) {
        this.msgStartDate = msgStartDate;
    }

    public Date getMsgEndDate() {
        return msgEndDate;
    }

    public void setMsgEndDate(Date msgEndDate) {
        this.msgEndDate = msgEndDate;
    }

    public Date getMsgPubTime() {
        return msgPubTime;
    }

    public void setMsgPubTime(Date msgPubTime) {
        this.msgPubTime = msgPubTime;
    }

    public String getMsgPubUserId() {
        return msgPubUserId;
    }

    public void setMsgPubUserId(String msgPubUserId) {
        this.msgPubUserId = msgPubUserId == null ? null : msgPubUserId.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getMsgAttachmentId() {
        return msgAttachmentId;
    }

    public void setMsgAttachmentId(Integer msgAttachmentId) {
        this.msgAttachmentId = msgAttachmentId;
    }

    public Boolean getMsgIsValid() {
        return msgIsValid;
    }

    public void setMsgIsValid(Boolean msgIsValid) {
        this.msgIsValid = msgIsValid;
    }

    public Boolean getMsgIsDelete() {
        return msgIsDelete;
    }

    public void setMsgIsDelete(Boolean msgIsDelete) {
        this.msgIsDelete = msgIsDelete;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId='" + msgId + '\'' +
                ", msgTitle='" + msgTitle + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgStartDate=" + msgStartDate +
                ", msgEndDate=" + msgEndDate +
                ", msgPubTime=" + msgPubTime +
                ", msgPubUserId='" + msgPubUserId + '\'' +
                ", msgType=" + msgType +
                ", msgAttachmentId=" + msgAttachmentId +
                ", msgIsValid=" + msgIsValid +
                ", msgIsDelete=" + msgIsDelete +
                '}';
    }
}