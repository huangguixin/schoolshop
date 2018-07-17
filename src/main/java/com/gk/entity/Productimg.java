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
@TableName("tb_productimg")
public class Productimg extends Model<Productimg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_img_id", type = IdType.AUTO)
    private Integer productImgId;
    private String productImgAddress;
    private String productImgDesc;
    private Date productImgCreateTime;
    private Integer productId;


    public Integer getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Integer productImgId) {
        this.productImgId = productImgId;
    }

    public String getProductImgAddress() {
        return productImgAddress;
    }

    public void setProductImgAddress(String productImgAddress) {
        this.productImgAddress = productImgAddress;
    }

    public String getProductImgDesc() {
        return productImgDesc;
    }

    public void setProductImgDesc(String productImgDesc) {
        this.productImgDesc = productImgDesc;
    }

    public Date getProductImgCreateTime() {
        return productImgCreateTime;
    }

    public void setProductImgCreateTime(Date productImgCreateTime) {
        this.productImgCreateTime = productImgCreateTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    protected Serializable pkVal() {
        return this.productImgId;
    }

    @Override
    public String toString() {
        return "Productimg{" +
        ", productImgId=" + productImgId +
        ", productImgAddress=" + productImgAddress +
        ", productImgDesc=" + productImgDesc +
        ", productImgCreateTime=" + productImgCreateTime +
        ", productId=" + productId +
        "}";
    }
}
