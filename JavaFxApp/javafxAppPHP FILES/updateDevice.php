<?php

    require_once './DBCOnnect.php';
    $table = $_POST['table'];
    $state = $_POST['state'];
    $id = $_POST['id'];
    
    $sql = "UPDATE " . "". $table ."" . " SET state = " . "" . $state ."" . " WHERE id = " . "" . $id ."";
    if(mysqli_query($conn, $sql)){
        //echo "Records inserted successfully.";
        $response["success"] = 1;
        $response["message"] = "Records inserted successfully.";
        echo json_encode($response);
    } else{
        //echo " ERROR: Could not able to execute $sql. " . mysqli_error($conn);
        $response["success"] = 0;
        $response["message"] =  "ERROR: Could not able to execute $sql. " . mysqli_error($conn);
        echo json_encode($response);
    }