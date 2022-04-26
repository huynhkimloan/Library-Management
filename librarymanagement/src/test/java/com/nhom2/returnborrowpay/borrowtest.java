/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.returnborrowpay;

import com.nhom2.pojo.Book;
import com.nhom2.pojo.BorrowBook;
import com.nhom2.services.ReturnBorowService;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ASUS
 */
public class borrowtest {
    private static Connection conn;
    ReturnBorowService r = new ReturnBorowService();
     
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
    @CsvSource({"2023-04-30, 2022-04-27, false","2020-04-26, 2022-04-28, true",
                "2022-04-26, 2022-04-26, true"})
    public void diffTest(String date1, String date2, boolean expected)
            throws SQLException, ParseException{
        Assertions.assertEquals(r.daysDiffTest(date1, date2), expected);  
    }
    
    
    
    
}
