<?php

	require "config.php";
	
	if(isset($_GET['username']))	
	    $username = $_GET['username'];
	else
	    $username = null;
	if(isset($_GET['password']))
        $password = $_GET['password'];
    else
        $password = null;
    if(isset($_GET['email']))
        $email = $_GET['email'];
    else
         $email = null;
    $sql_select = "SELECT * FROM users WHERE username = '$username'";
    $result = mysqli_query($connection,$sql_select);

    if(mysqli_num_rows($result) != null) {
		echo trim("registered before");
	} else {
			$sql_insert = "INSERT INTO users(username,password,email)
			VALUES('$username','$password','$email');" ;
			if(mysqli_query($connection,$sql_insert)){
				$sql_insert = "INSERT INTO status(online,chat)
			    VALUES('0','');" ;
			    if(mysqli_query($connection,$sql_insert))
				    echo "";
			}
		    
		}
    
    
    mysqli_close($connection);
	?>