/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.managementtest;

import com.nhom2.services.management.ReaderModify;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 *
 * @author LENOVO
 */
public class managementreadertest {
    private static Connection conn;
    ReaderModify rds = new ReaderModify();
    
    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = JdbcUtils.getConn();
    }
    
    @AfterAll
    public static void AfterAll() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    @ParameterizedTest
    @CsvSource({"ngan07, true","ngan, false"})
    public void kiemTraTrungUsernameTest(String username, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, rds.kiemTraTrungUsername(username));
    }
}
