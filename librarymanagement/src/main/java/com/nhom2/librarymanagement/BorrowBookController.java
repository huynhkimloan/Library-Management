package com.nhom2.librarymanagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.nhom2.pojo.BorrowBook;
import com.nhom2.services.ReturnBorowService;
import com.nhom2.utils.JdbcUtils;
import com.nhom2.utils.Utils;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BorrowBookController implements Initializable {

    @FXML
    private TextField txtKw;
    @FXML
    private TableView<BorrowBook> tbData;
    @FXML
    private TextField txtCard_id;
    @FXML
    private TextField txtBook_id;
    @FXML
    private ComboBox cbAmount;
    @FXML
    private TextField txtBookName;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAmount;
    
    Statement stm = null;
    ResultSet rs = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadColumns();
        this.loadTableData(null);
        ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        cbAmount.setItems(list);
        txtKw.textProperty().addListener((evt) -> {
                this.loadTableData(txtKw.getText());
        });
        setCellValue();
    } 
    
    private void loadTableData(String kw) {
        ReturnBorowService s = new ReturnBorowService();
        try {
            this.tbData.setItems(FXCollections.observableList(s.getBorrow(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadColumns() {
        TableColumn col1 = new TableColumn("MÃ MƯỢN");
        col1.setCellValueFactory(new PropertyValueFactory("borrow_id"));
        
        TableColumn col2 = new TableColumn("MÃ THẺ");
        col2.setCellValueFactory(new PropertyValueFactory("card_id"));
        
        TableColumn col3 = new TableColumn("MÃ SÁCH");
        col3.setCellValueFactory(new PropertyValueFactory("book_id"));
        
        TableColumn col4 = new TableColumn("NGÀY MƯỢN");
        col4.setCellValueFactory(new PropertyValueFactory("start_date"));
        
        TableColumn col5 = new TableColumn("SỐ LƯỢNG");
        col5.setCellValueFactory(new PropertyValueFactory("amount"));
        
        TableColumn col6 = new TableColumn("TRẠNG THÁI");
        col6.setCellValueFactory(new PropertyValueFactory("status"));
       
        this.tbData.getColumns().addAll(col1, col2, col3, col4, col5, col6);
    }
    
    private void setCellValue() {
        ReturnBorowService r = new ReturnBorowService();
        tbData.setOnMouseClicked(e -> {
            BorrowBook ls = tbData.getItems().get(tbData.getSelectionModel().getSelectedIndex());
            txtCard_id.setText(String.valueOf(ls.getCard_id()));
            txtBook_id.setText(String.valueOf(ls.getBook_id()));
            cbAmount.setValue(ls.getAmount());
            try {
                txtBookName.setText(r.getBookName(txtBook_id.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(BorrowBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                txtName.setText(r.getReaderName(txtCard_id.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(BorrowBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }

    private void clean() {
        txtBook_id.setText(null);
        txtCard_id.setText(null);
        cbAmount.setValue(null);
        txtBookName.setText(null);
        txtName.setText(null);
        txtKw.setText(null);
        txtAmount.setText(null);
    }
    
    private void nullSomeThing() {
        txtBook_id.setText(null);
        cbAmount.setValue(null);
        txtBookName.setText(null);
    }
    
    @FXML
    private void refreshTable() {
        loadTableData(null);
        clean();
    }
    
    private void isActive(String kw) throws SQLException, ParseException{
        ReturnBorowService r = new ReturnBorowService();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long m = System.currentTimeMillis();
        Date d = new Date(m);
        String s = simpleDateFormat.format(d);
        
        if(r.checkCardId(txtKw.getText()).isEmpty()){
            Utils.getBox("Không tồn tại thẻ", Alert.AlertType.WARNING).show();
            refreshTable();
        }
        else {
            if(ReturnBorowService.daysDiff(s, r.getStatus(kw))>0){
                Utils.getBox("Thẻ vẫn còn hạn sử dụng", Alert.AlertType.INFORMATION).show();
                txtCard_id.setText(txtKw.getText());
                txtName.setText(r.getReaderName(txtKw.getText()));
                nullSomeThing();
                loadTableData(null);
            }
            else {
                    Utils.getBox("Thẻ đã hết hạn => Không được mượn sách", Alert.AlertType.ERROR).show();
                    refreshTable();
            }
        } 
    }
    
    
    @FXML
    private void checkExpiry(MouseEvent event) throws SQLException, ParseException {
        isActive((txtKw.getText()));
    }  

    @FXML
    private void showBookName(KeyEvent event) throws SQLException {
        if(event.getCode() == KeyCode.ENTER) {
            ReturnBorowService r = new ReturnBorowService();
            if(r.getBookName(txtBook_id.getText()).equals(""))
                Utils.getBox("Không tồn tại mã sách này", Alert.AlertType.ERROR).show();
            else {
                txtBookName.setText(r.getBookName(txtBook_id.getText()));
                txtAmount.setText(r.getAmountBook(txtBook_id.getText()));
            }
                
            
        }
    }
    
    
    @FXML
    private void addBorrowBook(MouseEvent event) throws SQLException, ParseException {
        ReturnBorowService r = new ReturnBorowService();
        if(txtBook_id.getText().isBlank() || txtBookName.getText().isBlank()||
                txtName.getText().isBlank()|| txtCard_id.getText().isBlank()||
                cbAmount.getSelectionModel().getSelectedItem().toString().isEmpty()){
            Utils.getBox("Vui lòng điền đủ mọi thông tin", Alert.AlertType.WARNING).show();  
        } 
        else {
            LocalDate l = LocalDate.now();
            java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(l.toString());
            java.sql.Date st = new java.sql.Date(d.getTime());
            
            String query = "INSERT INTO borrowbook (start_date, amount, card_id, "
                + "book_id) VALUES (?, ?, ?, ?);";
            try (Connection conn = JdbcUtils.getConn()){
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setDate(1, st);
                stm.setString(2, cbAmount.getSelectionModel().getSelectedItem().toString());
                stm.setString(3, txtCard_id.getText());
                stm.setString(4, txtBook_id.getText());
                stm.executeUpdate();

                Utils.getBox("Đã thêm phiếu mượn thành công!", Alert.AlertType.INFORMATION).show();
                
                int result = ReturnBorowService.amountRemain(cbAmount.getSelectionModel().getSelectedItem().toString(), 
                        txtAmount.getText());
                r.updateAmountBook(result, txtBook_id.getText());
                refreshTable();
                
            }
        
        }
        
    }
    
}
