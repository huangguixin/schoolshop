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
@TableName("tb_wechatauth")
public class Wechatauth extends Model<Wechatauth> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "we_chat_id", type = IdType.AUTO)
    private Integer weChatId;
    private Integer weChatOpenId;
    private Date weChatCreateTime;
    private Integer userId;


    public Integer getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(Integer weChatId) {
        this.weChatId = weChatId;
    }

    public Integer getWeChatOpenId() {
        return weChatOpenId;
    }

    public void setWeChatOpenId(Integer weChatOpenId) {
        this.weChatOpenId = weChatOpenId;
    }

    public Date getWeChatCreateTime() {
        return weChatCreateTime;
    }

    public void setWeChatCreateTime(Date weChatCreateTime) {
        this.weChatCreateTime = weChatCreateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.weChatId;
    }

    @Override
    public String toString() {
        return "Wechatauth{" +
        ", weChatId=" + weChatId +
        ", weChatOpenId=" + weChatOpenId +
        ", weChatCreateTime=" + weChatCreateTime +
        ", userId=" + userId +
        "}";
    }
}
