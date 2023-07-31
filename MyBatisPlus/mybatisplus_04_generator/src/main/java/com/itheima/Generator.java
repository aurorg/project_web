package com.itheima;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

public class Generator {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        DataSourceConfig datsource = new DataSourceConfig();
        datsource.setDriverName("com.mysql.cj.jdbc.Driver");
        datsource.setUrl("jdbc:mysql://localhost:3306/mybatisplus_db?serverTimezone=UTC");
        datsource.setUsername("root");
        datsource.setPassword("szl0905");

        autoGenerator.setDataSource(datsource);
        autoGenerator.execute();
    }
}
