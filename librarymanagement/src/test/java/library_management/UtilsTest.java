/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_management;

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
    @ParameterizedTest
    @CsvSource({"Pham           Thi,Pham Thi", "  Pham Dieu   Que,Pham Dieu Que"})
    public void remove_WhitespaceTest(String s, String expected) {
        Assertions.assertEquals(expected, Utils.remove_Whitespace(s));
    }
    
    @ParameterizedTest
    @CsvSource({"phamthidieuque10@gmail.com, true", "phamthi, false", "1@12378*, false"})
    public void validate_EmailTest(String email, boolean expected) {
        EmailFormatUtils e = new EmailFormatUtils();
        Assertions.assertEquals(expected, e.validate_Email(email));
    }
    
}
