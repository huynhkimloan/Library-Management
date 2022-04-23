/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.utils;

/**
 *
 * @author phamt
 */
import javafx.scene.control.Alert;

public class AlertUtils {
    public static void showAlert(String message, Alert.AlertType alertType) {
        new Alert(alertType, message).show();
    }
}
