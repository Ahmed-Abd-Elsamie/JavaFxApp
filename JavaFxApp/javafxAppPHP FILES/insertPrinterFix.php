<?php

    
    
// check for required fields
if (isset($_POST['device_num'])&& isset($_POST['customer_name']) && isset($_POST['phone_num']) && isset($_POST['prob'])
        && isset($_POST['fill_printer']) && isset($_POST['change_dram']) && isset($_POST['check_all'])
        && isset($_POST['cable_power']) && isset($_POST['cable_data'])
        && isset($_POST['cd']) && isset($_POST['other']) && isset($_POST['date_time'])
        && isset($_POST['device_name']) && isset($_POST['state'])) {

    $device_num = $_POST['device_num'];
    $customer_name = $_POST['customer_name'];
    $phone_number = $_POST['phone_num'];;
    $prob = $_POST['prob'];
    $fill_printer = $_POST['fill_printer'];
    $change_dram = $_POST['change_dram'];
    $check_all = $_POST['check_all'];
    $cable_power = $_POST['cable_power'];
    $cable_data = $_POST['cable_data'];
    $cd = $_POST['cd'];
    $other = $_POST['other'];
    $date_time = $_POST['date_time'];
    $device_name = $_POST['device_name'];
    $state = $_POST['state'];

    
    require_once './DBCOnnect.php';

    // connecting to db
    
            
    
    $sql = "INSERT INTO printer_fix (device_num, customer_name, phone_num, prob, fill_printer, change_dram, check_all, cable_power, cable_data, cd, other, date_time, device_name, state) " . "VALUES ("
            . "". $device_num .","
            . "'$customer_name',"
            . "". $phone_number .","
            . "'$prob',"
            . "". $fill_printer .","
            . "". $change_dram .","
            . "". $check_all .","
            . "". $cable_power .","
            . "". $cable_data .","
            . "". $cd .","
            . "'$other',"
            . "' $date_time ',"
            . "'$device_name',"
            . "". $state .");";
     
     
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
    
    // check if row inserted or not
$response["success"] = 1;
echo json_encode($response);
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}