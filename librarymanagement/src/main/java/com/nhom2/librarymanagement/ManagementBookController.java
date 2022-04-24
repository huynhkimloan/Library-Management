/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.Book;
import com.nhom2.services.management.BookModify;
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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ManagementBookController implements Initializable {
    @FXML private TableView<Book> tbBooks;
    @FXML private TextField txtBookName;
    @FXML private TextArea txtDescription;
    @FXML private TextField txtCategory;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtPublishingCompany;
    @FXML private DatePicker dtpPublishingYear;
    @FXML private DatePicker dtpImportDate;
    @FXML private TextField txtLocation;
    @FXML private Label lbMess;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.loadTableView();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    private void loadTableView(){
        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colId.setPrefWidth(20);
        
        TableColumn colName = new TableColumn("Tên sách");
        colName.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        colName.setPrefWidth(235);
        
        TableColumn colDescription = new TableColumn("Mô tả");
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setPrefWidth(235);
        
        TableColumn colCategory = new TableColumn("Loại sách");
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCategory.setPrefWidth(130);
        
        TableColumn colAuthor = new TableColumn("Tác giả");
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colAuthor.setPrefWidth(200);
        
        TableColumn colPublishingCompany = new TableColumn("Nhà xuất bản");
        colPublishingCompany.setCellValueFactory(new PropertyValueFactory<>("publishing_company"));
        colPublishingCompany.setPrefWidth(135);
        
        TableColumn colPublishingYear = new TableColumn("Năm XB");
        colPublishingYear.setCellValueFactory(new PropertyValueFactory<>("publishing_year"));
        colPublishingYear.setPrefWidth(120);
        
        TableColumn colImportDate = new TableColumn("Ngày nhập");
        colImportDate.setCellValueFactory(new PropertyValueFactory<>("import_date"));
        colImportDate.setPrefWidth(120);
        
        TableColumn colLocation = new TableColumn("Vị trí");
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colLocation.setPrefWidth(90);
        
        this.tbBooks.getColumns().addAll(colId, colName, colDescription, colCategory, 
                                    colAuthor, colPublishingCompany, colPublishingYear,
                                    colImportDate, colLocation);
    }
    
    private void loadTableData(String book_name) throws SQLException{
        BookModify b = new BookModify();
        this.tbBooks.setItems(FXCollections.observableList(b.getBook(book_name)));
    }
    
    public void searchHandler(ActionEvent event){
        try {
            this.loadTableData(this.txtBookName.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetHandler(ActionEvent event) {
        resetBook();
    }
    
    @FXML
    private void handleClickTableView(MouseEvent click) throws SQLException{
        Book b = tbBooks.getSelectionModel().getSelectedItem();
        if (b!=null) {
            txtBookName.setText(b.getBook_name());
            txtDescription.setText(b.getDescription());
            txtCategory.setText(b.getCategory());
            txtAuthor.setText(b.getAuthor());
            txtPublishingCompany.setText(b.getPublishing_company());
            dtpPublishingYear.setValue(LocalDate.of(Integer.parseInt((b.getPublishing_year().substring(6, 10))),
                    Integer.parseInt(b.getPublishing_year().substring(3, 5)),
                    Integer.parseInt(b.getPublishing_year().substring(0, 2))));

            dtpImportDate.setValue(LocalDate.of(Integer.parseInt((b.getImport_date().substring(6, 10))),
                    Integer.parseInt(b.getImport_date().substring(3, 5)),
                    Integer.parseInt(b.getImport_date().substring(0, 2))));
            
            txtLocation.setText(b.getLocation());
        }
    }
    
    public void addHandler(ActionEvent event) throws SQLException, ParseException {
        try{
            SimpleDateFormat f =new SimpleDateFormat("dd-MM-yyyy");
            
            String book_name = Utils.removeWhitespace(this.txtBookName.getText());
            String description = Utils.removeWhitespace(this.txtDescription.getText());
            String publishing_company = Utils.removeWhitespace(this.txtPublishingCompany.getText());
            
            String ngay1 = this.dtpPublishingYear.getValue().toString();
            Date ngayXB = f.parse(ngay1);
            java.sql.Date publishing_year = new java.sql.Date(ngayXB.getTime());
            
            String ngay2 = this.dtpImportDate.getValue().toString();
            Date ngayNhap = f.parse(ngay2);
            java.sql.Date import_date = new java.sql.Date(ngayNhap.getTime());
            
            String location = Utils.removeWhitespace(this.txtLocation.getText());
            String category = Utils.removeWhitespace(this.txtCategory.getText());
            String author = Utils.removeWhitespace(this.txtAuthor.getText());
            
            if (book_name == null || book_name.equals(""))
                lbMess.setText("Giá trị tên sách bắt buộc nhập!!");
            else if (category == null || category.equals(""))
                lbMess.setText("Giá trị loại sách bắt buộc nhập!!");
            else if (author == null || author.equals(""))
                lbMess.setText("Giá trị tên tác giả bắt buộc nhập!!");
            else {
                Book b = new Book(book_name, description, publishing_company, import_date, location, publishing_year, category, author);
                BookModify bm = new BookModify();
                bm.AddBook(b);
                loadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                resetBook();
            }
        }catch (NumberFormatException ex2){
                lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
        }
   }
    
    public void editHandler(ActionEvent event) throws SQLException, ParseException {
        Book b = this.tbBooks.getSelectionModel().getSelectedItem();
        if (b != null){
            try{
                SimpleDateFormat f =new SimpleDateFormat("dd-MM-yyyy");
                
                int book_id = b.getBook_id();
                String book_name = Utils.removeWhitespace(this.txtBookName.getText());
                String description = Utils.removeWhitespace(this.txtDescription.getText());
                String category = Utils.removeWhitespace(this.txtCategory.getText());
                String author = Utils.removeWhitespace(this.txtAuthor.getText());
                String publishing_company = Utils.removeWhitespace(this.txtPublishingCompany.getText());
                
                String ngay1 = this.dtpPublishingYear.getValue().toString();
                Date ngayXB = f.parse(ngay1);
                java.sql.Date publishing_year = new java.sql.Date(ngayXB.getTime());
            
                String ngay2 = this.dtpImportDate.getValue().toString();
                Date ngayNhap = f.parse(ngay2);
                java.sql.Date import_date = new java.sql.Date(ngayNhap.getTime());
                
                String location = Utils.removeWhitespace(this.txtLocation.getText());

                if (book_name == null || book_name.equals(""))
                    lbMess.setText("Giá trị tên sách bắt buộc nhập!!");
                else if (category == null || category.equals(""))
                    lbMess.setText("Giá trị loại sách bắt buộc nhập!!");
                else if (author == null || author.equals(""))
                    lbMess.setText("Giá trị tên tác giả bắt buộc nhập!!");
                else {
                    BookModify bm = new BookModify();
                    bm.UpdateBook(book_id, book_name, description, publishing_company, import_date, location, publishing_year, category, author);
                    loadTableData(null);      
                    Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                    resetBook();
                }
            }catch (NumberFormatException ex2){
                    lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
            }
        }
        else
            lbMess.setText("Chưa chọn đối tượng để sửa");
    }
    
    public void deleteHandler(ActionEvent event) throws SQLException {
        Book b = this.tbBooks.getSelectionModel().getSelectedItem();
  
        if (b != null){
            int book_id = b.getBook_id();
            BookModify bm = new BookModify();
            bm.DeleteBook(book_id);
            loadTableData(null);
            Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
            resetBook();
        }
        else
            lbMess.setText("Chưa chọn đối tượng để xoá");
    }
    
    private void resetBook(){
        this.lbMess.setText("");
        this.txtBookName.setText("");
        this.txtDescription.setText("");
        this.txtCategory.setText("");
        this.txtAuthor.setText("");
        this.txtPublishingCompany.setText("");
        this.dtpPublishingYear.setValue(LocalDate.now());
        this.dtpImportDate.setValue(LocalDate.now());
        this.txtLocation.setText("");
    }
    

}
