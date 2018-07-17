package com.gk.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gk.entity.Product;
import com.gk.entity.Productimg;
import com.gk.mapper.ProductMapper;
import com.gk.mapper.ProductimgMapper;
import com.gk.service.ProductService;
import com.gk.service.ProductimgService;
import com.gk.util.FileUtil;
import com.gk.util.ImageUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hgx
 * @since 2018-07-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

	
	@Autowired
	private ProductimgService productImgService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Transactional
	public boolean modifyProduct(Product product,InputStream inputStream,String fileName
			,List<InputStream> listInputStream,List<String> fileNames) {
		
		try {
			
			if(product!=null) {
				product.setProductEditTime(new Date());
				
				if (inputStream != null) {
					Product tempProduct = productService.selectById(product.getProductId());
					if (tempProduct.getProductImgAddress()!= null) {
						FileUtil.deleteFile(tempProduct.getProductImgAddress());
					}
					addThumbnail(product, inputStream, fileName);
				}
				
				if (listInputStream != null && listInputStream.size() > 0) {
					deleteProductImgs(product.getProductId());
					addProductImgs(product, listInputStream, fileNames);
				}
				
				boolean updateById = productService.updateById(product);
				if(updateById) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new RuntimeException("商品不能为空"); 
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage()+""); 
		}
		
	}
	
	
	
	@Transactional
	public boolean addProduct(Product product,InputStream inputStream,String fileName
			,List<InputStream> listInputStream,List<String> fileNames) {
		
	  try {
		  product.setProductCreateTime(new Date());
		  product.setProductEditTime(new Date());
		  product.setProductEnableStatus(1);
		  if(inputStream!=null && fileName!=null) {
			  addThumbnail(product, inputStream, fileName);
		  }
		  boolean insert = productService.insert(product);
		  
		  if(insert) {
			  
			  if (listInputStream != null && listInputStream.size() > 0) {
					addProductImgs(product, listInputStream, fileNames);
				}else {
					return false;
				}
			  
		  }else {
			  
			  throw new RuntimeException("创建商品失败"); 
			  
		  }
	} catch (Exception e) {
		 throw new RuntimeException(e.getMessage()+""); 
	}
	  
	  return true;
	}
	
	
	@SuppressWarnings("unused")
	private void deleteProductImgs(Integer productId) {
		List<Productimg> productImgList = productImgService.selectList(new EntityWrapper<Productimg>()
				                                           .eq("product_id", productId));
		for (Productimg productImg : productImgList) {
			FileUtil.deleteFile(productImg.getProductImgAddress());
		}
	  productImgService.delete(new EntityWrapper<Productimg>().eq("product_id", productId));
	}
	
	
	@SuppressWarnings("unused")
	private void addThumbnail(Product product, InputStream inputStream,String fileName) {
		String dest = FileUtil.getShopImagePath(product.getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(inputStream, fileName, dest);
		product.setProductImgAddress(FileUtil.getImgBasePath()+thumbnailAddr);
	}
	
	
	@SuppressWarnings("unused")
	private void addProductImgs(Product product, List<InputStream> listInputStream,List<String> fileNames) {
		String dest = FileUtil.getShopImagePath(product.getShopId());
		List<String> imgAddrList = ImageUtil.generateNormalImgs(listInputStream, fileNames, dest);
		if (imgAddrList != null && imgAddrList.size() > 0) {
			List<Productimg> productImgList = new ArrayList<Productimg>();
			for (String imgAddr : imgAddrList) {
				Productimg productImg = new Productimg();
				productImg.setProductImgAddress(FileUtil.getImgBasePath()+imgAddr);
				productImg.setProductId(product.getProductId());
				productImg.setProductImgCreateTime(new Date());
				productImgList.add(productImg);
			}
			try {
				boolean effectedNum = productImgService.insertBatch(productImgList);
				if (!effectedNum) {
					throw new RuntimeException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品详情图片失败:" + e.toString());
			}
		}
	}



	@Override
	public List<Product> selectPageByProduct(Page<Product> page, EntityWrapper<Product> entity) {
		return productMapper.selectPage(page, entity);
	}
	
	
	
}
