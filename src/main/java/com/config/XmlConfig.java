package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * Title:XmlConfig
 * Description:引入xml文件案例
 * Company:chaoxing
 * @author:高鹤
 * @date:2017年7月3日
 */
@Configuration
@ImportResource(locations={"classpath:application-bean.xml"})
public class XmlConfig {

}
