/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;


import com.nhom2.pojo.Department;
import com.nhom2.pojo.Reader;
import com.nhom2.services.management.DepartmentLoad;
import com.nhom2.services.management.ReaderModify;
import com.nhom2.utils.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
//import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ManagementReaderController implements Initializable {
    @FXML private TableView<Reader> tbReaders;
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;
    @FXML private TextField txtUsername;
    @FXML private DatePicker dtpBirthDate;
    @FXML private DatePicker dtpActivationDate;
    @FXML private ComboBox<String> cbSex;
    @FXML private ComboBox<String> cbObject;
    @FXML private ComboBox<Department> cbDepartment;
    @FXML private Label lbMess;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        resetReader();
        DayLimit();
        
        initCbObject();
        initCbSex();
        
        this.loadTableViewReader();
        try {
            this.loadTableDataReader(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DepartmentLoad d = new DepartmentLoad();
        try {
            this.cbDepartment.setItems(FXCollections.observableList(d.getDepartment()));
            this.cbDepartment.setValue(d.getDepartmentID(1));
            loadTableDataReader(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementReaderController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void loadTableViewReader(){
        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory<>("reader_id"));
        colId.setPrefWidth(20);

        TableColumn colName = new TableColumn("Họ và tên");
        colName.setCellValueFactory(new PropertyValueFactory<>("reader_name"));
        colName.setPrefWidth(200);

        TableColumn colBirthDate = new TableColumn("Ngày sinh");
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        colBirthDate.setPrefWidth(100);

        TableColumn colSex = new TableColumn("Giới tính");
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colSex.setPrefWidth(50);

        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(200);

        TableColumn colAddress = new TableColumn("Địa chỉ");
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setPrefWidth(135);

        TableColumn colPhone = new TableColumn("SĐT");
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colPhone.setPrefWidth(100);

        TableColumn colObject = new TableColumn("Đối tượng");
        colObject.setCellValueFactory(new PropertyValueFactory<>("object"));
        colObject.setPrefWidth(120);

        TableColumn colDepartment = new TableColumn("Bộ phận");
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department_id"));
        colDepartment.setPrefWidth(90);
        
        TableColumn colUsername = new TableColumn("Tên tài khoản");
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUsername.setPrefWidth(90);
        
        TableColumn colActivation = new TableColumn("Ngày kích hoạt thẻ");
        colActivation.setCellValueFactory(new PropertyValueFactory<>("activation_date"));
        colActivation.setPrefWidth(90);

        this.tbReaders.getColumns().addAll(colId, colName, colBirthDate, colSex, 
                                    colEmail, colAddress, colPhone, colObject,
                                    colDepartment, colUsername, colActivation);
    }

    private void loadTableDataReader(String reader_name) throws SQLException{
        //Reader
        ReaderModify r = new ReaderModify();
        this.tbReaders.setItems(FXCollections.observableList(r.getReader(reader_name)));
    }
    
    public void searchReaderHandler(ActionEvent event){
        try {
            this.loadTableDataReader(this.txtName.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ManagementReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void resetReaderHandler(ActionEvent event) {
        resetReader();
    }
    
    @FXML
    private void handleClickTableViewReader(MouseEvent click) throws SQLException{
        Reader r = tbReaders.getSelectionModel().getSelectedItem();
        if (r!=null) {
            txtName.setText(r.getReader_name());
            txtUsername.setText(r.getUsername());
            cbSex.setValue(r.getSex());
            
            dtpBirthDate.setValue(LocalDate.of(Integer.parseInt((r.getDate_of_birth().substring(6, 10))),
                    Integer.parseInt(r.getDate_of_birth().substring(3, 5)),
                    Integer.parseInt(r.getDate_of_birth().substring(0, 2))));
            
            txtEmail.setText(r.getEmail());
            txtAddress.setText(r.getAddress());
            txtPhone.setText(r.getPhone());
            cbObject.setValue(r.getObject());
            
            int department_id = r.getDepartment_id();
            DepartmentLoad deLoad = new DepartmentLoad();
            Department depart = deLoad.getDepartmentID(r.getDepartment_id());
            this.cbDepartment.setValue(depart);
            
            dtpActivationDate.setValue(LocalDate.of(Integer.parseInt((r.getActivation_date().substring(6, 10))),
                    Integer.parseInt(r.getActivation_date().substring(3, 5)),
                    Integer.parseInt(r.getActivation_date().substring(0, 2))));
        }
    }
    
    private int getDepartmentID(){
        Department depart = this.cbDepartment.getSelectionModel().getSelectedItem();
        int department_id = depart.getDepartment_id();
        return department_id;
    }
    
    public void editReaderHandler(ActionEvent event) throws SQLException, ParseException {
        Reader r = this.tbReaders.getSelectionModel().getSelectedItem();
        if (r != null){
            try{
                SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd");
                
                int reader_id = r.getReader_id();
                String reader_name = Utils.removeWhitespace(this.txtName.getText());
                String username = Utils.removeWhitespace(this.txtUsername.getText());
                String sex = Utils.removeWhitespace(this.cbSex.getValue());

                String ngay1 = this.dtpBirthDate.getValue().toString();
                Date ngaySinh = f.parse(ngay1);
                java.sql.Date date_of_birth = new java.sql.Date(ngaySinh.getTime());

                String email = Utils.removeWhitespace(this.txtEmail.getText());
                String address = Utils.removeWhitespace(this.txtAddress.getText());
                String phone = Utils.removeWhitespace(this.txtPhone.getText());
                String object = Utils.removeWhitespace(this.cbObject.getValue());
                
                String ngay2 = this.dtpActivationDate.getValue().toString();
                Date ngayKHT = f.parse(ngay2);
                java.sql.Date activation_date = new java.sql.Date(ngayKHT.getTime());

                if (reader_name == null || reader_name.equals(""))
                    lbMess.setText("Giá trị tên sách bắt buộc nhập!!");
                else if (username == null || username.equals(""))
                    lbMess.setText("Giá trị tên tài khoản bắt buộc nhập!!");
                else if (email == null || email.equals(""))
                    lbMess.setText("Giá trị email bắt buộc nhập!!");

                else {
                    int department_id = getDepartmentID();

                    ReaderModify rm = new ReaderModify();
                    rm.UpdateReader(reader_id, reader_name, username, sex, date_of_birth, email, address, phone, object, department_id, activation_date);
                    loadTableDataReader(null);
                    Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                    resetReader();
                }
            }catch (NumberFormatException ex2){
                    lbMess.setText("Bạn phải điền đủ các cột dữ liệu!!!");
            }
            
        }
        else
            lbMess.setText("Chưa chọn đối tượng để sửa!!!");
    }
    
    public void deleteReaderHandler(ActionEvent event) throws SQLException {
        Reader r = this.tbReaders.getSelectionModel().getSelectedItem();
  
        if (r != null){
            int reader_id = r.getReader_id();
            
            ReaderModify bm = new ReaderModify();
            bm.DeleteReader(reader_id);
            loadTableDataReader(null);
            Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
            resetReader();
        }
        else
            lbMess.setText("Chưa chọn đối tượng để xoá!!!");
    }
    
    private void initCbObject() {
        this.cbObject.getItems().add("Sinh viên");
        this.cbObject.getItems().add("Giảng viên");
        this.cbObject.getItems().add("Viên chức");
    }
    
    private void initCbSex() {
        this.cbSex.getItems().add("Nam");
        this.cbSex.getItems().add("Nữ");
        this.cbSex.getItems().add("Khác");
    }
    
    private void resetReader(){
        this.lbMess.setText("");
        this.txtName.setText("");
        this.txtEmail.setText("");
        this.txtAddress.setText("");
        this.txtPhone.setText("");
        this.dtpBirthDate.setValue(LocalDate.now());
        this.dtpActivationDate.setValue(LocalDate.now());
        this.txtUsername.setText("");
        this.cbObject.setValue("Sinh viên");
        this.cbSex.setValue("Nam");
    }
    
    
        private class MaxDateCell extends DateCell {

        private ObjectProperty<LocalDate> date;

        private MaxDateCell(ObjectProperty<LocalDate> date) {
            this.date = date;
        }

        @Override
        public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            if (item.isAfter(date.get())) {
                this.setDisable(true);
                setStyle("-fx-background-color: #7e7e7e;"); // I used a different coloring to see which are disabled.
            }
        }

    }
    
    private void DayLimit(){
        this.dtpBirthDate.setDayCellFactory(cf -> {
            DatePicker dayNow = new DatePicker();
            String date = LocalDate.now().toString();
            int d = 0, m = 0, y = 0;
            dayNow.setValue(Utils.getPreviousDay(date, d, m, y));
            dtpBirthDate.setValue(Utils.getPreviousDay(date, d, m, y));
            return new MaxDateCell(dayNow.valueProperty());
        });
    }
}

    
    
