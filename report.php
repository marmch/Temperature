<!Doctype html>
<html>
<body>
<center>
<h2>
Temperature Report
</h2>
<applet code="WeatherGraph.class" archive="weather.jar, mysql-connector-java-5.0.8-bin.jar, jfreechart-1.0.17.jar, jdatepicker.jar, jcommon-1.0.21.jar" width="1000" height="700">

<?php
$con=mysqli_connect("sql301.byethost7.com","b7_14226855","mm3612","b7_14226855_weather");

if (mysqli_connect_errno()){
	echo "Failed to connect to d: " . mysqli_connect_error();
}
else{
	$sql = "SELECT * FROM temp";
	$result = mysqli_query($con,$sql);

	if($result){
		$row = mysqli_fetch_array($result);
		$temps = $row['t'];
		$times = $row['dt'];
		while($row = mysqli_fetch_array($result)){
			$temps .= ";" . $row['t'];
			$times .= ";" . $row['dt'];
		}
	}
	else{
		echo "SQL Error";
	}
echo "<param name=\"temps\" value=\"" . $temps . "\" />";
echo "<param name=\"times\" value=\"" . $times . "\" />";
}
mysqli_close($con);
?>
<param name="cache_option" value="no">
</applet>
</center>
</body>
</html>