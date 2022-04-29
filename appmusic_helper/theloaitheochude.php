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
	
	$idChuDe = $_POST['idChuDe'];
	$arraytheloai = array();
	$querytheloai = "SELECT * FROM theloai WHERE IdChuDe = '$idChuDe'";
	$datatheloai = mysqli_query($con, $querytheloai);
	while($row = mysqli_fetch_assoc($datatheloai)){
		array_push($arraytheloai, new TheLoai($row['IdTheLoai'], $row['IdChuDe'], $row['TenTheLoai'], $row['HinhTheLoai']));
	}
	echo json_encode($arraytheloai);
?>