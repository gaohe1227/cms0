package com.cms.files.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cms.files.dao.FilesRespository;
import com.cms.files.mapper.FilesMapper;
import com.cms.files.model.Files;
import com.common.UUIDUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class FilesService {
	@Autowired
	private FilesMapper filesMapper;
	@Autowired
	private FilesRespository filesRespository;
	@RequestMapping("upFile")
	private String upFile(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
    	String uploadPath =req.getSession().getServletContext().getRealPath("/file/");// 上传文件的目录 
		//System.out.println(req.getSession().getServletContext().getRealPath("/"));
    	 HttpSession session = req.getSession();//使用request对象的getSession()获取session
	     
	            // Create a factory for disk-based file items  
	            DiskFileItemFactory factory = new DiskFileItemFactory();  // 实例化硬盘文件工厂
	           
	            // Set factory constraints  
	            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb  // 存放临时文件的内存大小
	            File tempPathFile=new File("d:/tempPathFile");
	            if(!tempPathFile.exists()){
	            	tempPathFile.mkdirs();
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
	 
				try {
					 List<FileItem>	items = upload.parseRequest(req);
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
				} catch (  Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 得到所有的文件  
	           
	            System.out.print("upload succeed");  
	            
	    
	

	}
				return null;
	
	
	}

	public List<Files> list(String filesName, Integer currentPage) {
		// TODO Auto-generated method stub
		
		   if(StringUtils.isNotBlank(filesName)){
			   filesName="%"+filesName.trim()+"%";
				return filesMapper.findAllByFilesName(filesName);
			} 
			return filesMapper.findAll();
	}

	/**
	 * @Title: upFile
	 * @Description: TODO
	 * @param @param file
	 * @param @param resp
	 * @param @param basePath
	 * @param @param dataStr
	 * @param @param jsonObject
	 * @param @throws UnsupportedEncodingException
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */ 
	public void upFile(MultipartFile file, HttpServletResponse resp, String basePath, String dataStr,
			JSONObject jsonObject) throws UnsupportedEncodingException, IOException {
		String fileName;
		String origname = new String(file.getOriginalFilename().getBytes("iso-8859-1"), "utf-8");
		 
		jsonObject.put("filename", file.getOriginalFilename());
		System.out.println( "process file{}:" + origname);
		String filetype = "";
		if (StringUtils.isNotBlank(origname) && origname.lastIndexOf(".") != -1) {
			filetype = origname.substring(origname.lastIndexOf("."), origname.length());
		}
		String uuid = UUIDUtils.getUID();
		fileName = uuid + filetype;
		File target=new  File(basePath +java.io.File.separator + dataStr, fileName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), target);
		System.out.println("上传文件完成");
		jsonObject.put("code", 200);
		jsonObject.put("path", new String(dataStr +java.io.File.separator + fileName)); 
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(jsonObject.toString());
		printWriter.close();
		Files files=new Files();
		files.setOrigname(origname);
		files.setType(filetype.substring(1));
		files.setPath(target.getPath());
		files.setName(fileName);
		filesRespository.save(files);
	}

	/*
	 * @Autowired private FileDao dao;
	 * 
	 * @Autowired private HttpServletRequest request;
	 *//**
		 * 方法描述：初始化dao，覆盖BaseServiceImpl类中的泛型dao 创建人：mail.zhangyong@gmail.com
		 * 创建时间：2016年4月9日 8:59:08
		 **//*
			 * @Override public BaseDao<File> getBaseDao(){ return dao; }
			 * 
			 * @Override public int delete(File query) { // TODO Auto-generated
			 * method stub return super.delete(query); String
			 * basePath=request.getSession().getServletContext().getRealPath(
			 * "/upload"); if(StringUtils.isNotBlank(query.getId())){
			 * query=this.dao.selectById(query.getId()); java.io.File
			 * pathfile=new
			 * java.io.File(basePath+java.io.File.separator+query.getPath());
			 * if(pathfile.exists()){ pathfile.delete(); } java.io.File
			 * swffile=new
			 * java.io.File(basePath+java.io.File.separator+query.getSwfpath());
			 * if(swffile.exists()){ swffile.delete(); } } return
			 * dao.delete(query);
			 * 
			 * }
			 * 
			 * @Override public int insert(File file) { // TODO Auto-generated
			 * method stub Indexfiled field =
			 * LuceneContext.file2Field(file);//转化为存储域 if(null!=field){ try {
			 * LuceneContext.getInstance().insertIndex(field);//添加索引 } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }//添加索引 } return this.dao.insert(file); }
			 */
}
