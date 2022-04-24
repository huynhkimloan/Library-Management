/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;

import com.nhom2.pojo.BorrowBook;
import com.nhom2.utils.JdbcUtils;
import com.nhom2.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author ASUS
 */
public class ReturnService {
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
    
    public static long daysDiff(String d1, String d2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        date1 = simpleDateFormat.parse(d1);
        date2 = simpleDateFormat.parse(d2);

        long result = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
       
        return result;
    }
    
    
}


