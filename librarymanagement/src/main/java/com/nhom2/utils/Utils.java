/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.control.Alert;

/**
 *
 * @author LENOVO
 */
public class Utils {
    public static Alert getBox (String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setContentText(content);
        
        return alert;
    }    
    
    public static String removeWhitespace(String txt){
        return txt.trim().replaceAll(" +", " ");        
    }
    
    public static boolean isValidDate(String dateStr, String dateFormat){
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    

}
