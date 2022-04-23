/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services;

import com.nhom2.pojo.Department;
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
public class DepartmentServices {

    public static int get;
    public List<Department> getDepartment() throws SQLException{
        List<Department> results = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM department");
            
            while (rs.next()) {
                Department d  = new Department(rs.getInt("department_id"), rs.getString("name"));
                results.add(d); 
            }
        }
        return results;
    }
    
    //    public List<Reader> getReader() throws SQLException{
//        List<Reader> results = new ArrayList<>();
//        try(Connection conn = JdbcUtils.getConn()) {
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM reader");
//            
//            while (rs.next()) {
//                Reader r  = new Reader(rs.getInt("reader_id"), rs.getString("reader_name"));
//                
//                results.add(r); 
//            }
//        }
//        return results;
//    }
    
    //lấy mã khoa
    public int getDepartmentID(String d_name) throws SQLException{
        int dID = 0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT department_id FROM department WHERE name = ?");
            stm.setString(1, d_name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                dID = rs.getInt("department_id");                
            }    
            return dID;
        }
    }
}
