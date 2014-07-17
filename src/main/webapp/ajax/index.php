<?php
	if( isset($_POST["login"]) ) {
		$login = $_POST["login"];
	} else {
		$login = "";
	}


	if( isset($_POST["pass"]) )  {
			$pass = $_POST["pass"];
		} else {
			$pass = "";
		}
	
	if ( $login == "admin" && $pass == "1234" ) {
		$a['status'] = true;
	} else {
		$a['status'] = false;
	}
	echo json_encode($a);
