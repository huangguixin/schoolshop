package com.gk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gk.entity.Product;
import com.gk.entity.Productcategory;
import com.gk.service.ProductService;
import com.gk.service.ProductcategoryService;
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
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductcategoryService productCategoryService;

	/**
	 * 修改商品信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyproduct")
	@ResponseBody
	public Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> map = MapFactory.getMap();
		Product product=null;
		  Integer shopId = (Integer) request.getSession().getAttribute("shopId");
		if (!CodeUtil.checkVerifyCode(request)) {
			map.put("success", false);
			map.put("errMsg", "输入了错误的验证码");
			return map;
		}
		
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile thumbnail = null;
		List<CommonsMultipartFile> productImgs = new ArrayList<CommonsMultipartFile>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			
			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				thumbnail = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
				for (int i = 0; i < 6; i++) {
					CommonsMultipartFile productImg = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
					if (productImg != null) {
						productImgs.add(productImg);
					}
				}
			} 
			
			
			product = JsonUtils.jsonToPojo(productStr, Product.class);
			if (shopId > 0) {
				product.setShopId(shopId);
			} else {
				map.put("success", false);
				map.put("errMsg", "商铺ID为空");
				return map;
			}
			
			List<InputStream> listInputStream =null;
			List<String> fileNames =null;
			if (product != null && thumbnail != null && productImgs.size() > 0) {
				
				
				
				 listInputStream = new ArrayList<>();
				 fileNames = new ArrayList<>();

				for (CommonsMultipartFile productImg : productImgs) {

					if (productImg != null) {
						listInputStream.add(productImg.getInputStream());
						fileNames.add(productImg.getOriginalFilename());
					}

				}
			}	
			boolean flag=false;
			if(thumbnail!=null && listInputStream!=null ) {
				flag = productService.modifyProduct(product, thumbnail.getInputStream(), 
						thumbnail.getOriginalFilename(), listInputStream, fileNames);
			}else if(thumbnail!=null) {
				flag = productService.modifyProduct(product, thumbnail.getInputStream(), 
						thumbnail.getOriginalFilename(), null, null);
			}else if(listInputStream!=null) {
				flag = productService.modifyProduct(product, null, 
						null, listInputStream, fileNames);
			}else {
				flag = productService.modifyProduct(product, null, 
						null, null, null);
			}
			
			
			if (flag) {
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("error", "失败");
				return map;
			}
			
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage()+"");
			return map;
		}
		
		
		
		
		return map;
	}

	/**
	 * 添加商品信息
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/addproduct")
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> map = MapFactory.getMap();

		Product product = null;
		if (!CodeUtil.checkVerifyCode(request)) {
			map.put("success", false);
			map.put("errMsg", "输入了错误的验证码");
			return map;
		}

		String productStr = HttpServletRequestUtil.getString(request, "productStr");

		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile thumbnail = null;
		List<CommonsMultipartFile> productImgs = new ArrayList<CommonsMultipartFile>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		try {

			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				thumbnail = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
				for (int i = 0; i < 6; i++) {
					CommonsMultipartFile productImg = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
					if (productImg != null) {
						productImgs.add(productImg);
					}
				}
			} else {
				map.put("success", false);
				map.put("errMsg", "上传图片不能为空");
				return map;
			}

			product = JsonUtils.jsonToPojo(productStr, Product.class);

			if (product != null && thumbnail != null && productImgs.size() > 0) {
				Integer shopId = (Integer) request.getSession().getAttribute("shopId");

				if (shopId > 0) {
					product.setShopId(shopId);
				} else {
					map.put("success", false);
					map.put("errMsg", "商铺ID为空");
					return map;
				}

				List<InputStream> listInputStream = new ArrayList<>();
				List<String> fileNames = new ArrayList<>();

				for (CommonsMultipartFile productImg : productImgs) {

					if (productImg != null) {
						listInputStream.add(productImg.getInputStream());
						fileNames.add(productImg.getOriginalFilename());
					}

				}

				boolean flag = productService.addProduct(product, thumbnail.getInputStream(),
						thumbnail.getOriginalFilename(), listInputStream, fileNames);

				if (flag) {
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("error", "失败");
					return map;
				}

				return map;
			}

		} catch (Exception e) {

			map.put("success", false);
			map.put("errMsg", e.toString());
			return map;
		}

		map.put("success", true);
		return map;
	}

	/**
	 * 通过商铺ID获取该商铺下的所有商品类别
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/getproductcategorylistbyshopId")
	@ResponseBody
	public Map<String, Object> getProductCategoryListByShopId(HttpServletRequest request) {
		Map<String, Object> map = MapFactory.getMap();

		try {
			Integer shopId = (Integer) request.getSession().getAttribute("shopId");

			if (shopId > 0) {

				List<Productcategory> productCategoryList = productCategoryService
						.selectList(new EntityWrapper<Productcategory>().eq("product_category_shop_id", shopId));

				map.put("productCategoryList", productCategoryList);
				map.put("success", true);
			} else {

				map.put("success", false);
				map.put("error", "商铺ID错误");
				return map;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return map;
	}

	/**
	 * 通过商品ID 获取该商品的信息
	 */

	@RequestMapping("/getproductbyid")
	@ResponseBody
	public Map<String, Object> getProductById(Integer productId, HttpServletRequest request) {
		Map<String, Object> map = MapFactory.getMap();

		if (productId > 0) {
			Product product = productService.selectById(productId);

			Integer attribute = (Integer) request.getSession().getAttribute("shopId");

			if (attribute > 0) {
				List<Productcategory> productCategoryList = productCategoryService
						.selectList(new EntityWrapper<Productcategory>().eq("product_category_shop_id", attribute));
				map.put("productCategoryList", productCategoryList);

			} else {
				map.put("success", false);
				map.put("error", "请先进入某一家店铺");
				return map;
			}

			map.put("success", true);
			map.put("product", product);
		} else {
			map.put("success", false);
			map.put("error", "商品ID为空");
			return map;
		}

		return map;
	}

	/**
	 * 跳转到商品编辑或添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/productoperator")
	public String productEdit() {

		return "shop/productedit";
	}

	/**
	 * 根据商品ID更新商品的状态
	 * 
	 * @param product
	 * @return
	 */

	@RequestMapping("/modifyproductid")
	@ResponseBody
	public Map<String, Object> modifyProductId(HttpServletRequest request) {
		Map<String, Object> map = MapFactory.getMap();
		Product product = null;
		try {
			String string = HttpServletRequestUtil.getString(request, "productStr");

			product = JsonUtils.jsonToPojo(string, Product.class);
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage() + "");
			return map;
		}

		if (product != null && product.getProductId() > 0) {

			boolean updateById = productService.updateById(product);
			if (updateById) {
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("error", "商品更新失败");
				return map;
			}

		} else {

			map.put("success", false);
			map.put("error", "商品状态错误");
			return map;
		}

		return map;
	}

	/**
	 * 根据商铺ID获取该商铺下的所有商品信息
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/getlist")
	@ResponseBody
	public Map<String, Object> getList(HttpServletRequest request) {

		Map<String, Object> map = MapFactory.getMap();

		try {

			Integer shopId = (Integer) request.getSession().getAttribute("shopId");

			if (shopId > 0) {

				List<Product> productList = productService
						.selectList(new EntityWrapper<Product>().eq("shop_id", shopId));
				map.put("success", true);
				map.put("productList", productList);

			} else {
				map.put("success", false);
				map.put("error", "商铺ID为空");
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
	 * 跳转到商品管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productmanage")
	public String productManage() {
		return "/shop/productmanage";
	}

}
