package com.cms.files.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.cms.files.model.Files;
import com.cms.files.service.FilesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("files")
public class FilesController {

	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

	@Autowired
	private FilesService filesService;

	/**
	 * 列表管理
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/basic")
	public String basic() {
		return "data/files";
	}

	@RequestMapping("/addFile")
	public String addFile() {
		return "data/addFile";
	}

	@RequestMapping(value = "/upFile", method = RequestMethod.POST)
	public @ResponseBody void upFile(@RequestParam("file") MultipartFile file, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			String basePath = req.getSession().getServletContext().getRealPath("/upload");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String dataStr = sf.format(new Date());
			String fileName = null;
			JSONObject jsonObject = new JSONObject();
			if (!file.isEmpty() && file.getSize() < (Long.valueOf(1024) * 1024 * 1024)) {
				filesService.upFile(file, resp, basePath, dataStr, jsonObject);
			} else {
				jsonObject.put("code", 500);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write(jsonObject.toString());
				printWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 列表管理
	 * 
	 * @param filesName:
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JSONObject list(@RequestParam(value = "filesName", required = false) String filesName,
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "rows", defaultValue = "10") Integer pageSize) {
		Page page = PageHelper.startPage(currentPage, pageSize);
		List<Files> list = this.filesService.list(filesName, currentPage);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", page.getTotal());
		jsonObject.put("rows", list);
		jsonObject.put("page", currentPage);

		return jsonObject;
	}

	/**
	 * 文件上传具体实现方法（单文件上传）(上出文件不能大于10M)
	 *
	 * @param file
	 * @return
	 * 
	 * @author 单红宇(CSDN CATOOP)
	 * @create 2017年3月11日
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest req, HttpServletResponse resp) {
		try {
			String basePath = req.getSession().getServletContext().getRealPath("/upload");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String dataStr = sf.format(new Date());
			String fileName = null;
			JSONObject jsonObject = new JSONObject();
			if (!file.isEmpty() && file.getSize() < (Long.valueOf(1024) * 1024 * 1024)) {
				filesService.upFile(file, resp, basePath, dataStr, jsonObject);
			} else {
				jsonObject.put("code", 500);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write(jsonObject.toString());
				printWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
	 *
	 * @param request
	 * @return
	 * 
	 * @author 单红宇(CSDN CATOOP)
	 * @create 2017年3月11日
	 */
	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	public @ResponseBody String batchUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}
 //-----------------------------------以下为redis缓存测试
	 
	@RequestMapping("/test")
	public @ResponseBody String test() {
		System.out.println("==========================================>test02");
		Files loaded = filesService.findById(1);
		System.out.println("loaded=" + loaded);
		Files cached = filesService.findById(1);
		System.out.println("cached=" + cached);
		loaded = filesService.findById(2);
		System.out.println("loaded2=" + loaded);
		return "ok0111111test";
	}

    @RequestMapping("/delete")
    public@ResponseBody String delete(long id){
    	filesService.deleteFromCache(id);
        return"ok";
    }
   
    @RequestMapping("/test1")
    public@ResponseBody String test1(){
    	filesService.test();
        System.out.println("DemoInfoController.test1()");
        return"ok";
    }
}