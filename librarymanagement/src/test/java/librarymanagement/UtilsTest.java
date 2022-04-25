/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

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
public class UtilsTest {
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
    @CsvSource({"Pham           Thi,Pham Thi", "  Pham Dieu   Que,Pham Dieu Que"})
    public void remove_WhitespaceTest(String s, String expected) {
        Assertions.assertEquals(expected, Utils.removeWhitespace(s));
    }
    
    @ParameterizedTest
    @CsvSource({"phamthidieuque10@gmail.com, true", "phamthi, false"})
    public void validate_EmailTest(String email, boolean expected) {
        EmailFormatUtils e = new EmailFormatUtils();
        Assertions.assertEquals(expected, e.validate_Email(email));
    }
}
