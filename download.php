<?php
function get_data($url) {
	$ch = curl_init();
	$timeout = 5;
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
	curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $timeout);
	$data = curl_exec($ch);
	curl_close($ch);
	return $data;
}

$content = "";
while($content==""){
$content = get_data('http://api.openweathermap.org/data/2.5/weather?id=5128581&mode=xml&APPID=7dad8bb980e8ab455223de43913ad10e');
echo "x<br>";
}
$xml = simplexml_load_string($content);
$temp = $xml->temperature->attributes()->value;
echo $temp;
echo "<br>";

$con=mysqli_connect("sql301.byethost7.com","b7_14226855","mm3612","b7_14226855_weather");

if (mysqli_connect_errno()){
	echo "Failed to connect to d: " . mysqli_connect_error();
}
else{
	$mysqltime = date("Y-m-d H:i:s");
	$sql = "INSERT INTO temp VALUES ($temp, '$mysqltime')";
	if (mysqli_query($con,$sql)){
	}
	else{
		echo "SQL Error";
	}
}

mysqli_close($con);
?>		