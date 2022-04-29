<?php
	require "connect_db_appmusic.php";
	
	if(isset($_POST['luotthich']) && isset($_POST['idBaiHat'])){
		$luotthich = $_POST['luotthich'];
		$idBaiHat = $_POST['idBaiHat'];
		
		$query = "SELECT LuotThich FROM baihat WHERE IdBaiHat = '$idBaiHat'";
		$dataluothich = mysqli_query($con, $query);
		$row = mysqli_fetch_assoc($dataluothich);
		$tongluotthich = $row['LuotThich'];
		
		$tongluotthich += $luotthich;
		$querysum = "UPDATE baihat SET LuotThich = '$tongluotthich' WHERE IdBaiHat = '$idBaiHat'";
		$dataupdate = mysqli_query($con, $querysum);
		if($dataupdate){
			echo "Success";
		}else{
			echo "Fail";
		}
	}else{
		echo "Fail";
	}
	
?>