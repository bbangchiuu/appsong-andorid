<?php
	require "connect_db_appmusic.php";

	class ChuDe{
		function ChuDe($IdChuDe, $TenChuDe, $HinhChuDe){
			$this->IdChuDe = $IdChuDe;
			$this->TenChuDe = $TenChuDe;
			$this->HinhChuDe = $HinhChuDe;
		}
	}
	
	$querychude = "SELECT * FROM chude";
	$arraychude = array();
	$datachude = mysqli_query($con, $querychude);
	while($row = mysqli_fetch_assoc($datachude)){
		array_push($arraychude, new ChuDe($row['IdChuDe'], $row['TenChuDe'], $row['HinhChuDe']));
	}
	
	echo json_encode($arraychude);
?>