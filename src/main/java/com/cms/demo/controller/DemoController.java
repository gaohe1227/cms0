package com.cms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@Autowired
    private JavaMailSender mailSender;
   
    /**
     * 发送邮件
     * 修改application.properties的用户，才能发送。
     */
     @RequestMapping("sendSimpleEmail")
    public String sendSimpleEmail(){
       SimpleMailMessage message = new SimpleMailMessage();
      
       message.setFrom("904724283@qq.com");//发送者.
       message.setTo("1098466221@qq.com");//接收者.
       message.setSubject("title");//邮件主题.
       message.setText("content");//邮件内容.
       mailSender.send(message);
        
       return "sendSimpleEmail";
    }
}
