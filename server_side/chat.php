<?php

	require "config.php";
    $myID = $_GET['myID'];
	$chat = $_GET['chat'];
	
    mysqli_query($connection,"UPDATE status SET chat='$chat' WHERE id='$myID'");
    
    mysqli_close($connection);
	?>