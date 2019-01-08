package com.accelerator.demo.standalone.shardingsphere;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DefaultTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        int i = 0;
        while (true) {
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.executeQuery("select distinct  username from t_user");
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String string = resultSet.getString(1);
                    System.out.println(i + "=" + string);
                }
            }
            i++;
        }
    }

}
