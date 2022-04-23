/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.Book;
import com.nhom2.services.BookService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SearchController implements Initializable {

    @FXML private TableView<Book> tb_book;
    @FXML private TextField kw_book_name;
    @FXML private TextField kw_author_name;
    @FXML private TextField kw_publishingyear_name;
    @FXML private TextField kw_category_name;
    @FXML private ComboBox<Book> cb;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        LoadTableViewBook();
        try {
            LoadTableDataBook();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BookService s = new BookService();
        try {
            this.cb.setItems(FXCollections.observableArrayList(s.getBooks()));
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    
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
    

     private void LoadTableDataBook() throws SQLException, ParseException{
        BookService s = new BookService();
        this.tb_book.setItems(FXCollections.observableArrayList(s.getBooks()));
    }
    
}
