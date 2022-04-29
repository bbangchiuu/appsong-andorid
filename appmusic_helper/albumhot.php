<?php 
	require "connect_db_appmusic.php";
	
	$query = "SELECT * FROM album LIMIT 4";
	$dataalbum = mysqli_query($con, $query);
	
	class Album{
		function Album($idAlbum, $tenAlbum, $tenCaSiAlbum, $hinhAlbum){
			$this->idAlbum = $idAlbum;
			$this->tenAlbum = $tenAlbum;
			$this->tenCaSiAlbum = $tenCaSiAlbum;
			$this->hinhAlbum = $hinhAlbum;
		}
	}
	
	$arrayalbum = array();
	while($row = mysqli_fetch_assoc($dataalbum)){
		array_push($arrayalbum, new Album($row['IdAlbum'], $row['TenAlbum'], $row['TenCaSiAlbum'], $row['HinhAlbum']));
	}
	
	echo json_encode($arrayalbum);
?>