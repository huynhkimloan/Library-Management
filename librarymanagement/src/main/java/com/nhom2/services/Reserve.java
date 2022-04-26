/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;

import com.nhom2.pojo.Book;
import com.nhom2.pojo.ReserveBook;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phamt
 */
public class Reserve {
    public List<Book> getBookList(String kw) throws SQLException{
        List<Book> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM book ";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE book_name LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Book s = new Book(rs.getInt("book_id"), rs.getString("book_name"), rs.getBoolean("active"));
                list.add(s);
            }
        }        
        return list;
     }    
     public int kiemTraSachTonTai() throws SQLException{
         int co = 1;
        try(Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT active FROM book");
            while (rs.next()){
                boolean kq = rs.getBoolean("active");
                if (kq == true)
                    return co = 1;
                return co = 0;
            }
            return co;
        }
     }  
        
    public void datSach(ReserveBook reserve) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO reservebook (activation_date, expiration_date, amount, card_id, book_id) "
                    + " VALUES (?, ?, ?, ?, ?)";           
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, reserve.getActivation_date());
            stm.setString(2, reserve.getExpiration_date());
            stm.setInt(3, reserve.getAmount());
            stm.setInt(4, reserve.getCard_id());
            stm.setInt(5, reserve.getBook_id());
            stm.executeUpdate();
        }
    }
    
//    public void UpdateReserve(Date activation_date, Date expiration_date, int amount, int card_id, int book_id) throws SQLException{
//        try(Connection conn = JdbcUtils.getConn()){
//            String sql = "UPDATE reserve SET reader_name = ?, username = ?, "
//                    + " sex = ?, date_of_birth = ?, "
//                    + " email = ?, address = ?, phone = ?, object = ?, department_id = ? "
//                    + " WHERE (reader_id = ?);";
//            PreparedStatement stm = conn.prepareStatement(sql);
//            
//            stm.setString(1, reader_name);
//            stm.setString(2, username);
//            stm.setString(3, sex);
//            stm.setDate(4, date_of_birth);
//            stm.setString(5, email);
//            stm.setString(6, address);
//            stm.setString(7, phone);
//            stm.setString(8, object);
//            stm.setInt(9, department_id);
//            stm.setInt(10, reader_id);
//            
//            stm.executeUpdate();
//        }
    
    public int getBookIDFromBook(int id) throws SQLException{
        int book_ID = -1;       
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT book_id FROM book WHERE book_id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();    
//            rs.next();
            while(rs.next())
                book_ID = rs.getInt("book_id");                                                    
            }         
        return book_ID;
    }  
    
    
    public int getCardIDFromCard(int id) throws SQLException{
        int card_ID =-1;
        try(Connection conn = JdbcUtils.getConn()){
           PreparedStatement stm = conn.prepareStatement("SELECT card_id FROM card WHERE card_id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();    
//            rs.next();
            while(rs.next())
                card_ID = rs.getInt("card_id");                                                    
            }         
        return card_ID;
    }
    
    public boolean kiemTraNamNhuan(int y) {
         if (y % 400 == 0 || y % 4 == 0 && y % 100 != 0)
            return true;
        return false;
    }
}
   
//    public static void long ngayHetHan() {
//        LocalDateTime ngayKH = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String formatted = ngayKH.format(formatter);
//        
//        // lấy thời điểm bây giờ:
//        Calendar c = Calendar.getInstance();
//        // cộng thêm một ngày:
//        c.add(Calendar.DAY_OF_YEAR, 2); // ngày mai// Muốn chuyển qua lớp khác như Date chẳng hạn:
//        Date d = (Date) c.getTime(); // vân vân.    
//    }
    
    
    
    //xoá giá trị của ô text
  
//    public void datSach(ReserveBook b) throws SQLException{        
//        try(Connection conn = JdbcUtils.getConn()){
//            String sql = "INSERT INTO reservebook (book_name, description, publishing_company, import_date, location, publishing_year, category, author)"
//                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setString(1, b.getBook_name());
//            stm.setString(2, b.getDescription());
//            stm.setString(3, b.getPublishing_company());
//            stm.setString(4, b.getImport_date());
//            stm.setString(5, b.getLocation());
//            stm.setString(6, b.getPublishing_year());
//            stm.setString(7, b.getCategory());
//            stm.setString(8, b.getAuthor());
//            
//            stm.executeUpdate();
//        }
//    }


        
