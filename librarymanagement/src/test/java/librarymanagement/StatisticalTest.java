/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

/**
 *
 * @author Phan Thi Dieu Hien
 */
import org.junit.jupiter.api.Assertions;
import com.nhom2.services.Statistical;
import com.nhom2.utils.JdbcUtils;
import com.nhom2.utils.Utils;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StatisticalTest {
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
    @CsvSource({"20   21,2021", "  2 02   1,2021"})
    public void remove_WhitespaceTest(String s, String expected) {
        Assertions.assertEquals(expected, Utils.removeWhitespace(s));
    }
    
    @ParameterizedTest
    @CsvSource({"0123, false","2021, true", "2022, true", "15673, false"})
    public void checkNam_Bang4characterTest(String nam, boolean expected) throws SQLException{
        Statistical rds = new Statistical();
        Assertions.assertEquals(expected, rds.check_Nam(nam));
    }
    
    @ParameterizedTest
    @CsvSource({"1969, false","2021, true", "2022, true", "2025, false"})
    public void checkNam_HopLe(String nam1, boolean expected) throws SQLException{
        Statistical rds = new Statistical();
        Assertions.assertEquals(expected, rds.check_NamHLe(nam1));
    }
    
    
}