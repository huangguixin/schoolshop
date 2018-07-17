package com.gk.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gk.dto.ShopExecution;
import com.gk.entity.Area;
import com.gk.entity.Shop;
import com.gk.entity.Shopcategory;
import com.gk.entity.User;
import com.gk.enums.ShopStateEnum;
import com.gk.service.AreaService;
import com.gk.service.ShopService;
import com.gk.service.ShopcategoryService;
import com.gk.util.CodeUtil;
import com.gk.util.HttpServletRequestUtil;
import com.gk.util.JsonUtils;
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
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopcategoryService shopCategoryService;

	@Autowired
	private AreaService areaService;

	Logger logger = LoggerFactory.getLogger(Shop.class);
	
	/**
	 * 获取某店家下的所有店铺信息
	 * @param request
	 * @return
	 */
		
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String,Object> getList(HttpServletRequest request){
		Map<String, Object> map = MapFactory.getMap();
		
		User user=new User();
		user.setUserId(1);
		user.setUserName("hgx");
		
		List<Shop> shopList = shopService.selectList(new EntityWrapper<Shop>().eq("user_id", user.getUserId()));
		map.put("success", true);
		map.put("shopList", shopList);
		map.put("user", user);
		
		return map;
	}
	
	
	
	

	/**
	 * 根据Id获取店铺信息
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/getShopById")
	@ResponseBody
	public Map<String, Object> getShopById(HttpServletRequest request) {

		Map<String, Object> map = MapFactory.getMap();

		/*Integer shopId = HttpServletRequestUtil.getInt(request, "shopId");*/
		

		try {
			
			Integer shopId = (Integer) request.getSession().getAttribute("shopId");
			

			if (shopId > 0) {

				Shop shop = shopService.selectById(shopId);
				Shopcategory shopCategory = shopCategoryService.selectById(shop.getShopcategoryId());
				Area area = areaService.selectById(shop.getAreaId());
				List<Area> areaList = areaService.selectList(new EntityWrapper<Area>());
				map.put("areaList", areaList);
				shop.setShopCategory(shopCategory);
				shop.setArea(area);

				map.put("success", true);
				map.put("shop", shop);
				
				return map;

			} else {

				throw new RuntimeException(ShopStateEnum.NULL_SHOPID + "");
			}

		} catch (Exception e) {

			map.put("success", false);
			map.put("error", e.getMessage());
			return map;
		}

	}

	/**
	 * 注册店铺
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request) {

		Map<String, Object> map = MapFactory.getMap();

		String verifyCode = HttpServletRequestUtil.getString(request, "verifyCode");

		if (!CodeUtil.checkVerifyCode(request)) {
			map.put("success", false);
			map.put("error", "输入了错误的验证码");
			return map;
		}

		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		logger.debug(shopStr + "");

		if (shopStr == null) {
			map.put("success", false);
			map.put("error", "表单不能为空");
			return map;
		} else {

			Shop shop = JsonUtils.jsonToPojo(shopStr, Shop.class);
			System.out.println(shop);
			MultipartHttpServletRequest multipartRequest = null;
			CommonsMultipartFile shopImgFile = null;
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				shopImgFile = (CommonsMultipartFile) multipartRequest.getFile("shopImg");

				try {
					ShopExecution register = shopService.register(shop, shopImgFile.getInputStream(),
							shopImgFile.getOriginalFilename());

					logger.debug(register + "****************");
				} catch (Exception e) {
					map.put("success", false);
					map.put("error", "注册失败");
					return map;
				}

			} else {
				map.put("success", false);
				map.put("error", "上传图片不能为空");
				return map;
			}

		}

		map.put("success", true);
		return map;
	}

	
	/**
	 * 修改店鋪信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyshop")
	@ResponseBody
	public Map<String, Object> modifyshop(HttpServletRequest request) {

		Map<String, Object> map = MapFactory.getMap();
		
		String verifyCode = HttpServletRequestUtil.getString(request, "verifyCode");

		if (!CodeUtil.checkVerifyCode(request)) {
			map.put("success", false);
			map.put("error", "输入了错误的验证码");
			return map;
		}
		
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		logger.debug(shopStr + "");

		if (shopStr == null) {
			map.put("success", false);
			map.put("error", "表单不能为空");
			return map;
		} else {

			Shop shop = JsonUtils.jsonToPojo(shopStr, Shop.class);
			System.out.println(shop);
			MultipartHttpServletRequest multipartRequest = null;
			CommonsMultipartFile shopImgFile = null;
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				shopImgFile = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
				ShopExecution modifyshop=null;
				try {
					if(shopImgFile==null) {
						modifyshop = shopService.modifyshop(shop, null,null);
					}else {
						 modifyshop = shopService.modifyshop(shop, shopImgFile.getInputStream(), shopImgFile.getOriginalFilename());
					}
					
					if(modifyshop.getState()==ShopStateEnum.SUCCESS.getState()) {
						map.put("success", true);
					}else {
						map.put("success", false);
						map.put("error","修改失敗");
					}
					
					
				} catch (RuntimeException | IOException e) {
					map.put("success", false);
					map.put("error", e.getMessage()+"");
				}

			} else {
				map.put("success", false);
				map.put("error", "修改失敗");
				return map;
			}

		}
		return map;
	}

	/**
	 * 跳转到店铺注册或修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "shopoperator")
	public String registerPre() {

		return "shop/addOrUpdateShop";
	}
	
	/**
	 * 跳转到商铺列表页面
	 * @return
	 */
	
	@RequestMapping(value = "shoplist")
	public String shopListPre() {

		return "shop/shoplist";
	}
	
	/**
	 * 跳转到商铺管理页面
	 * @return
	 */
	@RequestMapping(value = "shopmanage",method=RequestMethod.GET)
	public String shopManagePre(@RequestParam(value="shopId",required=true) Integer shopId,HttpServletRequest request) {
		System.out.println("**********"+shopId);
		request.getSession().setAttribute("shopId", null);
		request.getSession().setAttribute("shopId", shopId);
		return "shop/shopmanage";
	}


}
