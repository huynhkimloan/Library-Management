/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services.management;

import com.nhom2.pojo.Reader;
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
public class ReaderModify {
     public List<Reader> getReader(String reader_name) throws SQLException {
        List<Reader> readers = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {

            String sql = "SELECT r.*, c.activation_date, d.department_id \n" +
                        "FROM reader r, card c, department d \n" +
                        "WHERE r.reader_id=c.card_id and r.department_id=d.department_id ";
            if (reader_name != null && !reader_name.isEmpty())
                sql += " and reader_name like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            if (reader_name != null && !reader_name.isEmpty())
                stm.setString(1, reader_name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader r = new Reader(rs.getInt("reader_id"), rs.getString("reader_name"), 
                                    rs.getString("username"), rs.getString("password"),
                                    rs.getString("sex"), rs.getDate("date_of_birth"), 
                                    rs.getString("email"), rs.getString("address"), 
                                    rs.getString("phone"), rs.getString("object"), 
                                    rs.getBoolean("active"), rs.getString("user_role"),
                                    rs.getInt("department_id"), rs.getDate("activation_date"));
                readers.add(r);
            }
        }
        
        return readers;
    }
     
    public void UpdateReader(int reader_id, String reader_name, String username, String sex, Date date_of_birth, String email, String address, String phone, String object, int department_id, Date activation_date) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            //Reader
            String sql = "UPDATE reader SET reader_name = ?, username = ?, "
                    + " sex = ?, date_of_birth = ?, "
                    + " email = ?, address = ?, phone = ?, object = ?, department_id = ? "
                    + " WHERE (reader_id = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, reader_name);
            stm.setString(2, username);
            stm.setString(3, sex);
            stm.setDate(4, date_of_birth);
            stm.setString(5, email);
            stm.setString(6, address);
            stm.setString(7, phone);
            stm.setString(8, object);
            stm.setInt(9, department_id);
            stm.setInt(10, reader_id);
            
            stm.executeUpdate();
            
            //Card
            int card_id = reader_id;
            String sql2 = "UPDATE card SET activation_date = ? WHERE (card_id = ?);";
            PreparedStatement stm2 = conn.prepareStatement(sql2);
            stm2.setDate(1, activation_date);
            stm2.setInt(2, card_id);
            
            stm2.executeUpdate();
        }
    }
     
    public void DeleteReader(int reader_id) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            //Card
            int card_id = reader_id;
            String sql = "DELETE FROM card WHERE (card_id = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, card_id);
            stm.executeUpdate();
            
            //Reader
            String sql2 = "DELETE FROM reader WHERE (reader_id = ?);";
            PreparedStatement stm2 = conn.prepareStatement(sql2);
            stm2.setInt(1, reader_id);
            stm2.executeUpdate();
        }
    }
    
    

}
