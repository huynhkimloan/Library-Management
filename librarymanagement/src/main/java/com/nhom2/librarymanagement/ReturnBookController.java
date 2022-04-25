/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.BorrowBook;
import com.nhom2.services.ReturnBorowService;
import com.nhom2.utils.JdbcUtils;
import com.nhom2.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReturnBookController implements Initializable {
    @FXML
    private TableView<BorrowBook> tbData;
    @FXML
    private TextField txtBorrow_id;
    @FXML
    private DatePicker dpkStart_date;
    @FXML
    private DatePicker dpkEnd_date;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtCard_id;
    @FXML
    private TextField txtBook_id;
    @FXML
    private TextField txtFine;
    @FXML
    private TextField txtStatus;
    @FXML
    private TextField txtKw;
    
    
    private static String id;
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
        txtKw.textProperty().addListener((evt) -> {
                this.loadTableData(txtKw.getText());
        });
        this.refreshTable();
        this.setCellValue();
       
    }
        
    private void loadTableData(String kw) {
        ReturnBorowService s = new ReturnBorowService();
        try {
            this.tbData.setItems(FXCollections.observableList(s.getReturn(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadColumns() {
        TableColumn col1 = new TableColumn("MÃ MƯỢN");
        col1.setCellValueFactory(new PropertyValueFactory("borrow_id"));
        
        TableColumn col2 = new TableColumn("NGÀY MƯỢN");
        col2.setCellValueFactory(new PropertyValueFactory("start_date"));
        
        TableColumn col4 = new TableColumn("NGÀY TRẢ");
        col4.setCellValueFactory(new PropertyValueFactory("amount"));
        
        TableColumn col5 = new TableColumn("MÃ THẺ");
        col5.setCellValueFactory(new PropertyValueFactory("card_id"));
        
        TableColumn col6 = new TableColumn("MÃ SÁCH");
        col6.setCellValueFactory(new PropertyValueFactory("book_id"));
        
        TableColumn col7 = new TableColumn("TIỀN PHẠT");
        col7.setCellValueFactory(new PropertyValueFactory("fine"));
        
        TableColumn col8 = new TableColumn("TRẠNG THÁI");
        col8.setCellValueFactory(new PropertyValueFactory("status"));
       
        this.tbData.getColumns().addAll(col1, col2, col4, col5, col6, col7, col8);
    }
    
    private void setCellValue() {
        tbData.setOnMouseClicked(e -> {
            dpkEnd_date.setValue(null);
            BorrowBook ls = tbData.getItems().get(tbData.getSelectionModel().getSelectedIndex());
            txtBorrow_id.setText(String.valueOf(ls.getBorrow_id()));
            dpkStart_date.setValue(LocalDate.of(Integer.parseInt((ls.getStart_date().substring(6, 10))),
                    Integer.parseInt(ls.getStart_date().substring(3, 5)), 
                    Integer.parseInt(ls.getStart_date().substring(0, 2))));
            txtAmount.setText(String.valueOf(ls.getAmount()));
            txtCard_id.setText(String.valueOf(ls.getCard_id()));
            txtBook_id.setText(String.valueOf(ls.getBook_id()));
            if(ls.getFine()<=0) {
                txtFine.setText("0");
            }
            else {
                txtFine.setText(String.format("%.3f",ls.getFine()));
            }
            txtStatus.setText(String.valueOf(ls.getStatus()));
        });
        
    }
    
    private void clean() {
        txtBorrow_id.setText(null);
        txtBook_id.setText(null);
        txtCard_id.setText(null);
        txtAmount.setText(null);
        txtFine.setText(null);
        txtStatus.setText(null);
        dpkStart_date.setValue(null);
        dpkEnd_date.setValue(null);
        txtKw.setText(null);
    }
    
    @FXML
    private void refreshTable() {
        clean();
        loadTableData(null);
    }
    
    @FXML
    private void restore() throws ParseException{
        id = txtBorrow_id.getText();
        int fees = (int) (ReturnBorowService.daysDiff(dpkStart_date.getValue().toString(),
                dpkEnd_date.getValue().toString()));
        if (fees < 0) {
            Utils.getBox("Vui lòng chọn ngày hợp lệ", Alert.AlertType.WARNING).show();
        } else {
            if (fees >= 0 && fees <= 30) {
                txtFine.setText("0.0");
                String query = "UPDATE borrowbook SET end_date = '" + dpkEnd_date.getValue() + "',"
                        + "status = 0, fine = '" + txtFine.getText() + "' WHERE borrow_id = '" + txtBorrow_id.getText() + "'";
                try ( Connection conn = JdbcUtils.getConn()) {
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.execute();
                    Utils.getBox("Đã trả sách thành công", Alert.AlertType.INFORMATION).show();
                } catch (Exception ex) {
                    Utils.getBox("Đã có lỗi xảy ra", Alert.AlertType.WARNING).show();
                }
                refreshTable();
            } else {
                txtFine.setText(String.format("%.3f", (float) (fees - 30) * 5.000));
                String query = "UPDATE borrowbook SET end_date = '" + dpkEnd_date.getValue() + "',"
                        + "fine = '" + txtFine.getText() + "' WHERE borrow_id = '" + txtBorrow_id.getText() + "'";
                try ( Connection conn = JdbcUtils.getConn()) {
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.execute();
                    Utils.getBox("Cần phải thanh toán vì trả sách quá hạn", Alert.AlertType.WARNING).show();
                } catch (Exception ex) {
                    Utils.getBox("Đã có lỗi xảy ra", Alert.AlertType.WARNING).show();
                }
            }
        }
    }
    
    @FXML
    private void addView(MouseEvent event) {
        try {
            FXMLLoader p = new FXMLLoader(App.class.getResource("Payment.fxml"));
            Scene sc = new Scene(p.load());
            Stage s = new Stage();
            s.setScene(sc);
            s.initStyle(StageStyle.UTILITY);
            s.show();
        } catch (IOException ex) {
           Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
     
    /**
     * @return the id
     */
    public static String getId() {
        return id;
    }

    
    
}
