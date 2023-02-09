<?php

	require "config.php";
	if(isset($_GET['username']))	
	    $username = $_GET['username'];
	if(isset($_GET['password']))    
        $password = $_GET['password'];
    
    $result = mysqli_query($connection,"SELECT * FROM users where Username='$username' 
       and Password='$password'");
    $row = mysqli_fetch_array($result);
    $data = $row[0];
    if($data){
	   $myID = $row[3];
	   echo trim($myID);
    }
    else{
        echo trim("not found!");
    }
	mysqli_query($connection,"UPDATE status SET online='1' WHERE id='$myID'");
    
    mysqli_close($connection);
	?>