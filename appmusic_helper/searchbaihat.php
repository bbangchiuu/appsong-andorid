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
	
	$mangcakhuc = array();
	if(isset($_POST['tukhoa'])){
		$tukhoa = $_POST['tukhoa'];
		$query = "SELECT * FROM baihat WHERE lower(TenBaiHat) LIKE '%$tukhoa%'";
		$data = mysqli_query($con, $query);
		
		while($row = mysqli_fetch_assoc($data)){
			array_push($mangcakhuc, new Baihat($row['IdBaiHat'], $row['TenBaiHat'], $row['HinhBaiHat'], $row['Casi'], $row['LinkBaiHat'], $row['LuotThich']));
		}
	}
	echo json_encode($mangcakhuc);
?>