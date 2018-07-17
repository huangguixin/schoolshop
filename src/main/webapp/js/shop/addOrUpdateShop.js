$(function() {

	var shopId = getQueryString('shopId');

	var isEdit = shopId ? true : false;

	//var shopInfoUrl = '/myo2o/shop/getshopbyid?shopId=1';
    var shopInfoUrl = '/shop/shop/getShopById?shopId=' + shopId+'&time='+new Date();
	var initUrl = '/shop/shopcategory/getShopCategoryInfos?time'+new Date();
	
	
	var editShopUrl = '/shop/shop/register';
	if (isEdit) {
		editShopUrl = '/shop/shop/modifyshop';
	}

	function getInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddress);
				$('#shop-phone').val(shop.shopPhone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled','disabled');
				$('#area').html(tempAreaHtml);
				$("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
			}
		});
	}
	
	function getCategory() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#shop-category').removeAttr('disabled');
				$('#area').html(tempAreaHtml);
			}
		});
	}

	if (isEdit) {
		getInfo(shopId);
	} else {
		getCategory();
	}

	$('#submit').click(function() {
		var shop = {};

		if (isEdit) {
			shop.shopId=shopId;
		}
		
		shop.shopName = $('#shop-name').val();
		shop.shopAddress = $('#shop-addr').val();
		shop.shopPhone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();

		shop.shopCategory = {
				shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};

		var shopImg = $("#shop-img")[0].files[0];
		var formData = new FormData();
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));
		var verifyCodeActual = $('#j_captcha').val();
		alert(verifyCodeActual);
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCode", verifyCodeActual);
		$.ajax({
			url : editShopUrl,
			type : 'POST',
			// contentType: "application/x-www-form-urlencoded; charset=utf-8",
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					window.location.href = 	'/shop/shop/shoplist';
					/*if (isEdit){
						$('#captcha_img').click();
					} else{
						window.location.href="/shop/shoplist";
					}*/
				} else {
					$.toast('提交失败！');
					$.toast(data.error);
					//$('#captcha_img').click();
				}
			}
		});
	});

});