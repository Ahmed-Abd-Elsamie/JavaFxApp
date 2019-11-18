<?php

    require_once 'DBConfig.php';
    // Create connection
    $conn = new mysqli($DATABASE_URL, $DATABASE_USERNAME, $DATABASE_PASSWORD, $DATABASE_NAME);
    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
        /*$response["success"] = 0;
        $response["message"] = "Connection Error.";
        echo json_encode($response);*/
    }  else {
        /*$response["success"] = 1;
        $response["message"] = "Connected Successfuly";
        echo json_encode($response);*/
        mysqli_set_charset($conn, 'utf8'); 
    }