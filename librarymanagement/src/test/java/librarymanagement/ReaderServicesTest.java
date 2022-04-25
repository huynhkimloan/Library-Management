/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import com.nhom2.services.ReaderServices;
import com.nhom2.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author phamt
 */
public class ReaderServicesTest {
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
    @CsvSource({"admin, 123, true","admin, 1234, false"})
    public void KiemTraDangNhapADMINTest(String name, String pass, boolean expected) throws SQLException{
        ReaderServices rds = new ReaderServices();
        Assertions.assertEquals(expected, rds.KiemTraDangNhapADMIN(name, pass));
    }
    
    
    @ParameterizedTest
    @CsvSource({"hien01, 123, true","qqq, 123, false"})
    public void KiemTraDangNhapUSERTest(String name, String pass, boolean expected) throws SQLException{
        ReaderServices rds = new ReaderServices();
        Assertions.assertEquals(expected, rds.KiemTraDangNhapUSER(name, pass));
    }
    
    
    @ParameterizedTest
    @CsvSource({"hien01, true","phamdieuque, false"})
    public void check_username_existsTest(String username, boolean expected) throws SQLException{
        ReaderServices rds = new ReaderServices();
        Assertions.assertEquals(expected, rds.username_exists(username));
    }
    
    
    @ParameterizedTest
    @CsvSource({"0963490927, true","098765432, false", "09876543210, false"})
    public void check_PhoneNumberValidTest(String phone, boolean expected) throws SQLException{
        ReaderServices rds = new ReaderServices();
        Assertions.assertEquals(expected, rds.check_PhoneNumber(phone));
    }
    
    
    @ParameterizedTest
    @CsvSource({"012345, false","11111, true", "a, true", "123abcd, false"})
    public void checkPass_less6characterTest(String pass, boolean expected) throws SQLException{
        ReaderServices rds = new ReaderServices();
        Assertions.assertEquals(expected, rds.checkPass_less6character(pass));
    }
  
            
    @ParameterizedTest
    @CsvSource({"ab012345, ab012345, true","111111, 111112, false"})
    public void checkPass_SimilarTest(String p1, String p2, boolean expected) throws SQLException{
        ReaderServices rds = new ReaderServices();
        Assertions.assertEquals(expected, rds.checkPass_Similar(p1, p2));
    }
}
