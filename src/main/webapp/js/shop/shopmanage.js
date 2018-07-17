$(function(){

	var shopId=getQueryString("shopId");
	
	if(!shopId){
		window.location.href = 	'/shop/shop/shoplist';
	}
	
	
	$("#shopInfo").click(function(){
		window.location.href = 	'/shop/shop/shopoperator?shopId=' + shopId;
		/*window.location.href = '/shop/shop/shopoperator';*/
	});
	
	$("#shopmanage").click(function(){
		/*window.location.href = 	'/shop/product/productmanage?shopId=' + shopId;*/
		window.location.href = 	'/shop/product/productmanage';
	});
	
	
});




