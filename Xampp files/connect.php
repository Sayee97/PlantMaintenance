<?php

	function Connection(){
		$server="127.0.0.1";
		$user="root";
		$pass="";
		$db="plantmaintenance";

		$conn =  mysqli_connect($server, $user, $pass, $db);

		if (!$conn) {
	    	die('MySQL ERROR: ' . mysql_error());
		}



	return $conn;
	}
?>
