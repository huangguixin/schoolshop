package com.gk.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("tb_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;
    private String productName;
    private String productDesc;
    private String productImgAddress;
    private Double productNormalPrice;
    private Double productPromotionPrice;
    private Integer productPriority;
    private Date productCreateTime;
    private Date productEditTime;
    private Integer productEnableStatus;
    
    
    private Integer shopId;
    private Integer productcategoryId;
    
    @TableField(exist=false)
    private Shop shop;
    @TableField(exist=false)
    private Productcategory produceCategory;


    public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Productcategory getProduceCategory() {
		return produceCategory;
	}

	public void setProduceCategory(Productcategory produceCategory) {
		this.produceCategory = produceCategory;
	}

	public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductImgAddress() {
        return productImgAddress;
    }

    public void setProductImgAddress(String productImgAddress) {
        this.productImgAddress = productImgAddress;
    }

    public Double getProductNormalPrice() {
        return productNormalPrice;
    }

    public void setProductNormalPrice(Double productNormalPrice) {
        this.productNormalPrice = productNormalPrice;
    }

    public Double getProductPromotionPrice() {
        return productPromotionPrice;
    }

    public void setProductPromotionPrice(Double productPromotionPrice) {
        this.productPromotionPrice = productPromotionPrice;
    }

    public Integer getProductPriority() {
        return productPriority;
    }

    public void setProductPriority(Integer productPriority) {
        this.productPriority = productPriority;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductEditTime() {
        return productEditTime;
    }

    public void setProductEditTime(Date productEditTime) {
        this.productEditTime = productEditTime;
    }

    public Integer getProductEnableStatus() {
        return productEnableStatus;
    }

    public void setProductEnableStatus(Integer productEnableStatus) {
        this.productEnableStatus = productEnableStatus;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getProductcategoryId() {
        return productcategoryId;
    }

    public void setProductcategoryId(Integer productcategoryId) {
        this.productcategoryId = productcategoryId;
    }

    @Override
    protected Serializable pkVal() {
        return this.productId;
    }

    @Override
    public String toString() {
        return "Product{" +
        ", productId=" + productId +
        ", productName=" + productName +
        ", productDesc=" + productDesc +
        ", productImgAddress=" + productImgAddress +
        ", productNormalPrice=" + productNormalPrice +
        ", productPromotionPrice=" + productPromotionPrice +
        ", productPriority=" + productPriority +
        ", productCreateTime=" + productCreateTime +
        ", productEditTime=" + productEditTime +
        ", productEnableStatus=" + productEnableStatus +
        ", shopId=" + shopId +
        ", productcategoryId=" + productcategoryId +
        "}";
    }
}
