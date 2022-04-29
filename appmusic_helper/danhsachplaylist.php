<?php
	require "connect_db_appmusic.php";
	$query = "SELECT * FROM playlist";
	$data = mysqli_query($con, $query);
	
	class Danhsachplaylist{
		function Danhsachplaylist($IdPlayList, $Ten, $Hinhnen, $Hinhicon){
			$this->IdPlayList = $IdPlayList;
			$this->Ten = $Ten;
			$this->Hinhnen = $Hinhnen;
			$this->Hinhicon = $Hinhicon;
		}
	}
	
	$mangplaylist = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangplaylist, new Danhsachplaylist($row['IdPlayList'], $row['Ten'], $row['Hinhnen'], $row['Hinhicon']));
	}
	
	echo json_encode($mangplaylist);
?>