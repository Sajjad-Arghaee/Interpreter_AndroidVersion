<?php

	require "config.php";
    $myID = $_GET['myID'];
	
	$result1 = mysqli_query($connection,"SELECT * FROM status where online='1' and id!='$myID'");
    $result2 = mysqli_query($connection,"SELECT * FROM status where online='$myID'");
	if(mysqli_num_rows($result2) != null) {
		$row = mysqli_fetch_array($result2);
		$opponentID = $row[2];
		echo trim($opponentID);
		mysqli_query($connection,"UPDATE status SET online='$opponentID' WHERE id='$myID'");
	}else if(mysqli_num_rows($result1) != null) {
		$row = mysqli_fetch_array($result1);
		$opponentID = $row[2];
		echo trim($opponentID);
		mysqli_query($connection,"UPDATE status SET online='$opponentID' WHERE id='$myID'");
	}
	else
		echo trim("wait");
	
    mysqli_close($connection);
	?>