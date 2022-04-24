/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.utils;

import com.nhom2.pojo.BorrowBook;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author ASUS
 */
public class Utils {
    public static Alert getBox(String msg, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setContentText(msg);
        
        return a;
    }
    
//    public static List<BorrowBook> getListBorrow(){
//        r
//    }
}
