<?php

	require "config.php";
		
	$myID = $_GET['myID'];
	$opponentID = $_GET['opponentID'];
    mysqli_query($connection,"UPDATE status SET online='0' WHERE id='$myID'");
	mysqli_query($connection,"UPDATE status SET online='0' WHERE id='$opponentID'");
    echo trim("byebye");
    mysqli_close($connection);
	?>