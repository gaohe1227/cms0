package com.cms.base.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownController {
	private static final Logger logger = LoggerFactory.getLogger(DownController.class);
	/**
	 * 下载文件
	 * 
	 * @param path:文件路径
	 * @return
	 */
	@RequestMapping("dowmFile")
	public void downFile(@RequestParam("path")String path,HttpServletRequest httpServletRequest,HttpServletResponse response) {

		FileInputStream in = null;
		ServletOutputStream out = null;
		try {
			String basePath = httpServletRequest.getSession().getServletContext().getRealPath("/"); 
			out = response.getOutputStream();
			response.setCharacterEncoding("UTF-8");
			String fileName = path.substring(path.lastIndexOf("/") + 1);
			// response.setContentType("application/ ;charset=UTF-8");

			File file = new File(basePath + File.separator + path);
			if (StringUtils.isBlank(path) || !file.exists()) {
				basePath = "/opt/rose";
				file = new File(basePath + File.separator + path);
				if (StringUtils.isBlank(path) || !file.exists()) {
					response.reset();
					response.setHeader("Content-type", "text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("文件找不到了");
					return;
				}

			}

			response.setHeader("Content-type", "application/octet-stream");
			// response.setCharacterEncoding("UTF-8");
			// fileName= URLEncoder.encode(fileName, "UTF-8");
			fileName = new String(fileName.getBytes(), "iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 设置响应头
			in = new FileInputStream(file);
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		finally {

			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();// 关闭输出流
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭文件输入流
		}

	}
}
