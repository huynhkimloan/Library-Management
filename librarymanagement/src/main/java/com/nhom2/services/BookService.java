/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;

import com.nhom2.pojo.Book;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author ASUS
 */
public class BookService {
    // lấy sách theo tên sách
    public List<Book> getBook_By_BookName(String kw) throws SQLException{
        List<Book> results = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM book";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE book_name LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b  = new Book(rs.getString("book_name"), rs.getString("category"), 
                        rs.getString("author"),  rs.getString("description"), 
                        rs.getString("publishing_company"), rs.getDate("publishing_year"));
                results.add(b); 
            }
        }
        return results;
    }
    
    
    ///lấy sách theo tên tác giả
    public List<Book> getBook_By_AuthorName(String kw) throws SQLException{
        List<Book> results = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM book";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE author LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b  = new Book(rs.getString("book_name"), rs.getString("category"), 
                        rs.getString("author"),  rs.getString("description"), 
                        rs.getString("publishing_company"), rs.getDate("publishing_year"));
                results.add(b); 
            }
        }
        return results;
    }
     
    
    ///lấy sách theo loại sách
    public List<Book> getBook_By_CategoryBook(String kw) throws SQLException{
        List<Book> results = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM book";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE category LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b  = new Book(rs.getString("book_name"), rs.getString("category"), 
                        rs.getString("author"),  rs.getString("description"), 
                        rs.getString("publishing_company"), rs.getDate("publishing_year"));
                results.add(b); 
            }
        }
        return results;
    }
    
    
    //lấy sách theo năm xuất bản
    public List<Book> getBook_By_PublishingYear(int year) throws SQLException{
        String kw = Integer.toString(year);
        
        List<Book> results = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM book";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE YEAR(publishing_year) LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setInt(1, year);
           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b  = new Book(rs.getString("book_name"), rs.getString("category"), 
                        rs.getString("author"),  rs.getString("description"), 
                        rs.getString("publishing_company"), rs.getDate("publishing_year"));
                results.add(b); 
            }
        }
        return results;
    }
    
    
    //lấy sách
//    public List<Book> getBooks() throws SQLException{
//        List<Book> results = new ArrayList<>();
//        try(Connection conn = JdbcUtils.getConn()){
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM book");
//            
//            while (rs.next()) {
//                Book b  = new Book(rs.getString("book_name"), rs.getString("category"), 
//                        rs.getString("author"),  rs.getString("description"), 
//                        rs.getString("publishing_company"), rs.getDate("publishing_year"));
//                results.add(b); 
//            }
//        }
//        return results;
//    }

    
   

   
}
