/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.services.Statistical;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Phan Thi Dieu Hien
 */
public class StatisticalController implements Initializable {
    @FXML private Label lbPay;
    @FXML private Label lbBorrow;
    @FXML private TableView tbChuaTraSach;
    @FXML private TextField tfNam; 
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTBWChuaTraSach();
    }    
    
    public void muonSachTheoNamHandler(ActionEvent event) {
//        String a ="Tổng số lượng sách được mượn trong năm là " ;
        Statistical s = new Statistical();     
        if(tfNam.getText() != null)
            try {
                this.lbBorrow.setText(s.sachMuonTheoNam(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
            
    }
    
    public void muonSachTheoQuy1Handler(ActionEvent event) throws SQLException {
        //String a ="Tổng số lượng sách được mượn trong quý 1 là " ;
        Statistical s = new Statistical();
//        if(tfNam.getText() == null || s == null)
//            lbBorrow.setText("Qúy 1 độc giả chưa mượn sách");           
        if(tfNam.getText() != null)
            try {
                this.lbBorrow.setText(s.sachMuonTheoQuy1(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
            
    }
    
    public void muonSachTheoQuy2Handler(ActionEvent event) throws SQLException {
        //String a ="Tổng số lượng sách được mượn trong quý 2 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbBorrow.setText(s.sachMuonTheoQuy2(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void muonSachTheoQuy3Handler(ActionEvent event) {
        //String a ="Tổng số lượng sách được mượn trong quý 3 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbBorrow.setText(s.sachMuonTheoQuy3(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void muonSachTheoQuy4Handler(ActionEvent event) {
        //String a ="Tổng số lượng sách được mượn trong quý 4 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbBorrow.setText(s.sachMuonTheoQuy4(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void traSachTheoQuy1Handler(ActionEvent event) {
        //String a ="Tổng số lượng sách được trả trong quý 1 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbPay.setText(s.sachTraTheoQuy1(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void traSachTheoQuy2Handler(ActionEvent event) {
        //String a ="Tổng số lượng sách được trả trong quý 2 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)   
            try {
                    this.lbPay.setText(s.sachTraTheoQuy2(tfNam.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
                }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void traSachTheoQuy3Handler(ActionEvent event) {
        //String a ="Tổng số lượng sách được trả trong quý 3 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbPay.setText(s.sachTraTheoQuy3(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void traSachTheoQuy4Handler(ActionEvent event) {
        //String a ="Tổng số lượng sách được trả trong quý 4 là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbPay.setText(s.sachTraTheoQuy4(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    public void traSachTheoNamHandler(ActionEvent event) {
        //String a ="Tổng số lượng sách được trả trong năm là " ;
        Statistical s = new Statistical();
        if(tfNam.getText() != null)
            try {
                this.lbPay.setText(s.sachTraTheoNam(tfNam.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            lbBorrow.setText("Bạn cần phải nhập năm !!!");
    }
    
    
    public void banDocChuaTraSachHandler(ActionEvent event) {
        this.loadDataTBWChuaTraSach();
    }
     
    private void loadTBWChuaTraSach() {
        TableColumn colName = new TableColumn("Tên");
        colName.setCellValueFactory(new PropertyValueFactory<> ("reader_name"));
        colName.setPrefWidth(250);
        
        TableColumn colSex = new TableColumn("Giới tính");
        colSex.setCellValueFactory(new PropertyValueFactory<> ("sex"));
        colSex.setPrefWidth(100);
        
        TableColumn colDOB = new TableColumn("Ngày Sinh");
        colDOB.setCellValueFactory(new PropertyValueFactory<> ("date_of_birth"));
        colDOB.setPrefWidth(150);
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<> ("email"));
        colEmail.setPrefWidth(250);
        

        this.tbChuaTraSach.getColumns().addAll(colName, colSex, colDOB, colEmail);
    }
    private void loadDataTBWChuaTraSach() {
        Statistical s = new Statistical();
        try {
            this.tbChuaTraSach.setItems(FXCollections.observableList(s.chuaTraSach()));
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void reset1Handler(ActionEvent event) {
         resetBorrowPay();
    }
     
    private void resetBorrowPay(){
        this.lbBorrow.setText("");
        this.lbPay.setText("");
        this.tfNam.setText("");
       // this.tbChuaTraSach.setValue(LocalDate.now());
    }
}
