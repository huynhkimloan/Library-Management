/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaymentController implements Initializable {

    @FXML
    private Text txtName;
    @FXML
    private Text txtStartDate;
    @FXML
    private Text txtEndDate;
    @FXML
    private TextField txtBorrowId;
    @FXML
    private TextField txtDays;
    @FXML
    private TextField txtTotalMoney;
    @FXML
    private TextField txtFineDays;
    @FXML
    private TextField txtNote;
    @FXML
    private TextField txtGive;
    @FXML
    private TextField txtReturn;
    
    Statement stm = null;
    ResultSet rs = null;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        txtBorrowId.setText(ReturnBookController.getId());
        fineMoney();
    } 
    
    public void fineMoney(){
        String query = "SELECT r.reader_name, b.* FROM borrowbook b, reader r, "
                + "card c WHERE b.card_id = c.card_id AND r.reader_id = c.card_id "
                + "AND status = 1 AND c.card_id = '"+txtBorrowId.getText()+"'";
        try (Connection conn = JdbcUtils.getConn()){
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                txtName.setText(rs.getString("reader_name"));
                txtStartDate.setText(rs.getDate("start_date").toString());
                txtEndDate.setText(rs.getDate("end_date").toString());
                txtDays.setText(String.valueOf(ReturnBorowService.daysDiff(rs.getDate
                    ("start_date").toString(),rs.getDate("end_date").toString())));
                txtFineDays.setText(String.valueOf(ReturnBorowService.daysDiff(rs.getDate
                    ("start_date").toString(),rs.getDate("end_date").toString())-30));
                txtTotalMoney.setText(String.valueOf(String.format("%.3f",rs.getFloat("fine"))));
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    @FXML
    public void pay(MouseEvent event) {
        String query = "UPDATE borrowbook SET note = '"+txtNote.getText()+"',"
              + "status = 0 WHERE card_id = '"+txtBorrowId.getText()+"'";
        
        if(txtGive.getText().equals("")) {
            Utils.getBox("Vui lòng nhập tiền thu", Alert.AlertType.WARNING).show();
        }
        else {
            try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement ps= conn.prepareStatement(query);
            ps.executeUpdate();
            Utils.getBox("Đã thanh toán thành công", Alert.AlertType.INFORMATION).show();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            } catch(Exception ex) {
                Utils.getBox("Đã có lỗi xảy ra", Alert.AlertType.WARNING).show();
            }
        }
    } 
    
    @FXML
    private void charge(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            float result = Math.abs(Float.parseFloat(txtGive.getText()) - 
                Float.parseFloat(txtTotalMoney.getText()));
            if(result<=0) {
                txtReturn.setText("0");
            }
            else {
            txtReturn.setText(String.format("%.3f", result));
            }
        }   
    }

    @FXML
    private void pressCardId(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            fineMoney();
        }
    }


}
