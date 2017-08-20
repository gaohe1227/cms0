package com.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

 

@Controller
@RequestMapping("/fileUploadController/*")
public class FileUploadController implements ServletContextAware {

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	// 单文件上传
	@RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
	public void handleUploadFile(
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
/*		if (!file.isEmpty()) {
			String path = this.servletContext.getRealPath("/upload/"); // 获取本地存储路径
			// System.out.println(path);
			String fileName = file.getOriginalFilename();// 1048576
			System.out.println(file.getSize());
			Long fSize = file.getSize() / 1048576;
			if (fSize > 20) {
				 Params.getUserself(response, "outofsize");
			}
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			// System.out.println(fileType);
			String name = new Date().getTime() + fileType;
			File file2 = new File(path, name); // 新建一个文件
			try {
				file.getFileItem().write(file2); // 将上传的文件写入新建的文件中
			} catch (Exception e) {
				// e.printStackTrace();
				Params.getUserself(response, "fail");
			}
			String str = "/upload/" + name;
			 Params.getUserself(response, str);
		} else {
			// return "redirect:upload_error.jsp";
			 Params.getUserself(response, "fail");
		}*/
	}

	// 单文件上传
	@RequestMapping(value = "/uploadImg.do", method = RequestMethod.POST)
	@ResponseBody
	public String handleUploadData(
			@RequestParam("file") CommonsMultipartFile file) {
		/*if (!file.isEmpty()) {
			String path = this.servletContext.getRealPath("/upload/"); // 获取本地存储路径
			// System.out.println(path);
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			// System.out.println(fileType);
			String name = new Date().getTime() + fileType;
			File file2 = new File(path, name); // 新建一个文件
			try {
				file.getFileItem().write(file2); // 将上传的文件写入新建的文件中
			} catch (Exception e) {
				// e.printStackTrace();
				return "fail";
			}
			return "/upload/" + name;
		} else {
			// return "redirect:upload_error.jsp";
			return "fail";
		}*/
		return null;
	}

	// 多文件上传
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public void handleRequestInternal(MultipartHttpServletRequest request)
			throws Exception {
		List<MultipartFile> files = request.getFiles("file");
		// 将文件存入硬盘
		String uploadpath = request.getSession().getServletContext()
				.getRealPath("/");
		System.out.println(uploadpath);
		System.out.println(files.isEmpty());
		for (MultipartFile file : files) {
			if (file.isEmpty())
				continue;
			System.out.println("ok");
			System.out.println(file.getOriginalFilename());
			FileOutputStream fileOS = new FileOutputStream(uploadpath
					+ "/upload/" + file.getOriginalFilename());
			fileOS.write(file.getBytes());
			System.out.println(fileOS);
			fileOS.close();
		}
	}

	// excel上传
	@RequestMapping(value = "/uploadExcel.do", method = RequestMethod.POST)
	@ResponseBody
	public String excel(@RequestParam(value = "file") CommonsMultipartFile file) {
		/*if (file != null && !file.isEmpty()) {
			String path = this.servletContext.getRealPath("/upload/"); // 获取本地存储路径
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String name = new Date().getTime() + fileType;
			File excelFile = new File(path, name); // 新建一个文件
			try {
				file.getFileItem().write(excelFile); // 将上传的文件写入新建的文件中
				ImportExcelData importData = new ImportExcelData();
				importData.beginImport(excelFile);
				String[][] rs = importData.readExcelAddList(0);
				if (excelFile.exists()) {
					excelFile.delete();
				}
			} catch (Exception e) {
				// e.printStackTrace();
				return "fail";
			}
			return "success";
		} else {
			// return "redirect:upload_error.jsp";
			return "fail";
		}*/
		return null;
	}

}
