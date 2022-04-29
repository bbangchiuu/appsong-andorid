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
	$arraydanhsachbaihat = array();
	$query = "";
	
	if(isset($_POST['idAlbum'])){
		$idAlbum = $_POST['idAlbum'];
		$query = "SELECT * FROM baihat WHERE IdAlbum = '$idAlbum'";
	}
	
	if(isset($_POST['idTheLoai'])){
		$idTheLoai = $_POST['idTheLoai'];
		$query = "SELECT * FROM baihat WHERE IdTheLoai = '$idTheLoai'";
	}
	
	if(isset($_POST['idPlayList'])){
		$idPlayList = $_POST['idPlayList'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idPlayList', IdPlayList);";
	}
	
	if(isset($_POST['idquangcao'])){
		$idquangcao = $_POST['idquangcao'];
		
		//$queryquangcao = "SELECT * FROM quangcao WHERE Id = '$idquangcao'";
		//$dataquangcao = mysqli_query($con, $queryquangcao);
		//$rowquangcao = mysqli_fetch_assoc($dataquangcao);
		//$id = $rowquangcao['Idbaihat'];
		//$query = "SELECT * FROM baihat WHERE Idbaihat = '$id'";
		
		$query = "SELECT * FROM baihat WHERE Idbaihat IN (SELECT Idbaihat FROM quangcao WHERE Id = '$idquangcao');";
	}
	$data = mysqli_query($con, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arraydanhsachbaihat, new Baihat($row['IdBaiHat'], $row['TenBaiHat'], $row['HinhBaiHat'], $row['Casi'], $row['LinkBaiHat'], $row['LuotThich']));
	}
	
	echo json_encode($arraydanhsachbaihat);
?>