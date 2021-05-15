package com.accelerator.demo.standalone.shardingsphere;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DefaultTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.afterPropertiesSet();

        int i = 0;
        while (true) {
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.executeQuery("select * from t_dict");
                stmt.executeQuery("select * from t_order");
                stmt.executeQuery("select * from t_user");
            }
            i++;
        }
    }

}
