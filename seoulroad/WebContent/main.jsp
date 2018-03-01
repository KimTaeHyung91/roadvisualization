<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>603Lab.</title>
<style>
#map {
	height: 50%;
	width: 100%;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
<!--  AIzaSyBG2ah7IMczlsr0B7MZvTKPggnUB7HKMxI-->

<script type="text/javascript">
	var map;
	var poly1;
	var poly2;
	var poly3;
	var currentlatlng;
	var marker;

	function initMap() {
		var mylatlng = {
			lat : 37.566721,
			lng : 126.978405
		};
		map = new google.maps.Map(document.getElementById('map'), {
			center : mylatlng,
			zoom : 13,
			mapTypeId : 'roadmap'
		});

	}

	//ajax 를 통신하기위한 준비함수 --> "확인"버튼 이벤트를 발생시 실행되는 함수.
	$(document).ready(function() {

		$('#submit').click(function() {

			//selectBox에 value를 값을 얻는 함수.
			var selectbox = $('#sig').val();

			//ajax함수 시작.
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/index",//index로 맵핑.
				data : {
					selectbox : selectbox//selectbox의 값을 전달함.
				},
				dataType : 'json',//받을 data를 json으로 정함.
				success : function(data) {

					//success는 json data가 넘어오면 실행되는 함수.
					for (var i = 0; i < data.Node.length; i++) {

						var nodelatlng = [];
						var slat = data.Node[i].startXY.x;
						var slng = data.Node[i].startXY.y;
						var elat = data.Node[i].endXY.x;
						var elng = data.Node[i].endXY.y;
						nodelatlng.push({
							lat : slat,
							lng : slng
						}, {
							lat : elat,
							lng : elng
						});
					
					
	
						//OpenAPI를 통한 평균속도를 받아서 처리.
						if(data.Node[i].avgSpeed != 0){
							
							if(data.Node[i].avgSpeed <= 15){
						

								 poly1 = new google.maps.Polyline({
										path : nodelatlng,
										strokeColor : '#FF0000',
										strokeOpacity : 1.0,
										strokeWeight : 2
									});
									poly1.setMap(map);
							}else if(15 <= data.Node[i].avgSpeed && data.Node[i].avgSpeed <=30){

								 poly2 = new google.maps.Polyline({
										path : nodelatlng,
										strokeColor : '#FFFF00',
										strokeOpacity : 1.0,
										strokeWeight : 2
									});
									poly2.setMap(map);
							}else if(30 <= data.Node[i].avgSpeed){

								 poly3 = new google.maps.Polyline({
										path : nodelatlng,
										strokeColor : '#00FF00',
										strokeOpacity : 1.0,
										strokeWeight : 2
									});
									poly3.setMap(map);
							}
						
						}
					}
						
					
				},
				complete : function(data) {
					alert("mas");
					
					
					
				}

			});

		});
	});
</script>
</head>
<body>

	<center>
		<h1>서울시 실시간 도로 소통 정보</h1>
	</center>
	<br>
	<center>
		<form>
			<select name="sig" id="sig" style="width: 300px; height: 30px">
				<option selected="selected">행정구역을 선택하세요
					<!-- 한강이남  -->
				<option value="100">종로구
				<option value="101">중구
				<option value="102">용산구
				<option value="103">성동구
				<option value="104">광진구
				<option value="105">동대문구
				<option value="106">중랑구
				<option value="107">성북구
				<option value="108">강북구
				<option value="109">도봉구
				<option value="110">노원구
				<option value="111">은평구
				<option value="112">서대문구
				<option value="113">마포구
				<option value="114">양천구
				<option value="115">강서구
				<option value="116">구로구
				<option value="117">금천구
				<option value="118">영등포구
				<option value="119">동작구
				<option value="120">관악구
				<option value="121">서초구
				<option value="122">강남구
				<option value="123">송파구
				<option value="124">강동구
			</select> <input type="button" id="submit" value="확인">
			<button onclick="window.location.reload()">초기화</button>
			<br> <br> <br>


		</form>
	</center>
	<div id="map"></div>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDk_32hZbBreLjgQN7qxkcuVFzwjXvMzSo&callback=initMap"
		async defer></script>
</body>
</html>