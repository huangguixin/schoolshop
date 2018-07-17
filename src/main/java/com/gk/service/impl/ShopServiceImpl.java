package com.gk.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gk.dto.ShopExecution;
import com.gk.entity.Shop;
import com.gk.entity.Shopcategory;
import com.gk.enums.ShopStateEnum;
import com.gk.mapper.ShopMapper;
import com.gk.service.ShopService;
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
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {
	

	@Autowired
	private ShopMapper shopMapper;
	

	@Transactional
	public ShopExecution register(Shop shop, InputStream inputStream,String fileName)
			throws RuntimeException {
		
        shop.setShopcategoryId(shop.getShopCategory().getShopCategoryId());
        shop.setAreaId(shop.getArea().getAreaId());
		if (shop == null||shop.getShopcategoryId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}
		try {
			shop.setShopEnableStatus(0);
			shop.setShopCreateTime(new Date());
			shop.setShopEditTime(new Date());
			
			boolean effectedNum = insert(shop);
			
			if(!effectedNum) {
				throw new RuntimeException("注册店铺失败");
			}else {
				
				if (inputStream != null) {
					addShopImg(shop, inputStream,fileName);
					effectedNum = updateById(shop);
					if (!effectedNum) {
						throw new RuntimeException("创建图片地址失败");
					}
				}
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return new ShopExecution(ShopStateEnum.SUCCESS, shop);
		}
	
	@Transactional
	public ShopExecution modifyshop(Shop shop, InputStream inputStream,String fileName) throws RuntimeException {
		
		System.out.println("==============="+shop+"============");
		
		shop.setShopcategoryId(shop.getShopCategory().getShopCategoryId());
		shop.setAreaId(shop.getArea().getAreaId());
		if (shop == null||shop.getShopId()==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}else {
			
			if(inputStream!=null &&  fileName!=null) {
				
				Shop shopOld=selectById(shop.getShopId());
				String filePath=shopOld.getShopImg();
				if(filePath!=null) {
					FileUtil.deleteFile(filePath);
				}
				addShopImg(shopOld, inputStream, fileName); 
			}
			
			shop.setShopEditTime(new Date());
			boolean updateById = updateById(shop);
			if(!updateById) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else {
				
				shop = selectById(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS, shop);
			}
		}
	}

	
	
	
	
	private void addShopImg(Shop shop, InputStream inputStream,String fileName) {
		String dest = FileUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(inputStream, fileName,dest);
		shop.setShopImg(FileUtil.getImgBasePath()+shopImgAddr);
	}

	@Override
	public List<Shop> selectPageByShop(Page<Shop> page, EntityWrapper<Shop> entity) {
		return shopMapper.selectPage(page, entity);
	}
	
}
