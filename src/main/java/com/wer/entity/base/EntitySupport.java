package com.wer.entity.base;

import java.util.Date;

/**
 * @description: EntitySupport
 * @package_name: com.wer.entity.base
 * @data: 2019-12-9 11:08
 * @author: Sean
 * @version: V1.0
 */
public class EntitySupport implements BaseEntity {

    private String createBy;        //录入人
    private Date createTime;      //创建时间
    private String updateBy;        //修改人
    private Date updateTime;      //修改时间
    private Boolean isDeleted;      //是否被删除

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "EntitySupport{" +
                "createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
