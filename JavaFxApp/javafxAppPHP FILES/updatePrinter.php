<?php    
    
// check for required fields
if (isset($_POST['id'])&& isset($_POST['customer_name']) && isset($_POST['phone_num']) && isset($_POST['prob'])
        && isset($_POST['fill_printer']) && isset($_POST['change_dram']) && isset($_POST['check_all'])
        && isset($_POST['cable_power']) && isset($_POST['cable_data'])
        && isset($_POST['cd']) && isset($_POST['other'])
        && isset($_POST['device_name']) && isset($_POST['price'])) {

    $id = $_POST['id'];
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
    $device_name = $_POST['device_name'];
    $price = $_POST['price'];

    // connecting to db
    require_once './DBCOnnect.php';
    
    $sql = "UPDATE printer_fix"
            . " SET customer_name = " . "'$customer_name'"
            . ", phone_num = " . "". $phone_number .""
            . ", prob = " . "'$prob'"
            . ", fill_printer = " . "". $fill_printer .""
            . ", change_dram = " . "". $change_dram .""
            . ", check_all = " . "". $check_all .""
            . ", cable_power = " . "". $cable_power .""
            . ", cable_data = " . "". $cable_data .""
            . ", cd = " . "". $cd .""
            . ", other = " . "'$other'"            
            . ", price = " . "'$price'"
            . ", device_name = " . "'$device_name'" . "WHERE id = " . "" . $id ."";
               
     
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