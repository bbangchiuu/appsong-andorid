<?php
	require "connect_db_appmusic.php";
	
	class Baihat{
		function Baihat($idBaiHat, $tenBaiHat, $hinhBaiHat, $caSi, $linkBaiHat, $luotThich){
				$this->idBaiHat = $idBaiHat;
				$this->tenBaiHat = $tenBaiHat;
				$this->hinhBaiHat = $hinhBaiHat;
				$this->caSi = $caSi;
				$this->linkBaiHat = $linkBaiHat;
				$this->luotThich = $luotThich;
		}
	}
	
	$arraycasi = array();
	$query = "SELECT * FROM baihat ORDER BY LuotThich DESC LIMIT 5";
	$data = mysqli_query($con, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arraycasi, new Baihat($row['IdBaiHat'], $row['TenBaiHat'], $row['HinhBaiHat'], $row['Casi'], $row['LinkBaiHat'], $row['LuotThich']));
	}
	
	echo json_encode($arraycasi);
?>