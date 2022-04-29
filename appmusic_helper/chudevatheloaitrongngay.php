<?php
	require "connect_db_appmusic.php";
	
	class TheLoai{
		function TheLoai($IdTheLoai, $IdKeyChuDe, $TenTheLoai, $HinhTheLoai){
			$this->IdTheLoai = $IdTheLoai;
			$this->IdKeyChuDe = $IdKeyChuDe;
			$this->TenTheLoai = $TenTheLoai;
			$this->HinhTheLoai = $HinhTheLoai;
		}
	}
	
	class ChuDe{
		function ChuDe($IdChuDe, $TenChuDe, $HinhChuDe){
			$this->IdChuDe = $IdChuDe;
			$this->TenChuDe = $TenChuDe;
			$this->HinhChuDe = $HinhChuDe;
		}
	}

	$arraytheloai = array();
	$querytheloai = "SELECT * FROM theloai LIMIT 4";
	$datatheloai = mysqli_query($con, $querytheloai);
	while($row = mysqli_fetch_assoc($datatheloai)){
		array_push($arraytheloai, new TheLoai($row['IdTheLoai'], $row['IdChuDe'], $row['TenTheLoai'], $row['HinhTheLoai']));
	}
	
	$arraychude = array(); 
	$querychude = "SELECT * FROM chude LIMIT 4";
	$datachude = mysqli_query($con, $querychude);
	while($row = mysqli_fetch_assoc($datachude)){
		array_push($arraychude, new ChuDe($row['IdChuDe'], $row['TenChuDe'], $row['HinhChuDe']));
	}
	
	$arraychudetheloai = array('TheLoai' => $arraytheloai, 'ChuDe' => $arraychude);
	
	echo json_encode($arraychudetheloai);
?>