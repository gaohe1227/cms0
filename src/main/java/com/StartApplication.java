package com;

 
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
	
	
	/**----------------------------------配置druid连接池/
	
	
	
	
	/**
     * 注册DruidServlet
     *
     * @return
     *//*
    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    *//**
     * 注册DruidFilter拦截
     *
     * @return
     *//*
    @Bean
    public FilterRegistrationBean duridFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        //设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    *//**
     * 配置DataSource
     * @return
     * @throws SQLException
     *//*
    @Bean
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("qq123456");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test1");
        druidDataSource.setMaxActive(100);
        druidDataSource.setFilters("stat,wall");
        druidDataSource.setInitialSize(10);
        return druidDataSource;
    }*/

}