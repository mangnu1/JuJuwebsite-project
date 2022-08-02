/**
 * 
 */



$(function(){
	var apiURI="https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=4ae87fceb9085c429f5c847b4bb11727"
	$.ajax({
		url: apiURI,
		dataType: "json",
		success: function(result){
			console.log(result);
			console.log("절대온도:"+result.main.temp);
			console.log("섭씨온도:"+parseInt(result.main.temp-273.15));
			console.log("날씨아이콘 이름:"+result.weather[0].icon);
			
			var imgURL = "http://openweathermap.org/img/w/" + result.weather[0].icon + ".png";
			$("#w_icon").css("background-image", 'url('+imgURL+')');
			$("#temp").text(parseInt(result.main.temp-273.15));
			
		}
	});
});

