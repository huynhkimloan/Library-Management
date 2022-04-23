/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.Department;
import com.nhom2.pojo.Reader;
import com.nhom2.services.DepartmentServices;
import com.nhom2.services.ReaderServices;
import com.nhom2.utils.AlertUtils;
import com.nhom2.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author phamt
 */
public class SignUpController implements Initializable {
    @FXML private ComboBox<String> object;
    @FXML private ComboBox<Department> department;
    @FXML private ComboBox<String> sex;
    @FXML private TextField name;
    @FXML private DatePicker date_of_birth;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private TextField username;
    @FXML private TextField pass;
    @FXML private TextField pass2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBoxObject();
        initComboBoxSex();
        
        DepartmentServices d = new DepartmentServices();
        try {
            this.department.setItems(FXCollections.observableArrayList(d.getDepartment()));
        } catch (SQLException ex) {
            Logger.getLogger(Signin_upController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void initComboBoxObject() {
        this.object.getItems().add("Sinh viên");
        this.object.getItems().add("Giảng viên");
        this.object.getItems().add("Viên chức");
//        this.object.setValue("Sinh viên");
    }
    
    private void initComboBoxSex() {
        this.sex.getItems().add("Nam");
        this.sex.getItems().add("Nữ");
        this.sex.getItems().add("Khác");
//        this.sex.setValue("Nam");
    }
    
    public void btnSignUp(ActionEvent event) throws ParseException, SQLException, IOException {
        try{
            //object
            //department
            DepartmentServices d = new DepartmentServices();
            int r_dedepartment = d.getDepartmentID(department.getValue().toString());
            //name
            String r_name = Utils.remove_Whitespace(this.name.getText());
            //birthday
            String r_date_of_birth = this.date_of_birth.getValue().toString();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date day = f.parse(r_date_of_birth);
            java.sql.Date birthdate = new java.sql.Date(day.getTime());
            //sex
            //email
            String r_email = this.email.getText();
            //phone
            String r_phone = this.phone.getText();
            //address
            String r_add = Utils.remove_Whitespace(this.address.getText());
            //username
            String r_username = this.username.getText();
            //password
            String r_pass = this.pass.getText();
            String r_pass2 = this.pass2.getText();
                  
            ReaderServices rd = new ReaderServices(); 
            if (rd.username_exists(r_username))
                AlertUtils.showAlert("Tên đăng nhập đã tồn tại!", Alert.AlertType.ERROR);
            else if (rd.checkPass_less6character(r_pass))
                AlertUtils.showAlert("Mật khẩu phải trên 6 ký tự!", Alert.AlertType.ERROR);
            else if (rd.checkPass_Similar(r_pass, r_pass2)==false)
                AlertUtils.showAlert("Mật khẩu xác nhận không chính xác!", Alert.AlertType.ERROR);
            else {
                Reader reader = new Reader(r_name, r_username, r_pass, 
                        r_email, true, r_dedepartment, sex.getValue(), 
                        r_phone, r_add, object.getValue(), "USER", birthdate); 
                rd.SignUp_Account(reader);
                AlertUtils.showAlert("Đăng ký tài khoản thành công!", Alert.AlertType.INFORMATION);
                Parent root = FXMLLoader.load(getClass().getResource("Signin_up.fxml"));
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch (NullPointerException ex){
            AlertUtils.showAlert("Phải nhập đầy đủ các trường dữ liệu!", Alert.AlertType.WARNING);
        }
    }
    
    
    public void btn_Return(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Signin_up.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}