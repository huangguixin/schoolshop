package com.gk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("tb_area")
public class Area extends Model<Area> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "area_id", type = IdType.AUTO)
    private Integer areaId;
    private String areaName;
    private Integer areaPriority;
    private Date areaCreateTime;
    private Date areaEditTime;


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaPriority() {
        return areaPriority;
    }

    public void setAreaPriority(Integer areaPriority) {
        this.areaPriority = areaPriority;
    }

    public Date getAreaCreateTime() {
        return areaCreateTime;
    }

    public void setAreaCreateTime(Date areaCreateTime) {
        this.areaCreateTime = areaCreateTime;
    }

    public Date getAreaEditTime() {
        return areaEditTime;
    }

    public void setAreaEditTime(Date areaEditTime) {
        this.areaEditTime = areaEditTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.areaId;
    }

    @Override
    public String toString() {
        return "Area{" +
        ", areaId=" + areaId +
        ", areaName=" + areaName +
        ", areaPriority=" + areaPriority +
        ", areaCreateTime=" + areaCreateTime +
        ", areaEditTime=" + areaEditTime +
        "}";
    }
}
