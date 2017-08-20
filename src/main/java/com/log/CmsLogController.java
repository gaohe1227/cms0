package com.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.mapper.CmsLogMapper;
import com.cms.base.model.CmsLog;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@RequestMapping("/cmsLog")
public class CmsLogController {
	 @Autowired
	 private CmsLogMapper cmsLogMapper;
	/**
	 * 列表管理
	 * @param roleName:角色名称
	 * @return
	 */
   @RequestMapping("/basic")
   public String basic(){
	   return "basic/cmsLog";
   }
   /**
	 * 列表管理
 
	 * @return
	 */
  @RequestMapping("/list")
  @ResponseBody
  public JSONObject list (Model model,@RequestParam(value="page",defaultValue="1")Integer currentPage) throws Exception{	 
	    Page page=PageHelper.startPage(currentPage, 10);
	    
	   List<CmsLog> list=cmsLogMapper.lists();
	   JSONObject jsonObject=new JSONObject();
	   jsonObject.put("total", page.getTotal());
	   jsonObject.put("rows", list);
	   jsonObject.put("page", currentPage);
	   
	   return jsonObject;
  }
  
  
   
}
