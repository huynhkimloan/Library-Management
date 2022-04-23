/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services.management;
 
import com.nhom2.pojo.Book;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BookModify {
    public List<Book> getBook(String book_name) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM book");
            String sql = "SELECT * FROM book";
            if (book_name != null && !book_name.isEmpty())
                sql += " WHERE book_name like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            if (book_name != null && !book_name.isEmpty())
                stm.setString(1, book_name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b = new Book(rs.getInt("book_id"), rs.getString("book_name"), 
                                    rs.getString("description"),
                                    rs.getString("publishing_company"),
                                    rs.getDate("import_date"), 
                                    rs.getBoolean("active"), 
                                    rs.getString("location"),
                                    rs.getDate("publishing_year"),
                                    rs.getString("category"),
                                    rs.getString("author"));
                books.add(b);
            }
        }
        
        return books;
    }
    
    public void AddBook(Book b) throws SQLException{        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO book (book_name, description, category, author, publishing_company, publishing_year, import_date, location)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, b.getBook_name());
            stm.setString(2, b.getDescription());
            stm.setString(3, b.getCategory());
            stm.setString(4, b.getAuthor());
            stm.setString(5, b.getPublishing_company());
            stm.setDate(6, (Date) b.getPublishing_year());
            stm.setDate(7, (Date) b.getImport_date());
            stm.setString(8, b.getLocation());
            stm.executeUpdate();
        }
    }
    
    public void UpdateBook(int book_id, String book_name, String description, String publishing_company, Date import_date, String location, Date publishing_year, String category, String author) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE book SET book_name = ?, description = ?, "
                    + " publishing_company = ?, import_date = ?, "
                    + " location =?, publishing_year = ?, category = ?, author = ?"
                    + " WHERE (book_id = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, book_id);
            stm.setString(2, book_name);
            stm.setString(3, description);
            stm.setString(4, publishing_company);
            stm.setDate(5, import_date);
            stm.setString(6, location);
            stm.setDate(7, publishing_year);
            stm.setString(8, category);
            stm.setString(9, author);
            
            stm.executeUpdate();
        }
    }
    
    public void DeleteBook(int book_id) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "DELETE FROM book WHERE (book_id = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, book_id);
            stm.executeUpdate();
        }
    }

}
