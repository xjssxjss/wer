package com.wer.entity.wx;

import java.util.Date;

public class WxCaptureScreenLog {
    private String csId;

    private String csTitle;

    private String csUrl;

    private String csAddrIp;

    private Date csCaptureTime;

    private Boolean csValid;

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId == null ? null : csId.trim();
    }

    public String getCsTitle() {
        return csTitle;
    }

    public void setCsTitle(String csTitle) {
        this.csTitle = csTitle == null ? null : csTitle.trim();
    }

    public String getCsUrl() {
        return csUrl;
    }

    public void setCsUrl(String csUrl) {
        this.csUrl = csUrl == null ? null : csUrl.trim();
    }

    public String getCsAddrIp() {
        return csAddrIp;
    }

    public void setCsAddrIp(String csAddrIp) {
        this.csAddrIp = csAddrIp == null ? null : csAddrIp.trim();
    }

    public Date getCsCaptureTime() {
        return csCaptureTime;
    }

    public void setCsCaptureTime(Date csCaptureTime) {
        this.csCaptureTime = csCaptureTime;
    }

    public Boolean getCsValid() {
        return csValid;
    }

    public void setCsValid(Boolean csValid) {
        this.csValid = csValid;
    }
}