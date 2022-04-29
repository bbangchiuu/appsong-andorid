<?php 
	require "connect_db_appmusic.php";
	$query = "SELECT quangcao.*, baihat.IdBaiHat, baihat.HinhBaiHat, baihat.TenBaiHat FROM quangcao INNER JOIN baihat ON quangcao.Idbaihat = baihat.IdBaiHat";
	$data = mysqli_query($con, $query);
	
	class Quangcao{
		function Quangcao($IdQuangCao, $Hinhanh, $Noidung, $Idbaihat, $HinhBaiHat, $TenBaiHat){
			$this->IdQuangCao = $IdQuangCao;
			$this->Hinhanh = $Hinhanh;
			$this->Noidung = $Noidung;
			$this->Idbaihat = $Idbaihat;
			$this->HinhBaiHat = $HinhBaiHat;
			$this->TenBaiHat = $TenBaiHat;
		}
	}
	
	$mangquangcao = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangquangcao, new Quangcao($row['Id'], $row['Hinhanh'], $row['Noidung'], $row['Idbaihat'], $row['HinhBaiHat'], $row['TenBaiHat']));
	}
	
	echo json_encode($mangquangcao);
?>