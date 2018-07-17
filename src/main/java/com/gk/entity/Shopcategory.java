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
@TableName("tb_shopcategory")
public class Shopcategory extends Model<Shopcategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shop_category_id", type = IdType.AUTO)
    private Integer shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Date shopCategoryCreateTime;
    private Date shopCategoryEditTime;
    private Integer shopCategoryPriority;
    private Integer parentId;

    @TableField(exist=false)
    private Shopcategory shopCategory;

    public Shopcategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(Shopcategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public Date getShopCategoryCreateTime() {
        return shopCategoryCreateTime;
    }

    public void setShopCategoryCreateTime(Date shopCategoryCreateTime) {
        this.shopCategoryCreateTime = shopCategoryCreateTime;
    }

    public Date getShopCategoryEditTime() {
        return shopCategoryEditTime;
    }

    public void setShopCategoryEditTime(Date shopCategoryEditTime) {
        this.shopCategoryEditTime = shopCategoryEditTime;
    }

    public Integer getShopCategoryPriority() {
        return shopCategoryPriority;
    }

    public void setShopCategoryPriority(Integer shopCategoryPriority) {
        this.shopCategoryPriority = shopCategoryPriority;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.shopCategoryId;
    }

    @Override
    public String toString() {
        return "Shopcategory{" +
        ", shopCategoryId=" + shopCategoryId +
        ", shopCategoryName=" + shopCategoryName +
        ", shopCategoryDesc=" + shopCategoryDesc +
        ", shopCategoryImg=" + shopCategoryImg +
        ", shopCategoryCreateTime=" + shopCategoryCreateTime +
        ", shopCategoryEditTime=" + shopCategoryEditTime +
        ", shopCategoryPriority=" + shopCategoryPriority +
        ", parentId=" + parentId +
        "}";
    }
}
