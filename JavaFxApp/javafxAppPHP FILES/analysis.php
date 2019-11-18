<?php
    
    require_once './DBCOnnect.php';
    $query_hardware = "SELECT * FROM hardware_fix";
    $query_software = "SELECT * FROM software_fix";
    $query_printers = "SELECT * FROM printer_fix";
    
    //1 = done
    $query_hardware_done = "SELECT * FROM hardware_fix WHERE state = 1";
    $query_software_done = "SELECT * FROM software_fix WHERE state = 1";
    $query_printer_done = "SELECT * FROM printer_fix WHERE state = 1";
    
    //2 = not repaired
    $query_hardware_not_repaired = "SELECT * FROM hardware_fix WHERE state = 2";
    $query_software_not_repaired = "SELECT * FROM software_fix WHERE state = 2";
    $query_printer_not_repaired = "SELECT * FROM printer_fix WHERE state = 2";
    
    //3 = submitted
    $query_hardware_submitted = "SELECT * FROM hardware_fix WHERE state = 0";
    $query_software_submitted = "SELECT * FROM software_fix WHERE state = 0";
    $query_printer_submitted = "SELECT * FROM printer_fix WHERE state = 0";
    
    $response["analysis"] = array();
    $analysis = array();
    
    if($result = mysqli_query($conn, $query_hardware)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_hardware"] = $rowcount;
        $response["success"] = 1;
    }  else {
        $response["success"] = 0;
    }
    
    if($result = mysqli_query($conn, $query_software)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_software"] = $rowcount;
        $response["success"] = 1;
    }  else {
        $response["success"] = 0;
    }
    
    if($result = mysqli_query($conn, $query_printers)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_printer"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }
    
    /*---------------------------------------DONE-----------------------------------------*/
    
    if($result = mysqli_query($conn, $query_hardware_done)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_hardware_done"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }


    if($result = mysqli_query($conn, $query_software_done)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_software_done"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }


    if($result = mysqli_query($conn, $query_printer_done)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_printer_done"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }

 /*--------------------------------------NOT REPAIRED-----------------------------------------*/
    
    
    
    if($result = mysqli_query($conn, $query_hardware_not_repaired)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_hardware_not"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }


    if($result = mysqli_query($conn, $query_software_not_repaired)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_software_not"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }


    if($result = mysqli_query($conn, $query_printer_not_repaired)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_printer_not"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }    
    
 /*--------------------------------------SUBMITTED-----------------------------------------*/
    
    if($result = mysqli_query($conn, $query_hardware_submitted)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_hardware_submitted"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }


    if($result = mysqli_query($conn, $query_software_submitted)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_software_submitted"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }


    if($result = mysqli_query($conn, $query_printer_submitted)){
        
        $rowcount = mysqli_num_rows($result);
        $analysis["num_printer_submitted"] = $rowcount;
        $response["success"] = 1;

    }  else {
        $response["success"] = 0;
    }    
    
    
    array_push($response["analysis"], $analysis);
    echo json_encode($response);