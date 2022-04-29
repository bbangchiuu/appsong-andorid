<?php
	require "connect_db_appmusic.php";
	
	$query = "SELECT DISTINCT * FROM playlist ORDER BY rand(" . date("Ymd") . ") LIMT 3";
	
	class PlayListToday{
		function PlayListToday($IdPlayList, $Ten, $Hinhnen, $Hinhicon){
			$this->IdPlayList = $IdPlayList;
			$this->Ten = $Ten;
			$this->Hinhnen = $Hinhnen;
			$this->Hinhicon = $Hinhicon;
		}
	}
	
	$arrayplaylistfortoday = array();
	$data = mysqli_query($con, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrayplaylistfortoday, new PlayListToday($row['IdPlayList'], $row['Ten'], $row['Hinhnen'], $row['Hinhicon']));
	}
	
	echo json_encode($arrayplaylistfortoday);
?>