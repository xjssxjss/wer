package com.wer.entity.visa;

import com.wer.entity.sys.DictionaryEntries;

import java.util.Date;

public class VisaArticle {
    private Integer id;

    private String title;

    private String description;

    private Integer picUrl;

    private DictionaryEntries picUrlEntries;

    private String url;

    private Date effectiveDate;

    private Boolean isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(Integer picUrl) {
        this.picUrl = picUrl;
    }

    public DictionaryEntries getPicUrlEntries() {
        return picUrlEntries;
    }

    public void setPicUrlEntries(DictionaryEntries picUrlEntries) {
        this.picUrlEntries = picUrlEntries;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "VisaArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl=" + picUrl +
                ", picUrlEntries=" + picUrlEntries +
                ", url='" + url + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", isValid=" + isValid +
                '}';
    }
}