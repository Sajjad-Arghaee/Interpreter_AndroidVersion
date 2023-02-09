<?php

	require "config.php";
    $myID = $_GET['myID'];
	$opponentID = $_GET['opponentID'];
	$received = $_GET['received'];

    if($received == 1)
        mysqli_query($connection,"UPDATE status SET chat='' WHERE id='$opponentID'");
    $result = mysqli_query($connection,"SELECT * FROM status where id='$myID'");
    $row = mysqli_fetch_array($result);
    $data = $row[0];
	if($data == 0)
		echo trim($data);
	else{
		$result = mysqli_query($connection,"SELECT * FROM users where id='$opponentID'");
		$row = mysqli_fetch_array($result);
		$username = $row[0];
        
        $result = mysqli_query($connection,"SELECT * FROM status where id='$opponentID'");
		$row = mysqli_fetch_array($result);
		$data = $row[1];
		
		if($data != null){
			echo trim($username . " : " . $data);
		}
	}
    
    mysqli_close($connection);
	?>