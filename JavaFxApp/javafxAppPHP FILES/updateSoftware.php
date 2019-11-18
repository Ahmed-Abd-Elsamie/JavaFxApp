<?php    
    
// check for required fields
if (isset($_POST['id'])&& isset($_POST['customer_name']) && isset($_POST['phone_num']) && isset($_POST['prob'])
        && isset($_POST['win7']) && isset($_POST['win8']) && isset($_POST['win10']) && isset($_POST['check_all'])
        && isset($_POST['software']) && isset($_POST['battery']) && isset($_POST['charger'])
        && isset($_POST['bag']) && isset($_POST['cd']) && isset($_POST['other'])
        && isset($_POST['device_name']) && isset($_POST['price'])) {

    $id = $_POST['id'];
    $customer_name = $_POST['customer_name'];
    $phone_number = $_POST['phone_num'];;
    $prob = $_POST['prob'];
    $win7 = $_POST['win7'];
    $win8 = $_POST['win8'];
    $win10 = $_POST['win10'];
    $check_all = $_POST['check_all'];
    $software = $_POST['software'];
    $battery = $_POST['battery'];
    $charger = $_POST['charger'];
    $bag = $_POST['bag'];
    $cd = $_POST['cd'];
    $other = $_POST['other'];
    $device_name = $_POST['device_name'];
    $price = $_POST['price'];

    // connecting to db
    require_once './DBCOnnect.php';
    
    $sql = "UPDATE software_fix"
            . " SET customer_name = " . "'$customer_name'"
            . ", phone_num = " . "". $phone_number .""
            . ", prob = " . "'$prob'"
            . ", win7 = " . "". $win7 .""
            . ", win8 = " . "". $win8 .""
            . ", win10 = " . "". $win10 .""
            . ", check_all = " . "". $check_all .""
            . ", software = " . "". $software .""
            . ", battery = " . "". $battery .""
            . ", charger = " . "". $charger .""
            . ", bag = " . "". $bag .""
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