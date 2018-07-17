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
@TableName("tb_shop")
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;
    private String shopName;
    private String shopDesc;
    private String shopImg;
    private Date shopCreateTime;
    private Date shopEditTime;
    private String shopAdvice;
    private String shopAddress;
    private String shopPhone;
    private Integer shopPriority;
    private Integer shopEnableStatus;
    
    private Integer areaId;
    private Integer shopcategoryId;
    private Integer userId;
    
    @TableField(exist=false)
    private Area area;
    
    @TableField(exist=false)
    private Shopcategory shopCategory;
    
    @TableField(exist=false)
    private User user;
    
    
    


    public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Shopcategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(Shopcategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public Date getShopCreateTime() {
        return shopCreateTime;
    }

    public void setShopCreateTime(Date shopCreateTime) {
        this.shopCreateTime = shopCreateTime;
    }

    public Date getShopEditTime() {
        return shopEditTime;
    }

    public void setShopEditTime(Date shopEditTime) {
        this.shopEditTime = shopEditTime;
    }

    public String getShopAdvice() {
        return shopAdvice;
    }

    public void setShopAdvice(String shopAdvice) {
        this.shopAdvice = shopAdvice;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getShopcategoryId() {
        return shopcategoryId;
    }

    public void setShopcategoryId(Integer shopcategoryId) {
        this.shopcategoryId = shopcategoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopPriority() {
        return shopPriority;
    }

    public void setShopPriority(Integer shopPriority) {
        this.shopPriority = shopPriority;
    }

    public Integer getShopEnableStatus() {
        return shopEnableStatus;
    }

    public void setShopEnableStatus(Integer shopEnableStatus) {
        this.shopEnableStatus = shopEnableStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.shopId;
    }

    @Override
    public String toString() {
        return "Shop{" +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", shopDesc=" + shopDesc +
        ", shopImg=" + shopImg +
        ", shopCreateTime=" + shopCreateTime +
        ", shopEditTime=" + shopEditTime +
        ", shopAdvice=" + shopAdvice +
        ", shopAddress=" + shopAddress +
        ", shopPhone=" + shopPhone +
        ", areaId=" + areaId +
        ", shopcategoryId=" + shopcategoryId +
        ", userId=" + userId +
        ", shopPriority=" + shopPriority +
        ", shopEnableStatus=" + shopEnableStatus +
        "}";
    }
}
