package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * Description:文件上传
 * @author:高鹤
 * @date:2017年9月18日
 */
public class UploadFiles {

	
	public Map toUpload( MultipartFile file, int ftype,HttpServletRequest request) {
		
		Map json = new HashMap();

		
		if (file == null) {
			json.put("status", false);
			json.put("msg", "file is null");
		}
		else {
			try {
				String separator = System.getProperty("file.separator");
				String filePath = ""; 
				HttpSession session = request.getSession();
				ServletContext servletContext = session.getServletContext();
				String path = servletContext.getRealPath("\\");
				
				String dir = DateUtils.dateToStr(new Date(), "yyyy" + separator + "MM" + separator + "dd");
				

				filePath = String.format("%supload%s%s%s%s", separator, separator, ftype, separator, dir);

				File folder = new File(path + filePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				String tmpName = file.getOriginalFilename();
				String fileName = String.format("%s%s%s", filePath, separator, tmpName);
				FileOutputStream fos = new FileOutputStream(new File(path + fileName));
				byte[] b = new byte[(int) file.getBytes().length];
				file.getInputStream().read(b);
				fos.write(b);
				fos.close();

				json.put("status", true);
				json.put("msg", "ok");
				json.put("path", fileName.replace(separator, "\\"));
			}
			catch (Exception e) {
				System.out.println(e);
				json.put("status", false);
				json.put("msg", "system error");
			}
		}
		return json;
	}


}
