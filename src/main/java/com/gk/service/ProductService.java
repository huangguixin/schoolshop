package com.gk.service;

import com.gk.entity.Product;

import java.io.InputStream;
import java.util.List;

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
public interface ProductService extends IService<Product> {

	
	public boolean addProduct(Product product,InputStream inputStream,String fileName
			,List<InputStream> listInputStream,List<String> fileNames);
	
	
	public boolean modifyProduct(Product product,InputStream inputStream,String fileName
			,List<InputStream> listInputStream,List<String> fileNames) ;


	public List<Product> selectPageByProduct(Page<Product> page, EntityWrapper<Product> entity);
}
