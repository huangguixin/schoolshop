package com.gk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hgx
 * @since 2018-07-06
 */
@TableName("tb_headline")
public class Headline extends Model<Headline> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "head_id", type = IdType.AUTO)
    private Integer headId;
    private String headName;
    private String headImg;
    private String headLink;
    private Date headCreateTime;
    private Date headEditTime;
    private Integer headEnableStatus;
    private Integer headPriority;


    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHeadLink() {
        return headLink;
    }

    public void setHeadLink(String headLink) {
        this.headLink = headLink;
    }

    public Date getHeadCreateTime() {
        return headCreateTime;
    }

    public void setHeadCreateTime(Date headCreateTime) {
        this.headCreateTime = headCreateTime;
    }

    public Date getHeadEditTime() {
        return headEditTime;
    }

    public void setHeadEditTime(Date headEditTime) {
        this.headEditTime = headEditTime;
    }

    public Integer getHeadEnableStatus() {
        return headEnableStatus;
    }

    public void setHeadEnableStatus(Integer headEnableStatus) {
        this.headEnableStatus = headEnableStatus;
    }

    public Integer getHeadPriority() {
        return headPriority;
    }

    public void setHeadPriority(Integer headPriority) {
        this.headPriority = headPriority;
    }

    @Override
    protected Serializable pkVal() {
        return this.headId;
    }

    @Override
    public String toString() {
        return "Headline{" +
        ", headId=" + headId +
        ", headName=" + headName +
        ", headImg=" + headImg +
        ", headLink=" + headLink +
        ", headCreateTime=" + headCreateTime +
        ", headEditTime=" + headEditTime +
        ", headEnableStatus=" + headEnableStatus +
        ", headPriority=" + headPriority +
        "}";
    }
}
