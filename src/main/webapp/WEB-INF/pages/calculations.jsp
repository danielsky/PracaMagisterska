<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script src="js/charts/Chart.js"></script>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script>
		$(document).ready(function(){
		  	var ctx = document.getElementById("myChart").getContext("2d");

			var data = ${dataset};

			var myLineChart = new Chart(ctx).Line(data, {animation: false});
			
			var interval = setInterval(function(){makeAjax()}, 1000);
				

		}); 
		
		function makeAjax(){
			$.ajax({
				type: "POST",
				url: "update.do",
				data: { identifier: "${identifier}" }
				})
				.done(function(msg) {
					$("#updates").html("<span>"+msg+"</span>");
			});
		}
	</script>

	</head>
	<body>
	<div>	
		<div id="titlediv">
			<span>Symulator wymiennika ciepła typu rura w rurze</span>
		</div>
		<div id="chartdiv">
			<canvas id="myChart" width="1000" height="400"></canvas>
		</div>
		<div id="updates">
		
		</div>
		
	</div>
	</body>
</html>