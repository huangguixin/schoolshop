package com.gk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gk.entity.Area;
import com.gk.entity.Headline;
import com.gk.entity.Product;
import com.gk.entity.Productcategory;
import com.gk.entity.Shop;
import com.gk.entity.Shopcategory;
import com.gk.service.AreaService;
import com.gk.service.HeadlineService;
import com.gk.service.ProductService;
import com.gk.service.ProductcategoryService;
import com.gk.service.ShopService;
import com.gk.service.ShopcategoryService;
import com.gk.util.MapFactory;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hgx
 * @since 2018-07-06
 */
@Controller
@RequestMapping("/headline")
public class HeadlineController {

	@Autowired
	private HeadlineService headlineService;

	@Autowired
	private ShopcategoryService shopCategoryService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductcategoryService productCategoryService;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/listproductsbyshop")
	@ResponseBody
	public Map<String, Object> listProductsByShop(Integer pageIndex, Integer pageSize, Integer productCategoryId,
			Integer shopId, String productName) {
		Map<String, Object> map = MapFactory.getMap();

		EntityWrapper<Product> entity=new EntityWrapper<>();
		
		Page<Product> page=new Page<>(pageIndex,pageSize);
		
		if(productCategoryId!=null && productCategoryId>0) {
			entity.eq("productcategory_id", productCategoryId);
		}
		
		if(shopId!=null && shopId>0) {
			entity.eq("shop_id", shopId);
		}
		
		if(productName!=null && !productName.equals("")) {
			entity.like("product_name", productName);
		}
		
		List<Product> selectPageByProduct = productService.selectPageByProduct(page,entity);
		page.setRecords(selectPageByProduct);
		
		map.put("success", true);
		map.put("page", page);
		
		return map;
	}

	@RequestMapping("/shopdetail")
	public String shopDetailPre() {

		return "/pro/shopdetail";
	}

	@ResponseBody
	@RequestMapping(value = "/listshopdetailpageinfo")
	public Map<String, Object> listShopDetailPageInfo(Integer shopId) {

		System.out.println(shopId+"******");
		
		Map<String, Object> map = MapFactory.getMap();

		if (shopId != null && shopId > 0) {
			List<Productcategory> productCategoryList = productCategoryService
					.selectList(new EntityWrapper<Productcategory>().eq("product_category_shop_id", shopId));

			Shop shop = shopService.selectById(shopId);

			map.put("success", true);
			map.put("shop", shop);
			map.put("productCategoryList", productCategoryList);
		} else {
			map.put("success", false);
			map.put("error", "店鋪ID為空");
		}

		return map;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listshops")
	@ResponseBody
	public Map<String, Object> listShops(Integer pageIndex, Integer pageSize, Integer parentId, Integer areaId,
			Integer shopCategoryId, String shopName) {
		Map<String, Object> map = MapFactory.getMap();

		EntityWrapper<Shop> cond = new EntityWrapper<>();

		if (parentId != null && parentId > 0 && shopCategoryId != null && shopCategoryId > 0) {

			cond.eq("shopcategory_id", shopCategoryId);

		} else if (parentId != null && parentId > 0 && shopCategoryId == null) {

			List<Shopcategory> selectList = shopCategoryService
					.selectList(new EntityWrapper<Shopcategory>().eq("parent_id", parentId));
			List<Integer> shopCategoryIds = new ArrayList<>();

			for (Shopcategory shopcategory : selectList) {
				if (shopcategory != null && shopcategory.getShopCategoryId() > 0) {
					shopCategoryIds.add(shopcategory.getShopCategoryId());
				}
			}
			cond.in("shopcategory_id", shopCategoryIds);
		}

		if (areaId != null && areaId > 0) {
			cond.eq("area_id", areaId);
		}

		if (shopName != null && !shopName.equals("")) {
			// cond.eq("shop_name", shopName);
			cond.like("shop_name", shopName);
		}

		Page<Shop> page = new Page<>(pageIndex, pageSize);

		List<Shop> selectPageByShop = shopService.selectPageByShop(page, cond);

		page.setRecords(selectPageByShop);

		map.put("success", true);
		map.put("page", page);

		return map;
	}

	@RequestMapping("/index")
	public String indexPre() {

		return "/pro/index";
	}

	/**
	 * 获取前段首页所需的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getindexinfo")
	@ResponseBody
	public Map<String, Object> getIndexInfo() {

		Map<String, Object> map = MapFactory.getMap();

		List<Headline> headLineList = headlineService
				.selectList(new EntityWrapper<Headline>().eq("head_enable_status", 1));

		List<Shopcategory> shopCategoryList = shopCategoryService
				.selectList(new EntityWrapper<Shopcategory>().isNull("parent_id"));

		System.out.println("**************"+shopCategoryList.toString());
		map.put("success", true);
		map.put("headLineList", headLineList);
		map.put("shopCategoryList", shopCategoryList);

		return map;
	}

	/**
	 * 获取区域信息和某父分类下的子分类信息
	 * 
	 * @return
	 */

	@RequestMapping(value = "/listshopspageinfo")
	@ResponseBody
	public Map<String, Object> listShopsPageInfo(Integer parentId) {
		Map<String, Object> map = MapFactory.getMap();

		if (parentId > 0) {

			List<Shopcategory> shopCategoryList = shopCategoryService
					.selectList(new EntityWrapper<Shopcategory>().eq("parent_id", parentId));

			map.put("shopCategoryList", shopCategoryList);// areaList

			List<Area> areaList = areaService.selectList(null);
			map.put("areaList", areaList);

			map.put("success", true);
			return map;

		} else {
			map.put("success", false);
			map.put("error", "父ID为空");
			return map;
		}

	}

	/**
	 * 跳转到前段商品列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/shoplist")
	public String shopListPre() {
		return "/pro/shoplist";
	}

}
