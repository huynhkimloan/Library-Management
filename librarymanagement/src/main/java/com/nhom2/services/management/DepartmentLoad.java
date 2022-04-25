/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.services.management;

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
 * @author LENOVO
 */
public class DepartmentLoad {
    public List<Department> getDepartment() throws SQLException {
        List<Department> departments = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM department");
            
            while (rs.next()) {
                Department d = new Department(rs.getInt("department_id"), rs.getString("name"));
                departments.add(d);
            }
        }
        return departments;
    }
    
    public Department getDepartmentID(int department_id) throws SQLException{
        Department deID = null;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM department WHERE department_id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, department_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                deID = new Department(rs.getInt("department_id"), rs.getString("name"));
            }
        }
        return deID;
    }
}
