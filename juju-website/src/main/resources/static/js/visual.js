/**
 * 작성자: 이주현
 */
 
$(function(){
	$.ajax({
		url:"/common/visuals",
		success:function(result){
			$("#visual>.wrap").html(result);
			
		}
	});
});