<?php
$url = 'http://192.168.137.136/add.php';

$ch = curl_init();

$data = array(

    'query' => 'SELECT * FROM LIGHT WHERE srno = 100',
    'method' => 'post'

);

curl_setopt($ch, CURLOPT_URL, $url);

curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

curl_setopt($ch, CURLOPT_POST, true);

curl_setopt($ch, CURLOPT_POSTFIELDS, $data);

$output = curl_exec($ch);


if($output==FALSE){
	echo "CURL ERROR" . curl_errno($ch);
}

curl_close($ch);

print_r($output);

?>