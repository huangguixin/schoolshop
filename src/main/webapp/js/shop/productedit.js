$(function() {
	var productId = getQueryString('productId');
	//var shopId = 1;
	var infoUrl = '/shop/product/getproductbyid?productId=' + productId;
	var categoryUrl = '/shop/product/getproductcategorylistbyshopId';
	var productPostUrl = '/shop/product/modifyproduct';
	var isEdit = false;
	if (productId) {
		getInfo(productId);
		isEdit = true;
	} else {
		getCategory();
		productPostUrl = '/shop/product/addproduct';
	}

	function getInfo(id) {
		$
				.getJSON(
						infoUrl,
						function(data) {
							if (data.success) {
								var product = data.product;
								$('#product-name').val(product.productName);
								$('#product-desc').val(product.productDesc);
								$('#priority').val(product.productPriority);
								$('#normal-price').val(product.productNormalPrice);
								$('#promotion-price').val(
										product.productPromotionPrice);

								var optionHtml = '';
								var optionArr = data.productCategoryList;
								var optionSelected = product.productcategoryId;
								optionArr
										.map(function(item, index) {
											var isSelect = optionSelected === item.productCategoryId ? 'selected'
													: '';
											optionHtml += '<option data-value="'
													+ item.productCategoryId
													+ '"'
													+ isSelect
													+ '>'
													+ item.productCategoryName
													+ '</option>';
										});
								$('#category').html(optionHtml);
							}
						});
	}

	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.productCategoryList;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
							+ item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#category').html(optionHtml);
			}
		});
	}

	$('.detail-img-div').on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">');
		}
	});

	$('#submit').click(
			function() {
				var product = {};
				
				if(productId){
					product.productId=productId;
				}
				
				product.productName = $('#product-name').val();
				product.productDesc = $('#product-desc').val();
				product.productPriority = $('#priority').val();
				product.productNormalPrice = $('#normal-price').val();
				product.productPromotionPrice = $('#promotion-price').val();
				product.productcategoryId=$('#category').find('option').not(
						function() {
							return !this.selected;
						}).data('value');
				/*product.produceCategory = {
					productCategoryId : $('#category').find('option').not(
							function() {
								return !this.selected;
							}).data('value')
				};*/
				/*product.productId = productId;*/

				var thumbnail = $('#small-img')[0].files[0];
				//console.log(thumbnail);
				var formData = new FormData();
				formData.append('thumbnail', thumbnail);
				$('.detail-img').map(
						function(index, item) {
							if ($('.detail-img')[index].files.length > 0) {
								formData.append('productImg' + index,
										$('.detail-img')[index].files[0]);
							}
						});
				formData.append('productStr', JSON.stringify(product));
				var verifyCodeActual = $('#j_captcha').val();
				if (!verifyCodeActual) {
					$.toast('请输入验证码！');
					return;
				}
				formData.append("verifyCode", verifyCodeActual);
				$.ajax({
					url : productPostUrl,
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功！');
							$('#captcha_img').click();
						} else {
							$.toast('提交失败！');
							$('#captcha_img').click();
						}
					}
				});
			});

});