package com;

 
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 作用:启动类
 *
 */
@SpringBootApplication
@ServletComponentScan // @ServletComponentScan为扫描相应Servlet的包
@MapperScan("com.cms.*.mapper")//扫描mapper包
@EnableTransactionManagement // 启注解事务管理
public class StartApplication extends SpringBootServletInitializer {
 
   private static final Logger logger = LoggerFactory.getLogger(StartApplication.class);
   
   
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       return application.sources(StartApplication.class);
   }
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(StartApplication.class, args);
		 logger.info("启动成功");

	}
	
	
	/**
     * 配置DataSource数据源
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/cms");
        druidDataSource.setMaxActive(100);
        druidDataSource.setFilters("stat,wall");
        druidDataSource.setInitialSize(10);
        return druidDataSource;
    }
 
 
}
