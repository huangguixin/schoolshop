package com.gk.service;

import com.gk.dto.ShopExecution;
import com.gk.entity.Shop;
import com.gk.mapper.ShopMapper;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hgx
 * @since 2018-07-06
 */
public interface ShopService extends IService<Shop> {

	public ShopExecution register(Shop shop, InputStream inputStream,String fileName)
			throws RuntimeException;
	
	
	public ShopExecution modifyshop(Shop shop, InputStream inputStream,String fileName) throws RuntimeException;
	
	
	public List<Shop> selectPageByShop(Page<Shop> page,EntityWrapper<Shop> entity);
	
}
