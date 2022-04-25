/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.nhom2.services.ReaderServices;
import com.nhom2.utils.AlertUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author phamt
 */
public class Signin_upController implements Initializable {
    @FXML private ComboBox<String> user_role;
    @FXML private TextField username;
    @FXML private TextField password;
//    public static int readerID = 0;
//    public static int adminID = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox();

//        ReaderServices s = new ReaderServices();
//        try {
//            this.user_role.setItems(FXCollections.observableArrayList(s.getReader()));
//        } catch (SQLException ex) {
//            Logger.getLogger(Signin_upController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }    
    
     private void initComboBox() {
        this.user_role.getItems().add("ADMIN");
        this.user_role.getItems().add("USER");
        this.user_role.setValue("USER");
    }
    
    @FXML
    private void btnSignUp (ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
     
    String role;
    @FXML
    public void btnSignIn(ActionEvent event) throws SQLException, IOException {
        role = this.user_role.getValue();   
        String user = this.username.getText();
        String pass = this.password.getText();
        
        if (this.user_role.getValue().equals("")) {
            AlertUtils.showAlert("Chọn đối tượng đăng nhập!", Alert.AlertType.WARNING);
            role = null;
        }
        else if (user.equals("") && pass.equals("")) {
            AlertUtils.showAlert("Phải nhập tài khoản và mật khẩu!", Alert.AlertType.WARNING);
            this.username.setStyle("-fx-border-color: lightskyblue;");
            this.password.setStyle("-fx-border-color: lightskyblue;");
            role = null;
        }
        else if (user.equals("")) {
            AlertUtils.showAlert("Phải nhập tài khoản!", Alert.AlertType.WARNING);
            this.username.setStyle("-fx-border-color: lightskyblue;");
            this.password.setStyle("-fx-border-color: none;");
            role = null;
        }
        else if (pass.equals("")) {
            AlertUtils.showAlert("Phải nhập mật khẩu!", Alert.AlertType.WARNING);
            this.username.setStyle("-fx-border-color: none;");
            this.password.setStyle("-fx-border-color: lightskyblue;");
            role = null; 
        }
        else if (role.equals("USER")) {   
            ReaderServices rd = new ReaderServices();
            boolean kt = rd.KiemTraDangNhapUSER(user, pass);
            if (kt){
//                readerID = rd.getReaderID(this.username.getText());
//                adminID = 0;
                //qua scene ...
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                AlertUtils.showAlert("Tên tài khoản hoặc mật khẩu không chính xác!", Alert.AlertType.ERROR);
                this.username.setStyle("-fx-border-color: red;");
                this.password.setStyle("-fx-border-color: red;");
                role = "";
            }
        }
        else if (role.equals("ADMIN")){
            ReaderServices rd = new ReaderServices();
            boolean kt = rd.KiemTraDangNhapADMIN(user, pass);
            if (kt){
//                readerID = rd.getReaderID(this.username.getText());
//                adminID = 0;
                //qua scene Quản lý 
                //chỗ này t để sang 1 trong 2 cái Management không được nên để tạm primary
                Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                AlertUtils.showAlert("Tên tài khoản hoặc mật khẩu không chính xác!", Alert.AlertType.ERROR);
                this.username.setStyle("-fx-border-color: red;");
                this.password.setStyle("-fx-border-color: red;");
                role = "";
            }
        }
    }  
    
}
