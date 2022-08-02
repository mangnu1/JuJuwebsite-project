/**
 * 
 */

$(function() {
	
	$.get("/common/items", function(listHtmlData){
			$("#items-list>.wrap").html(listHtmlData);
	});
	
});