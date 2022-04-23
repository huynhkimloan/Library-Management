/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.librarymanagement;

import com.nhom2.pojo.Book;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableViewBook();
    }    
    
    
    private void LoadTableViewBook(){
        TableColumn colName = new TableColumn("Tên sách");
        colName.setCellValueFactory(new PropertyValueFactory("book_name"));
        colName.setPrefWidth(250);
        
        TableColumn colDescription = new TableColumn("Mô tả");
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colDescription.setPrefWidth(100);

        
//        this.tb_book.getColumns().addAll(colName, colDescription);
    }
    
}
