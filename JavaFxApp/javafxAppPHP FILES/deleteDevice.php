<?php

    require_once './DBCOnnect.php';
    $table = $_POST['table'];
    $id = $_POST['id'];
    
    $sql = "DELETE FROM " . "". $table ."" . " WHERE id = " . "" . $id ."";
    if(mysqli_query($conn, $sql)){
        //echo "Records inserted successfully.";
        $response["success"] = 1;
        $response["message"] = "Deleted successfully.";
        echo json_encode($response);
    } else{
        //echo " ERROR: Could not able to execute $sql. " . mysqli_error($conn);
        $response["success"] = 0;
        $response["message"] =  "ERROR: Could not able to execute $sql. " . mysqli_error($conn);
        echo json_encode($response);
    }