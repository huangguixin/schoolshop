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
@TableName("tb_productcategory")
public class Productcategory extends Model<Productcategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_category_id", type = IdType.AUTO)
    private Integer productCategoryId;
    private String productCategoryName;
    private Integer productCategoryPriority;
    private Date productCategoryCreateTime;
    private Integer productCategoryShopId;


    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getProductCategoryPriority() {
        return productCategoryPriority;
    }

    public void setProductCategoryPriority(Integer productCategoryPriority) {
        this.productCategoryPriority = productCategoryPriority;
    }

    public Date getProductCategoryCreateTime() {
        return productCategoryCreateTime;
    }

    public void setProductCategoryCreateTime(Date productCategoryCreateTime) {
        this.productCategoryCreateTime = productCategoryCreateTime;
    }

    public Integer getProductCategoryShopId() {
        return productCategoryShopId;
    }

    public void setProductCategoryShopId(Integer productCategoryShopId) {
        this.productCategoryShopId = productCategoryShopId;
    }

    @Override
    protected Serializable pkVal() {
        return this.productCategoryId;
    }

    @Override
    public String toString() {
        return "Productcategory{" +
        ", productCategoryId=" + productCategoryId +
        ", productCategoryName=" + productCategoryName +
        ", productCategoryPriority=" + productCategoryPriority +
        ", productCategoryCreateTime=" + productCategoryCreateTime +
        ", productCategoryShopId=" + productCategoryShopId +
        "}";
    }
}
