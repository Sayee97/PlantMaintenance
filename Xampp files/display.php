<?php 

	/*
	* Created by Belal Khan
	* website: www.simplifiedcoding.net 
	* Retrieve Data From MySQL Database in Android
	*/
	
	//database constants
	include("connect.php");
    $conn=Connection();

	
	//creating a query
	$stmt = $conn->prepare("SELECT id, quantity FROM inventory;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id, $quantity);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['quantity'] = $quantity; 
		
		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);