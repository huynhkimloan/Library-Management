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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    
    @FXML
    private void pageManagementBook (ActionEvent event) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ManagementBook.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           Logger.getLogger(ManagementBookController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    @FXML
    private void pageManagementReader (ActionEvent event) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ManagementReader.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           Logger.getLogger(ManagementReaderController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    @FXML
    private void btnExit (ActionEvent event) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signin_up.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           Logger.getLogger(Signin_upController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
}
