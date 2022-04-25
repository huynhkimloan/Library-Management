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
import com.nhom2.services.Reserve;
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

public class ReserveTest {
 
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
    @CsvSource({"2017, false","2016, true", "2020, true", "2021, false"})
    public void ktnn(int y, boolean expected) throws SQLException{
        Reserve rds = new Reserve();
        Assertions.assertEquals(expected, rds.kiemTraNamNhuan(y));
    }
    
    @ParameterizedTest
    @CsvSource({"1969, false","2021, true", "2022, true", "2025, false"})
    public void kiemTraSach(boolean expected) throws SQLException{
        Reserve rds = new Reserve();
        Assertions.assertEquals(expected, rds.kiemTraSachTonTai());
    }
}
