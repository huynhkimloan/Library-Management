/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;


import com.nhom2.pojo.BorrowBook;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ReturnBorowService {
    Statement stm = null;
    ResultSet rs = null;
   
    public List<BorrowBook> getReturn(String kw) throws SQLException{
        String query = "SELECT * FROM borrowbook WHERE status = 1";
        try (Connection conn = JdbcUtils.getConn()){
            if (kw != null && !kw.isEmpty())
                query += " AND card_id = '"+kw+"' ";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query);
            List<BorrowBook> ls = new ArrayList<>();
            while (rs.next()) {
               ls.add(new BorrowBook(rs.getInt("borrow_id"), rs.getDate("start_date"),
                       rs.getInt("amount"),rs.getInt("card_id"),rs.getInt("book_id"),
                       rs.getFloat("fine"), rs.getInt("status")));
            }
            
            return ls;
        }
    
    }
    
    public List<BorrowBook> getBorrow(String kw) throws SQLException{
        String query = "SELECT * FROM borrowbook WHERE status = 1";
        try (Connection conn = JdbcUtils.getConn()){
            if (kw != null && !kw.isEmpty())
                query += " AND card_id = '"+kw+"' ";
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query);
            List<BorrowBook> ls = new ArrayList<>();
            while (rs.next()) {
               ls.add(new BorrowBook(rs.getInt("borrow_id"), rs.getInt("card_id"),
                       rs.getInt("book_id"),rs.getDate("start_date"),
                       rs.getInt("amount"), rs.getInt("status")));
            }
            
            return ls;
        }
    
    }
    
    public String getEnd_date(String kw) throws SQLException, ParseException{
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate l = LocalDate.now();
        Date d = f.parse(l.toString());
        String query = "SELECT end_date FROM borrowbook WHERE borrow_id = '"+kw+"'";
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query); 
            String r = f.format(d) ;
            while(rs.next()) {
                r = rs.getString(1);
            }
                return r ;
            
        }
    }
    
    public String getStatus(String kw) throws SQLException{
        String query = "SELECT expiration_date FROM card WHERE card_id = '"+kw+"'";
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query);
            String r = "";
            while(rs.next()) {
                r = rs.getString(1);
            }
            return r ;
            
        }
    }
    
    public String getBookName(String kw) throws SQLException{
        String query = "SELECT book_name FROM book WHERE book_id = '"+kw+"'";
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query); 
            String r = "";
            while(rs.next()) {
                r = rs.getString(1);
            }
            return r ;
            
        }
    }
    
    public String checkCardId(String kw) throws SQLException{
        String query = "SELECT card_id FROM card WHERE card_id = '"+kw+"'";
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query); 
            String r = "";
            while(rs.next()) {
                r = rs.getString(1);
            }
            return r ;
            
        }
    }
    
    public String getReaderName(String kw) throws SQLException {
        String query = "SELECT reader_name FROM reader r, card c"
                + " WHERE r.reader_id = c.card_id AND c.card_id = '"+kw+"'";
        try (Connection conn = JdbcUtils.getConn()){
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            String s ="";
            while(rs.next()){
                s = rs.getString(1);
            }
            return s;
        } 
    }
    
    public String getAmountBook(String kw) throws SQLException {
        String query = "SELECT amount FROM book WHERE book_id = '"+kw+"'";
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(query);
            rs = stm.executeQuery(query); 
            String r = "";
            while(rs.next()) {
                r = rs.getString(1);
            }
            return r ;
            
        }
    }
    
    public static int amountRemain(String a, String b) {
        int give, remain;
        give = Integer.parseInt(a);
        remain = Integer.parseInt(b);
        
        return Math.abs(give - remain);
    }
   
    
    public void updateAmountBook(int newValue, String kw) throws SQLException {
        String query = "UPDATE book SET amount = '"+newValue+"'  WHERE book_id = '" +kw+ "'";
        try ( Connection conn = JdbcUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } 
    }
    
    public static long daysDiff(String d1, String d2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        date1 = simpleDateFormat.parse(d1);
        date2 = simpleDateFormat.parse(d2);

        long result = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
       
        return result;
    }
    
    //
    public Boolean daysDiffTest(String d1, String d2) throws ParseException {
        if(daysDiff(d1, d2) >= 0)
            return true;
        else
            return false;
    }
    
}


