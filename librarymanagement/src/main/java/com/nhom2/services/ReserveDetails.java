/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;

import com.nhom2.pojo.ReserveBook;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

/**
 *
 * @author Phan Thi Dieu Hien
 */
public class ReserveDetails {
    //lấy sách theo năm xuất bản
    
    public List<ReserveBook> getReserve_By_cardID1() throws SQLException{
        
        List<ReserveBook> result = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM reservebook";            
            PreparedStatement stm = conn.prepareStatement(sql);           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ReserveBook  b  = new ReserveBook(rs.getInt("reserve_id"), rs.getDate("activation_date"), 
                        rs.getDate("expiration_date"),  rs.getInt("amount"), 
                        rs.getInt("book_id"), rs.getInt("card_id"));
                result.add(b);
            }
        }
        return result;
}   
    
    public List<ReserveBook> getReserve_By_cardID(String kw) throws SQLException{        
        List<ReserveBook> results = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM reservebook WHERE card_id = '" + kw + "' ";  
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ReserveBook  b  = new ReserveBook(rs.getInt("reserve_id"), rs.getDate("activation_date"), 
                        rs.getDate("expiration_date"),  rs.getInt("amount"), 
                        rs.getInt("book_id"), rs.getInt("card_id"));
                results.add(b); 
            }
        }
        return results;
    }
    public void setTextNull1(TextField a) {
        a.setText("");
    }
    

    
     
         
               
    
}

