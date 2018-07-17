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
@TableName("tb_localauth")
public class Localauth extends Model<Localauth> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "local_id", type = IdType.AUTO)
    private Integer localId;
    private String localName;
    private String localPassword;
    private Date localCreateTime;
    private Date localEditTime;
    private Integer userId;


    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocalPassword() {
        return localPassword;
    }

    public void setLocalPassword(String localPassword) {
        this.localPassword = localPassword;
    }

    public Date getLocalCreateTime() {
        return localCreateTime;
    }

    public void setLocalCreateTime(Date localCreateTime) {
        this.localCreateTime = localCreateTime;
    }

    public Date getLocalEditTime() {
        return localEditTime;
    }

    public void setLocalEditTime(Date localEditTime) {
        this.localEditTime = localEditTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.localId;
    }

    @Override
    public String toString() {
        return "Localauth{" +
        ", localId=" + localId +
        ", localName=" + localName +
        ", localPassword=" + localPassword +
        ", localCreateTime=" + localCreateTime +
        ", localEditTime=" + localEditTime +
        ", userId=" + userId +
        "}";
    }
}
