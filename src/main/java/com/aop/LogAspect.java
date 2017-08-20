package com.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cms.base.dao.CmsLogRepository;
import com.cms.base.model.CmsLog;
import com.cms.base.model.User;

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 作用:操作日志记录
 *
 */
@Aspect
@Component
public class LogAspect {
	@Autowired
	private CmsLogRepository cmsLogRepository;
	ThreadLocal<Date> t=new ThreadLocal<Date>(); 
	@Before("execution(*  com.cms.*.controller.*.*(..))")
	 public void doBefore(JoinPoint joinPoint) {
       try {
  
           // 接收到请求，记录请求内容
           ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
           HttpServletRequest request = attributes.getRequest();
           String beanName = joinPoint.getSignature().getDeclaringTypeName();
           String methodName = joinPoint.getSignature().getName();
           String uri = request.getRequestURI(); 
           String sessionId = request.getSession().getId();
           Long userid=0L;
           User loginuser =   (User) request.getSession().getAttribute("loginuser");
           if(loginuser!=null){
        	   userid=loginuser.getUserid();
           }
           String method = request.getMethod();
           String remoteAddr=request.getRemoteAddr();
           String params = "";
       
/*           System.out.println("uri=" + uri + "; beanName=" + beanName + "; user=" + user
                   + "; methodName=" + methodName + "; params=" + params); */
           Date date=new Date();
           t.set(date);
           CmsLog cmslog=new CmsLog( uri, remoteAddr, userid, date, null, null,1);
            cmsLogRepository.save(cmslog);
       } catch (Exception e) {
       e.printStackTrace();
       }
   }

	@After("execution(*  com.cms.*.controller.*.*(..))")
	 public void doAfter(JoinPoint joinPoint) {
      try {
 
          // 接收到请求，记录请求内容
          ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
          HttpServletRequest request = attributes.getRequest();
          String beanName = joinPoint.getSignature().getDeclaringTypeName();
          String methodName = joinPoint.getSignature().getName();
          String uri = request.getRequestURI(); 
          String sessionId = request.getSession().getId();
          Long userid=0L;
          User loginuser =   (User) request.getSession().getAttribute("loginuser");
          if(loginuser!=null){
       	   userid=loginuser.getUserid();
          }
          String method = request.getMethod();
          String remoteAddr=request.getRemoteAddr();
          String params = "";
      
/*           System.out.println("uri=" + uri + "; beanName=" + beanName + "; user=" + user
                  + "; methodName=" + methodName + "; params=" + params); */
          Date start=t.get();
          Date end=new Date(); 
          CmsLog cmslog=new CmsLog( uri, remoteAddr, userid, start, end, "耗时"+(end.getTime()-start.getTime())+"毫秒", 0);
           cmsLogRepository.save(cmslog);
      } catch (Exception e) {
      e.printStackTrace();
      }
  } 
	
	
 
    // @Order(5)

}
