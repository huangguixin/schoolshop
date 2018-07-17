package com.gk.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gk.entity.Productcategory;
import com.gk.service.ProductcategoryService;
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
@RequestMapping("/productcategory")
public class ProductcategoryController {

	@Autowired
	private ProductcategoryService productCategoryService;
	
	
	@RequestMapping(value = "/removeproductcategory")
	@ResponseBody
	public Map<String,Object> removeproductcategory(Integer productCategoryId,Integer shopId){
		Map<String, Object> map = MapFactory.getMap();
		System.out.println("******"+productCategoryId+"----"+shopId+"*******");
		try {
			if(productCategoryId> 0&& shopId>0) {
				
				boolean delete = productCategoryService.delete(new EntityWrapper<Productcategory>()
						              .eq("product_category_id", productCategoryId)
		                     	      .eq("product_category_shop_id", shopId));
				
				if(delete) {
					map.put("success", true);
				}else {
					map.put("success", false);
					map.put("error", "删除失败");
					return map;
				}

				
			}else {
				
			}
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage()+"");
			return map;
		}
		
		
		
		
		return map;
	}
	
	/**
	 * 批量添加商品类别
	 * @param productCategoryList
	 * @return
	 */
	@RequestMapping(value = "/addBatch")
	@ResponseBody
	public Map<String, Object> addBatch(@RequestBody List<Productcategory> productCategoryList,HttpServletRequest request) {
		Map<String, Object> map = MapFactory.getMap();
		try {
			
			
			Integer shopId = (Integer) request.getSession().getAttribute("shopId");
			if(shopId!=null && shopId>0) {
				for (Productcategory productcategory : productCategoryList) {
					productcategory.setProductCategoryCreateTime(new Date());
					productcategory.setProductCategoryShopId(shopId);
				}
			}else {
				map.put("success", false);
				map.put("error", "商鋪ID 為空");
				return map;
			}

			boolean insertBatch = productCategoryService.insertBatch(productCategoryList);

			if (insertBatch) {
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("error", "批量插入 失败");
				return map;
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage() + "");
			return map;
		}

		return map;
	}
	
	
	
	
	/**
	 * 根据店铺ID 获取该店铺下的所有商品分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getproductcategorylist")
	@ResponseBody
	public Map<String,Object> getproductcategorylist(HttpServletRequest request){
		
		Map<String,Object> map=MapFactory.getMap();
		
		/*@SuppressWarnings("unused")
		Integer shopId=HttpServletRequestUtil.getInt(request, "shopId");*/
		Integer shopId=null;
		try {
			shopId = (Integer) request.getSession().getAttribute("shopId");
		} catch (Exception e) {
			map.put("success", false);
	    	map.put("error"," 商铺ID错误");
	    	return map;
		}
		
		
	    if(shopId>0) {
	    	
	    	List<Productcategory> data = productCategoryService.selectList(new EntityWrapper<Productcategory>().eq("product_category_shop_id", shopId).orderBy("product_category_priority", false));
	    	map.put("data", data);
	    	map.put("success", true);
	    }else {
	    	map.put("success", false);
	    	map.put("error"," 商铺ID错误");
	    	return map;
	    }
		return map;
	}
	
	
	/**
	 * 跳转到店铺下商品类别管理页面
	 * @return
	 */
	@RequestMapping(value="getproductcategorys")
	public String getProductcategorysPre() {
		
		return "shop/productcategorymanage";
	}
}

