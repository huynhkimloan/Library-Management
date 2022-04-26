/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        
package com.nhom2.managementtest;

import com.nhom2.services.management.BookModify;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 *
 * @author LENOVO
 */
public class managementbooktest {
    private static Connection conn;
    BookModify bm = new BookModify();
    
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
    @CsvSource({"K19, true","K11, false"})
    public void kiemTraTrungViTriTest(String location, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, bm.kiemTraTrungViTri(location));
    }
    
    @ParameterizedTest
    @CsvSource({"2019-04-26, 2022-04-24, true","2021-04-26, 2019-04-24, false"})
    public void soSanhNXB_NNTest(String publishing_year, String import_date, boolean expected) throws SQLException, ParseException{
        Assertions.assertEquals(expected, BookModify.soSanhNgay(publishing_year, import_date));
    }
    
    

}
