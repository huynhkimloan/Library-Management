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
    
    public static int sntd (int m, int y){
	switch (m)
	{
            case 2:
                    if (y % 400 == 0 || y % 4 == 0 && y % 100 != 0)
                        return 29;
                    return 28;
            case 4: case 6: case 9: case 11:
                    return 30;
            default:
                    return 31;
	}
}
    public static LocalDate getPreviousDay(String strDate, int d, int m, int y){
        d = Integer.parseInt(strDate.substring(8));
        m = Integer.parseInt(strDate.substring(5, 7));
        y = Integer.parseInt(strDate.substring(0, 4));
        int sn = Utils.sntd(m, y);
        if (d > sn){
            m++;
            if (m > 12){
                y++;
                m = 1;
            }
            d -= sn;
        }
        LocalDate date = LocalDate.of(y, m, d);
        return date;
    }
    

}
