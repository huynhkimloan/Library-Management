/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.nhom2.pojo.Book;
import com.nhom2.pojo.Reader;
import com.nhom2.services.BookService;
import com.nhom2.utils.AlertUtils;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
 * @author phamt
 */
public class SearchController implements Initializable {
    @FXML private TableView<Book> tb_book;
    @FXML private TextField kw_book_name;
    @FXML private TextField kw_author_name;
    @FXML private TextField kw_publishingyear_name;
    @FXML private TextField kw_category_name;
    
    private int totalBook;
    private List<Book> listBook;
    
    /**
     * Initializes the controller class.
     */
//    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        LoadTableViewBook();
        
        try {
            LoadTableDataBook_ByBookName(null);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //tìm kiếm theo tên sách
        this.kw_book_name.textProperty().addListener((evt) -> {
                try {
                    this.LoadTableDataBook_ByBookName(this.kw_book_name.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });       
        
        
        //tìm kiếm theo tên tác giả
        this.kw_author_name.textProperty().addListener((evt) -> {
            try {
                this.LoadTableDataBook_ByAuthorName(this.kw_author_name.getText());
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        //tìm kiếm theo loại sách
        this.kw_category_name.textProperty().addListener((evt) -> {
            try {
                this.LoadTableDataBook_ByCategoryBook(this.kw_category_name.getText());
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        
        
        //tìm kiếm theo năm xuất bản
        this.kw_publishingyear_name.textProperty().addListener((evt) -> {
            try {
                this.LoadTableDataBook_ByPublishingYear(this.kw_publishingyear_name.getText());
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }   
    
    
    //định nghĩa các cột
    private void LoadTableViewBook(){
        TableColumn colName = new TableColumn("Tên sách");
        //định nghĩa cách lấy dữ liệu cho ô
        colName.setCellValueFactory(new PropertyValueFactory("book_name"));
        colName.setPrefWidth(100);
        
        TableColumn colCategory = new TableColumn("Loại sách");
        colCategory.setCellValueFactory(new PropertyValueFactory("category"));
        colCategory.setPrefWidth(100);
        
        TableColumn colAuthor = new TableColumn("Tác giả");
        colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        colAuthor.setPrefWidth(100);
        
        TableColumn colDes = new TableColumn("Mô tả");
        colDes.setCellValueFactory(new PropertyValueFactory("description"));
        colDes.setPrefWidth(100);
        
        TableColumn colPublishCompany = new TableColumn("Nhà sản xuất");
        colPublishCompany.setCellValueFactory(new PropertyValueFactory("publishing_company"));
        colPublishCompany.setPrefWidth(100);
        
        TableColumn colPublishYear = new TableColumn("Năm xuất bản");
        colPublishYear.setCellValueFactory(new PropertyValueFactory("publishing_year"));
        colPublishYear.setPrefWidth(100);
        
        TableColumn colSelect = new TableColumn("Chọn");
        colSelect.setCellValueFactory(new PropertyValueFactory("select"));
        colSelect.setPrefWidth(100);
        
        this.tb_book.getColumns().addAll(colName, colCategory, colAuthor, colDes, colPublishCompany, colPublishYear, colSelect);
    }
    
    
    //hiển thị dữ liệu sách lên table book theo tên sách
     private void LoadTableDataBook_ByBookName(String kw) throws SQLException, ParseException{
        BookService s = new BookService();
        this.tb_book.setItems(FXCollections.observableArrayList(s.getBook_By_BookName(kw)));
    }
     
     
    //hiển thị dữ liệu sách lên table book theo tên tác giả
     private void LoadTableDataBook_ByAuthorName(String kw) throws SQLException, ParseException{
        BookService s = new BookService();
        this.tb_book.setItems(FXCollections.observableArrayList(s.getBook_By_AuthorName(kw)));
    }
     
    
     //hiển thị dữ liệu sách lên table book theo năm xuất bản
     private void LoadTableDataBook_ByPublishingYear(String kw) throws SQLException, ParseException{
        int year = Integer.parseInt(kw);
        BookService s = new BookService();
        this.tb_book.setItems(FXCollections.observableArrayList(s.getBook_By_PublishingYear(year)));
     }
     
     
    //hiển thị dữ liệu sách lên table book theo loại sách
     private void LoadTableDataBook_ByCategoryBook(String kw) throws SQLException, ParseException{
        BookService s = new BookService();
        this.tb_book.setItems(FXCollections.observableArrayList(s.getBook_By_CategoryBook(kw)));
    }
     
     
    //tìm kiếm 1 ô, xoá trắng các ô còn lại
    @FXML
    private void keyPressed_BookName(KeyEvent e) {
        if (e.getText()!="")
            bs.setTextNull(kw_author_name, kw_category_name, kw_publishingyear_name);
    }  
    
    BookService bs = new BookService();
    
    @FXML
    private void keyPressed_AuthorName(KeyEvent e) {
        if (e.getText()!="") 
            bs.setTextNull(kw_book_name, kw_category_name, kw_publishingyear_name);
    }
    
    
    @FXML
    private void keyPressed_Category(KeyEvent e) {
        if (e.getText()!="")
            bs.setTextNull(kw_book_name, kw_author_name, kw_publishingyear_name);
    }
    
    
    @FXML
    private void keyPressed_Year(KeyEvent e) {
        if (e.getText()!="") 
            bs.setTextNull(kw_book_name, kw_category_name, kw_author_name);
        
        //hạn chế chỉ nhập số
        this.kw_publishingyear_name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                kw_publishingyear_name.setText(newValue.replaceAll("[^\\d]", ""));
        });
    }
    
   
     
    //sự kiện tick chọn combobox
    @FXML
    private void handleClickTableViewBook(MouseEvent click){
        try{
            Book bk = this.tb_book.getSelectionModel().getSelectedItem();
            double temp = 0;
            //hiển thị checkbox và chuyển trạng thái
            if (bk != null){ 
                if (bk.getSelect().isSelected())                
                    bk.getSelect().setSelected(false);  //chuyển trạng thái                           
                else
                    bk.getSelect().setSelected(true); //chuyển trạng thái
            }
            if (bk.getSelect().isSelected()){
                this.totalBook += 1;
//                getReader_id();
                listBook.add(bk);
            }
            else{
                listBook.remove(bk);
                this.totalBook -= 1;
                if (totalBook <= 0)
                        totalBook = 0;
                    
            }
//        AlertUtils.showAlert(listBook.toString(), Alert.AlertType.WARNING);
//        this.cb.setItems(FXCollections.observableArrayList(listBook));
        
        }catch(NullPointerException ex){};
    }
    
}
