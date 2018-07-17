package com.gk.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.gk.entity.Area;
import com.gk.entity.Productcategory;
import com.gk.entity.Shopcategory;
import com.gk.service.AreaService;
import com.gk.service.ProductcategoryService;
import com.gk.service.ShopcategoryService;
import com.gk.util.HttpServletRequestUtil;
import com.gk.util.MapFactory;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hgx
 * @since 2018-07-06
 */
@Controller
@RequestMapping("/shopcategory")
public class ShopcategoryController {

	@Autowired
	private ShopcategoryService shopCategoryService;
	
	@Autowired
	private AreaService areaServcie;
	
	
	
	
	/**
	 * 获取父分类为空的商息信息
	 * @return
	 */
	@RequestMapping(value="/getShopCategoryInfo")
	@ResponseBody
	public Map<String,Object> getShopCategoryInfo(){
		
		Map<String,Object> map=MapFactory.getMap();
		
		try {
			List<Shopcategory> shopCategoryList = shopCategoryService.selectList(new EntityWrapper<Shopcategory>().isNull("parent_id"));
			map.put("success", true);
			map.put("shopCategoryList", shopCategoryList);
			
			List<Area> areaList = areaServcie.selectList(new EntityWrapper<Area>());
			map.put("areaList", areaList);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage());
		}
		
		return map;
		
	}
	
	/**
	 * 获取父分类不为空的商息信息
	 * @return
	 */
	@RequestMapping(value="/getShopCategoryInfos")
	@ResponseBody
	public Map<String,Object> getShopCategoryInfos(){
		
		Map<String,Object> map=MapFactory.getMap();
		
		try {
			List<Shopcategory> shopCategoryList = shopCategoryService.selectList(new EntityWrapper<Shopcategory>().isNotNull("parent_id"));
			map.put("success", true);
			map.put("shopCategoryList", shopCategoryList);
			
			List<Area> areaList = areaServcie.selectList(new EntityWrapper<Area>());
			map.put("areaList", areaList);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage());
		}
		
		return map;
		
	}
	
	
	
}

