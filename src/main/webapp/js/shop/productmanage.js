$(function() {
	//var shopId = 1;
	
	var shopId = getQueryString('shopId');
	//var isEdit = shopId ? true : false;
	
	var listUrl = '/shop/product/getlist?shopId='+ shopId;
	var deleteUrl = '/shop/product/modifyproductid';
	getList();
	function getList() {
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var productList = data.productList;
				var tempHtml = '';
				productList.map(function(item, index) {
					var textOp = "下架";
					var contraryStatus = 0;
					if (item.productEnableStatus == 0) {
						textOp = "上架";
						contraryStatus = 1;
					} /*else {
						contraryStatus = 0;
					}*/
					tempHtml += '' + '<div class="row row-product">'
							+ '<div class="col-33">'
							+ item.productName
							+ '</div>'
							+ '<div class="col-33">'
							+ item.productPriority
							+ '</div>'
							+ '<div class="col-33">'
							+ '<a href="#" class="edit" data-id="'
							+ item.productId
							+ '" data-status="'
							+ item.productEnableStatus
							+ '">编辑</a>'
							+ '<a href="#" class="delete" data-id="'
							+ item.productId
							+ '" data-status="'
							+ contraryStatus
							+ '">'
							+ textOp
							+ '</a>'
							+ '<a href="#" class="preview" data-id="'
							+ item.productId
							+ '" data-status="'
							+ item.productEnableStatus
							+ '">预览</a>'
							+ '</div>'
							+ '</div>';
				});
				$('.product-wrap').html(tempHtml);
			}
		});
	}

	

	function deleteItem(id, enableStatus) {
		var product = {};
		product.productId = id;
		product.productEnableStatus = enableStatus;
		$.confirm('确定么?', function() {
			$.ajax({
				url : deleteUrl,
				type : 'POST',
				data : {
					productStr : JSON.stringify(product),
					//productId = id,
					//productEnableStatus = enableStatus,
					statusChange : true
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.toast('操作成功！');
						getList();
					} else {
						$.toast('操作失败！');
					}
				}
			});
		});
	}

	$('.product-wrap')
			.on(
					'click',
					'a',
					function(e) {
						var target = $(e.currentTarget);
						if (target.hasClass('edit')) {
							window.location.href = '/shop/product/productoperator?productId='
									+ e.currentTarget.dataset.id;
						} else if (target.hasClass('delete')) { 
							deleteItem(e.currentTarget.dataset.id,
									e.currentTarget.dataset.status);
						} else if (target.hasClass('preview')) {
							/*window.location.href = '/myo2o/frontend/productdetail?productId='
									+ e.currentTarget.dataset.id;*/
						}
					});
	
	$('#new').click(function() {
		window.location.href = '/shop/product/productoperator';
	});
});