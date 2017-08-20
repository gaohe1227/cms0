package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.Iterator;  
import java.util.List;  
 /**
  * 
  * Description:上传案例
  * @author:高鹤
  * @date:2017年8月20日
  */
@WebServlet("/FilesServlet")
public class FilesServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    File tempPathFile;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String uploadPath =req.getSession().getServletContext().getRealPath("/file/");// 上传文件的目录 
		//System.out.println(req.getSession().getServletContext().getRealPath("/"));
    	 HttpSession session = req.getSession();//使用request对象的getSession()获取session
		 try {  
	            // Create a factory for disk-based file items  
	            DiskFileItemFactory factory = new DiskFileItemFactory();  
	           
	            // Set factory constraints  
	            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb  
	            factory.setRepository(tempPathFile);// 设置缓冲区目录  
	  
	            // Create a new file upload handler  
	            ProgressListener progressListener = new ProgressListener(){
	            	   public void update(long pBytesRead, long pContentLength, int pItems) {
	            	       System.out.println("文件个数" + pItems);
	            	       if (pContentLength == -1) {
	            	           System.out.println("正上传：" + pBytesRead );
	            	       } else {
	            	           System.out.println("正上传" + pBytesRead + " of " + pContentLength
	            	                              + " bytes have been read.");
	            	       
	            	       }
	            	   }
	            	};
	            	
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            
	            upload.setProgressListener(progressListener);
	            
	            // Set overall request size constraint  
	            upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB  
	  
	            List<FileItem> items = upload.parseRequest(req);// 得到所有的文件  
	            Iterator<FileItem> i = items.iterator();  
	            while (i.hasNext()) {  
	                FileItem fi = (FileItem) i.next();  
	                String fileName = fi.getName();  
	                if (fileName != null) {  
	                    File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题  
	                    File savedFile = new File(uploadPath, fullFile.getName());  
	                    fi.write(savedFile);  
	                    System.out.println(uploadPath);
	                }  
	            }  
	            System.out.print("upload succeed");  
	        } catch (Exception e) {  
	        	System.out.println("出现错误");
	        }  
	}

	

}
