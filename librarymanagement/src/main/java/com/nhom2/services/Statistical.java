/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;

import com.nhom2.pojo.Reader;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ASUS
 */
public class Statistical {   
    LocalDate localDate = LocalDate.now();
    int namHienTai = localDate.getYear();

    //Bạn đọc chưa trả sách    
    public List<Reader> chuaTraSach() throws SQLException { 
        List<Reader> readers = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT r.* FROM borrowbook b, card c, reader r WHERE b.card_id = c.card_id AND c.card_id = r.reader_id AND c.active = 0");
   
            while(rs.next()) {
                Reader reader;
                reader = new Reader(rs.getString("reader_name"), 
                        rs.getString("sex"),
                        rs.getDate("date_of_birth"),      
                        rs.getString("email"));
                readers.add(reader);
            }
        }    
        return readers;
    }
    
    
//    public String sachMuonTheoNam(String nam) throws SQLException {
//        int year = Integer.parseInt(nam);
//        LocalDate localDate = LocalDate.now();
//        int namHienTai = localDate.getYear();
//        int kq =0;
//        try(Connection conn = JdbcUtils.getConn()){
////            String sql = "SELECT amount From borrowbook GROUP BY YEAR(start_date)";
////            if (nam != null)
////                sql += "WHERE YEAR(start_date) LIKE CONCAT('%', ?, '%')";
//            PreparedStatement stm = conn.prepareStatement(sql);                          
//            if(year > 1970 && year <= namHienTai)
//                stm.setInt(1, year);
//            ResultSet rs = stm.executeQuery();
//            while(rs.next()){
//                int sc = rs.getInt("amount");
//                kq += sc;
//            }
//        }
//        String kq2=Integer.toString(kq);
//
//        return kq2;
//
//    }
    public String sachMuonTheoNam(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;                          
        try(Connection conn = JdbcUtils.getConn()){
        PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(start_date) = ? GROUP BY YEAR(start_date)");
        stm.setInt(1, year);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            int sc = rs.getInt("amount");
                kq += sc;  
            }
        
    }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Năm " + year + " tổng số sách độc giả mượn là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 = "Năm " + year + " độc giả không mượn sách" ;
        
    }
    
    //Tổng số sách được mượn theo quý
    public String sachMuonTheoQuy1(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(start_date) = ? AND MONTH(start_date) BETWEEN 1 AND 3  GROUP BY YEAR(start_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;    
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 1 " + "Năm " + year + " độc giả mượn là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Quý 1 "+  "Năm " + year + " độc giả không mượn sách" ;
        
    }
     
     
    public String sachMuonTheoQuy2(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(start_date) = ? AND MONTH(start_date) BETWEEN 4 AND 6  GROUP BY YEAR(start_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;
//                Date year = rs.getDate("year");
//                kq = "Tổng số lượng sách được mượn trong quý 1 của năm " + sc + "      Năm " + year + "\n";
              
                //a.add(kq);
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 2 " + "Năm " + year + " độc giả mượn là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Quý 2 "+  "Năm " + year + " độc giả không mượn sách" ;

    }
    public String sachMuonTheoQuy3(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(start_date) = ? AND MONTH(start_date) BETWEEN 7 AND 9  GROUP BY YEAR(start_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 3 " + "Năm " + year + " độc giả mượn là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Quý 3 "+  "Năm " + year + " độc giả không mượn sách"  ;

    }
    
    public String sachMuonTheoQuy4(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(start_date) = ? AND MONTH(start_date) BETWEEN 10 AND 12  GROUP BY YEAR(start_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 4 " + "Năm " + year + " độc giả mượn là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Quý 4 "+  "Năm " + year + " độc giả không mượn sách";

    }
    
    
    //Tổng số sách được trả theo từng nam
    public String sachTraTheoNam(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(end_date) = ? GROUP BY YEAR(end_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;            
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách " + "Năm " + year + " độc giả trả là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Năm " + year + " độc giả không mượn sách" ;

        }
    
    
    //Tổng số sách được trả theo quý

    public String sachTraTheoQuy1(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(end_date) = ? AND MONTH(end_date) BETWEEN 1 AND 3  GROUP BY YEAR(end_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;            
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 1 " + "Năm " + year + " độc giả trả là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Qúy 1 Năm " + year + " độc giả không mượn sách" ;

        }
        
    public String sachTraTheoQuy2(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(end_date) = ? AND MONTH(end_date) BETWEEN 4 AND 6  GROUP BY YEAR(end_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;            
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 2 " + "Năm " + year + " độc giả trả là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Qúy 2 Năm " + year + " độc giả không mượn sách" ;

    }   
         
    public String sachTraTheoQuy3(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(end_date) = ? AND MONTH(end_date) BETWEEN 7 AND 9  GROUP BY YEAR(end_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;            
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 3 " + "Năm " + year + " độc giả trả là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Qúy 3 Năm " + year + " độc giả không mượn sách" ;
    }
        
    public String sachTraTheoQuy4(String nam) throws SQLException {
        int year = Integer.parseInt(nam);
        int kq =0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT amount From borrowbook WHERE YEAR(end_date) = ? AND MONTH(end_date) BETWEEN 10 AND 12  GROUP BY YEAR(end_date)");
            stm.setInt(1, year);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("amount");
                kq += sc;            
            }
        }
        String kq2;
        if(kq != 0)
        {
            kq2 = "Tổng số sách quý 4 " + "Năm " + year + " độc giả trả là " + Integer.toString(kq);
            return kq2;
        }
        return kq2 ="Qúy 4 Năm " + year + " độc giả không mượn sách" ;
    }
    //kiểm tra số điện thoại hợp lệ
    public boolean check_Nam(String p) {
        if (p.length() != 4 )
            return false;
        return true;
    }
    // Kiểm tra năm có hợp lệ không
    public boolean check_NamHLe(String p) {
        String a = p;
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int i = Integer.parseInt(a);
        if(i > year || i <1970)
            return false;      
        return true;
    }
    
}

  

//    public ResultSet sachTraTheoNam() throws SQLException { 
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT SUM(amount) AS 'Tổng số lượng sách được trả trong năm', YEAR(start_date) AS 'Năm' From BorrowBook GROUP BY YEAR(end_date)\n");
//        return rs;
    //}




