/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.Card;
import com.nhom2.pojo.Department;
import com.nhom2.pojo.Reader;
import com.nhom2.services.DepartmentServices;
import com.nhom2.services.ReaderServices;
import com.nhom2.utils.AlertUtils;
import com.nhom2.utils.EmailFormatUtils;
import com.nhom2.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.input.KeyEvent;
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
    

    //làm mới lại các giá trị 
    public static void init_Null(String name, String email, String phone, 
             String add, String username, String pass1, String pass2){
        name = null;
        email = null;
        phone = null;
        add = null;
        username = null;
        pass1 = null;
        pass2 = null;
    }

    
    //button đăng ký
    String r_email, r_add, r_phone, r_username, r_pass, r_pass2, r_name;
    public void btnSignUp(ActionEvent event) throws ParseException, SQLException, IOException {
        try{
            //object
            //department
            DepartmentServices d = new DepartmentServices();
            int r_dedepartment = d.getDepartmentID(department.getValue().toString());
            //name
            r_name = Utils.removeWhitespace(this.name.getText());
            //birthday
            String r_date_of_birth = this.date_of_birth.getValue().toString();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date day = f.parse(r_date_of_birth);
            java.sql.Date birthdate = new java.sql.Date(day.getTime());
            //sex
            //email
            r_email = this.email.getText();
            //phone
            r_phone = this.phone.getText();
            //address
            r_add = Utils.removeWhitespace(this.address.getText());
            //username
            r_username = this.username.getText();
            //password
            r_pass = this.pass.getText();
            r_pass2 = this.pass2.getText();
                  
            ReaderServices rd = new ReaderServices(); 
            EmailFormatUtils e = new EmailFormatUtils();
            if (r_name.equals("")|| r_pass.equals("") || r_pass2.equals("") ||
                    r_username.equals("") || r_email.equals("") || r_phone.equals("") ||
                    r_add.equals("") || r_date_of_birth.equals("") || object.getValue().equals("") ||
                    department.getValue().equals("") || sex.getValue().equals(""))
            {
                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);
                AlertUtils.showAlert("Phải nhập đầy đủ các trường dữ liệu!", Alert.AlertType.ERROR);
            }
            else if (rd.check_PhoneNumber(r_phone)==false)
            {
                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);
                AlertUtils.showAlert("Định dạng số điện thoại không hợp lệ", Alert.AlertType.ERROR);
            }
            else if (e.validate_Email(r_email)==false)
            {
                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);
                AlertUtils.showAlert("Định dạng email không hợp lệ", Alert.AlertType.ERROR);
            }
            else if (rd.checkPass_less6character(r_pass))
            {
                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);
                AlertUtils.showAlert("Mật khẩu phải trên 6 ký tự!", Alert.AlertType.ERROR);
            }
            else if (rd.checkPass_Similar(r_pass, r_pass2)==false)
            {
                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);
                AlertUtils.showAlert("Mật khẩu xác nhận không chính xác!", Alert.AlertType.ERROR);
            }
            else if (rd.username_exists(r_username))
            {
                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);
                AlertUtils.showAlert("Tên đăng nhập đã tồn tại!", Alert.AlertType.ERROR);
            }
            else {
                Reader reader = new Reader(r_name, r_username, r_pass, 
                        r_email, true, r_dedepartment, sex.getValue(), 
                        r_phone, r_add, object.getValue(), "USER", birthdate); 
                rd.SignUp_Account(reader);
                
                
                //lấy mã độc giả
                ReaderServices re_service = new ReaderServices();
                int id_card = re_service.getReaderID(r_username);
                
                //khai báo các cột
                LocalDate date1 = LocalDate.now();
                LocalDate date2 = date1.plusYears(1);
                
                String d1 = date1.toString();
                DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                Date day_1 = f1.parse(d1);
                java.sql.Date dd1 = new java.sql.Date(day_1.getTime());
                
                String d2 = date2.toString();
                Date day_2 = f1.parse(d2);
                java.sql.Date dd2 = new java.sql.Date(day_2.getTime());
                float penalty = 0;
                
                Card card = new Card(id_card, true, dd1, dd2, penalty);
                rd.create_Card(card);
                
                AlertUtils.showAlert("Đăng ký tài khoản thành công!", Alert.AlertType.INFORMATION);
//                SignUpController.init_Null(r_name, r_email, r_phone, r_add, r_username, r_pass, r_pass2);

                //chuyển scene
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
    
    
    //không cho nhập chữ
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.phone.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            phone.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    
    
    //button quay lại
    public void btn_Return(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Signin_up.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
