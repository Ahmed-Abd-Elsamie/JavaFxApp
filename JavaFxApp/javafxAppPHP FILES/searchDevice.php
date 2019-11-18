<?php

    require_once './DBCOnnect.php';
    
    $table = $_POST['table'];
    $word = $_POST['word'] . "%";
    
    $sql = "SELECT * FROM " . "". $table ."" . " WHERE customer_name like" . "'$word'";

    if ($result = mysqli_query($conn, $sql)){
        
        if(mysqli_num_rows($result) > 0){
            
            $response["devices"] = array();
            if($table == "hardware_fix"){
                while ($row = mysqli_fetch_array($result)){
                    $devices = array();
                    $devices["id"] = $row["id"];
                    $devices["device_num"] = $row["device_num"];
                    $devices["customer_name"] = $row["customer_name"];
                    $devices["phone_num"] = $row["phone_num"];
                    $devices["prob"] = $row["prob"];
                    $devices["win7"] = $row["win7"];
                    $devices["win8"] = $row["win8"];
                    $devices["win10"] = $row["win10"];
                    $devices["check_all"] = $row["check_all"];
                    $devices["software"] = $row["software"];
                    $devices["hardware"] = $row["hardware"];
                    $devices["battery"] = $row["battery"];
                    $devices["charger"] = $row["charger"];
                    $devices["bag"] = $row["bag"];
                    $devices["cd"] = $row["cd"];
                    $devices["other"] = $row["other"];
                    $devices["date_time"] = $row["date_time"];
                    $devices["device_name"] = $row["device_name"];
                    $devices["state"] = $row["state"];
                    $devices["price"] = $row["price"];

                    array_push($response["devices"], $devices);
            
                
                }
            }else if($table == "software_fix"){
                while ($row = mysqli_fetch_array($result)){
                    $devices = array();
                    $devices["id"] = $row["id"];
                    $devices["device_num"] = $row["device_num"];
                    $devices["customer_name"] = $row["customer_name"];
                    $devices["phone_num"] = $row["phone_num"];
                    $devices["prob"] = $row["prob"];
                    $devices["win7"] = $row["win7"];
                    $devices["win8"] = $row["win8"];
                    $devices["win10"] = $row["win10"];
                    $devices["check_all"] = $row["check_all"];
                    $devices["software"] = $row["software"];
                    $devices["battery"] = $row["battery"];
                    $devices["charger"] = $row["charger"];
                    $devices["bag"] = $row["bag"];
                    $devices["cd"] = $row["cd"];
                    $devices["other"] = $row["other"];
                    $devices["date_time"] = $row["date_time"];
                    $devices["device_name"] = $row["device_name"];
                    $devices["state"] = $row["state"];
                    $devices["price"] = $row["price"];

                    array_push($response["devices"], $devices);
            
                }
                
            }  else {
                while ($row = mysqli_fetch_array($result)){
                    $devices = array();
                    $devices["id"] = $row["id"];
                    $devices["device_num"] = $row["device_num"];
                    $devices["customer_name"] = $row["customer_name"];
                    $devices["phone_num"] = $row["phone_num"];
                    $devices["prob"] = $row["prob"];
                    $devices["fill_printer"] = $row["fill_printer"];
                    $devices["change_dram"] = $row["change_dram"];
                    $devices["check_all"] = $row["check_all"];
                    $devices["cable_power"] = $row["cable_power"];
                    $devices["cable_data"] = $row["cable_data"];
                    $devices["cd"] = $row["cd"];
                    $devices["other"] = $row["other"];
                    $devices["date_time"] = $row["date_time"];
                    $devices["device_name"] = $row["device_name"];
                    $devices["state"] = $row["state"];
                    $devices["price"] = $row["price"];

                    array_push($response["devices"], $devices);
                
                }
                
            }
            $response["success"] = 1;
            echo json_encode($response);
        } else {
            $response["success"] = 0;
            $response["message"] = "No devices found";  
            echo json_encode($response);
        }
        
    }  else {
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
        // echoing JSON response
        echo json_encode($response);
    }