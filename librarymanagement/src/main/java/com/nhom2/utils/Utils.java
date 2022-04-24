/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.utils;

import javafx.scene.control.Alert;

/**
 *
 * @author Phan Thi Dieu Hien
 */
public class Utils {
    public static Alert getBox(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        return alert;
    }

    public static String removeWhitespace(String txt){
        return txt.trim().replaceAll(" +", " ");        
    }

    private Utils() {
    }

    

}