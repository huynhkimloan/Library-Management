/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import com.nhom2.services.DepartmentServices;
import org.junit.jupiter.api.Assertions;
import com.nhom2.services.ReaderServices;
import com.nhom2.utils.EmailFormatUtils;
import com.nhom2.utils.JdbcUtils;
import com.nhom2.utils.Utils;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author phamt
 */
public class DepartmentServicesTest {
    private static Connection conn;
     
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
    @CsvSource({"Công nghệ thông tin, 1", "CNSH, 0"})
    public void getDepartmentIDTest(String d_name, int expected) throws SQLException {
        DepartmentServices d = new DepartmentServices();
        Assertions.assertEquals(expected, d.getDepartmentID(d_name));
    }
}
