/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LibrarianHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pageBorrow(MouseEvent event) {
        try {
            FXMLLoader p = new FXMLLoader(App.class.getResource("BorrowBook.fxml"));
            Scene sc = new Scene(p.load());
            Stage s = new Stage();
            s.setScene(sc);
            s.initStyle(StageStyle.UTILITY);
            s.show();
        } catch (IOException ex) {
           Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    @FXML
    private void pageReturn(MouseEvent event) {
        try {
            FXMLLoader p = new FXMLLoader(App.class.getResource("ReturnBook.fxml"));
            Scene sc = new Scene(p.load());
            Stage s = new Stage();
            s.setScene(sc);
            s.initStyle(StageStyle.UTILITY);
            s.show();
        } catch (IOException ex) {
           Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
}
