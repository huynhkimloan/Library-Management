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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

/**
 *
 * @author ASUS
 */
public class ReaderServices {

    //kiểm tra đăng nhập của admin bằng tênđn + mk
    public boolean KiemTraDangNhapADMIN(String r_name, String r_pass) throws SQLException {
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT password FROM reader WHERE username = ? AND user_role = ?");
            stm.setString(1, r_name);
            stm.setString(2, "ADMIN");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String pass = rs.getString("password");
                if (r_pass.equals(pass)== true)
                    return true;
            }
            return false;
        }
    }
    
    
    //kiểm tra đăng nhập của user
    public boolean KiemTraDangNhapUSER(String r_name, String r_pass) throws SQLException {
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT password FROM reader WHERE username = ? AND user_role = ?");
            stm.setString(1, r_name);
            stm.setString(2, "USER");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String pass = rs.getString("password");
                if (r_pass.equals(pass)== true)
                    return true;
            }
            return false;
        }
    }
    
    
    //lấy mã độc giả *
    public int getReaderID(String username) throws SQLException{
        int rID = 0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT reader_id FROM reader WHERE username = ?");
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                rID = rs.getInt("reader_id");                
            }    
            return rID;
        }
    }
    
    
    //kiểm tra tên đã tồn tại
    public boolean username_exists(String u_name) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT username FROM reader");
            while (rs.next()){
                String name = rs.getString("username");
                if (u_name.equals(name)== true)
                    return true;
            }
            return false;              
        }
    }
    
    //đăng ký tài khoản
    public void SignUp_Account(Reader r) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("INSERT INTO ktpm_qltv.reader "
                    + "(reader_name, username, password, sex, address, phone, email, "
                    + "date_of_birth, object, active, user_role, department_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stm.setString(1, r.getReader_name());
            stm.setString(2, r.getUsername());
            stm.setString(3, r.getPassword());
            stm.setString(4, r.getSex());
            stm.setString(5, r.getAddress());
            stm.setString(6, r.getPhone());
            stm.setString(7, r.getEmail());
            stm.setDate(8, (Date) r.getDate_of_birth2());
            stm.setString(9, r.getObject());
            stm.setBoolean(10, r.isActive());
            stm.setString(11, r.getUser_role());
            stm.setInt(12, r.getDepartment_id());
            stm.executeUpdate();
            conn.commit();
        }
    }
 
    
    //kiểm tra số điện thoại hợp lệ
    public boolean check_PhoneNumber(String p) {
        if (p.length() != 10)
            return false;
        return true;
    }
  
    
    //kiểm tra mật khẩu dưới 6 ký tự
    public boolean checkPass_less6character(String p){
        if (p.length() < 6)
            return true;
        return false;
    }
    
    //kiểm tra mật khẩu chứa ký tự đặc biệt [!@#$%&*()_+=|<>?{}\\[\\]~-]
//    public static boolean SpecialChar(String s){
//        for(int i = 0; i < s.length(); i++)
//        {
//          
//        }
//        return true;
//    }    
    //
    
    //kiểm tra mật khẩu giống nhau
    public boolean checkPass_Similar(String p1, String p2){
        if (p1.equals(p2) == false)
                return false;
        return true;
    }
    
    //mật khảu phải chứa chữ hoa, chữ thường & số
     public static String checkPassword(String str){
        int upper = 0, lower = 0, number = 0, special = 0;

            for(int i = 0; i < str.length(); i++)
            {
                char ch = str.charAt(i);
                if (ch >= 'A' && ch <= 'Z')
                    upper++;
                else if (ch >= 'a' && ch <= 'z')
                    lower++;
                else if (ch >= '0' && ch <= '9')
                    number++;
                else
                    special++;
            }
        if (upper == 0 || number == 0 || lower == 0)
            return "Mật khẩu phải bao gồm chữ hoa, chữ thường và số";
        return "";        
    }
   
}
