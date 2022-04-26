/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.ReserveBook;
import com.nhom2.services.ReserveDetails;
import com.nhom2.services.ReturnBorowService;
import com.nhom2.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Phan Thi Dieu Hien
 */
public class ReserveDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML private TableView<ReserveBook> tbReserve;
     @FXML private TextField txtNhapCardID;
     @FXML private TextField txtNgayHetHan;
     private Label lBThongBao;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadTableViewReserveDetails(this.txtNhapCardID.getText());
        ReserveDetails a = new ReserveDetails();
        
         try {
             tbReserve.setItems(FXCollections.observableList(a.getReserve_By_cardID1()));
         } catch (SQLException ex) {
             Logger.getLogger(ReserveDetailsController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
        
        //tìm kiếm cardid
        this.txtNhapCardID.textProperty().addListener((evt) -> {
           
            try {
                tbReserve.setItems(FXCollections.observableList(a.getReserve_By_cardID(txtNhapCardID.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(ReserveDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        setCellValue();
        
    }   
   
    private void LoadTableViewReserveDetails(String text){
        TableColumn colReserveID = new TableColumn("Mã phiếu đặt");
        //định nghĩa cách lấy dữ liệu cho ô
        colReserveID.setCellValueFactory(new PropertyValueFactory("reserve_id"));
        colReserveID.setPrefWidth(100);
        
        TableColumn colNgayDat = new TableColumn("Ngày đặt sách");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("activation_date"));
        colNgayDat.setPrefWidth(100);
        
        TableColumn colNgayHH = new TableColumn("Ngày hết hạn");
        colNgayHH.setCellValueFactory(new PropertyValueFactory("expiration_date"));
        colNgayHH.setPrefWidth(100);
        
        TableColumn colSL = new TableColumn("Số lượng");
        colSL.setCellValueFactory(new PropertyValueFactory("amount"));
        colSL.setPrefWidth(100);
        
        TableColumn colMaSach = new TableColumn("Mã sách");
        colMaSach.setCellValueFactory(new PropertyValueFactory("book_id"));
        colMaSach.setPrefWidth(100);
        
        TableColumn colDocGia = new TableColumn("Mã độc giả");
        colDocGia.setCellValueFactory(new PropertyValueFactory("card_id"));
        colDocGia.setPrefWidth(100);
        
        this.tbReserve.getColumns().addAll(colReserveID, colNgayDat, colNgayHH, colSL, colMaSach, colDocGia);
    }
    
    //hiển thị dữ liệu phiếu đặt lên table reservebook theo cardid
     
     
     private void LoadTableDataReserve_ByCardID(String kw) throws SQLException, ParseException{
        
        ReserveDetails s = new ReserveDetails();
        this.tbReserve.setItems(FXCollections.observableArrayList(s.getReserve_By_cardID(kw)));
     }
     
     @FXML
    private void keyPressed_CardID(KeyEvent e) {
        ReserveDetails s = new ReserveDetails();
    
        if (!"".equals(e.getText())) 
            s.setTextNull1(txtNhapCardID);
        
        //hạn chế chỉ nhập số
        this.txtNhapCardID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                txtNhapCardID.setText(newValue.replaceAll("[^\\d]", ""));
        });
    }
    
    
//    public String checkCardId(String kw) throws SQLException{
//        String query = "SELECT card_id FROM card WHERE card_id = '"+kw+"'";
//        try (Connection conn = JdbcUtils.getConn()){
//            PreparedStatement stm = conn.prepareStatement(query);
//            rs = stm.executeQuery(query); 
//            String r = "";
//            while(rs.next()) {
//                r = rs.getString(1);
//            }
//            return r ;
//            
//        }
//    }
    private void setCellValue() {
        ReserveDetails r = new ReserveDetails();
        tbReserve.setOnMouseClicked(e -> {
            ReserveBook ls = tbReserve.getItems().get(tbReserve.getSelectionModel().getSelectedIndex());
            txtNgayHetHan.setText(new SimpleDateFormat("yyyy-MM-dd").format(ls.getExpiration_date()));
        });
        
    }
    
    
                    
    
    
    
    @FXML
    public void nhanSachHandler (ActionEvent event) throws ParseException {
       String d1 = this.txtNgayHetHan.getText();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long m = System.currentTimeMillis();
        java.util.Date d = new java.util.Date(m);
        String s = simpleDateFormat.format(d);
       if(ReturnBorowService.daysDiff(d1, s) < 0)
           
           Utils.getBox("Đã hết thời gian quy định(sau 48h) nên không thể nhận sách !!! ", Alert.AlertType.INFORMATION).show();
       else
           
           Utils.getBox("Độc giả có thể nhận sách !!! ", Alert.AlertType.INFORMATION).show();
    }

    @FXML
    private void addView(MouseEvent event) {
    }
    
    @FXML
    private void btnExit (ActionEvent event) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LibrarianHome.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           Logger.getLogger(LibrarianHomeController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
}
