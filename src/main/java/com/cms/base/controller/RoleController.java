package com.cms.base.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cms.base.model.Role;
import com.cms.base.service.RoleService;
import com.util.Result;
 

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 作用:
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	
	
	
	/**
	 * 列表管理
	 * @param roleName:角色名称
	 * @return
	 */
   @RequestMapping("/basic")
   public String basic(){
	   return "basic/role";
   }
	 
	
	
	
	
	/**
	 * 列表管理
	 * @param roleName:角色名称
	 * @return
	 */
   @RequestMapping("/list")
   @ResponseBody
   public List<Role> list(String roleName,Model model){
	   List<Role> list= this.roleService.list(roleName);
	 
	   model.addAttribute("list", list);
	   return list;
   }
   /**
	 * 编辑管理
	 * @param roleName:角色名称
	 * @return
	 */
 @RequestMapping(value="edit",method = RequestMethod.POST)
 @ResponseBody
  public Result edit (Role role) {  
	  
	  try{ 
			 int i=	 roleService.edit(role);
			 if(i>0){
				 return  new Result(1, "操作成功", null);
			 }
			 }catch(Exception e){
					logger.error(e.getMessage());
				
					 return  new Result(0, "操作失败,后台报错了", e.getMessage());
			 }
		 return  new Result(0, "操作失败", null);
	  
  }
  
 
 
 /*	*//**
	 * 按Id查询
	 * 
	 * @param roleVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectRoleById.do", method = RequestMethod.POST)
	@ResponseBody
	public Role selectRoleById(Role roleVo)   { // 查询详细信息
		return roleService.selectById(roleVo.getId());
	}

	/**
	 * 删除角色
	 * 
	 * @param roleVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteRole.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteAllRole(Integer id)  {
		try{
	  Integer i=this.roleService.deleteById(id);
	  if(i>0){
		  return true;
	  }
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	  return false;
		 
	}


	 
	 /**
	  * 查询全部角色
	  * @param role
	  * @param paper
	  * @param page:当前页
	  * @param rows:每页显示条数
	  * @return
	  *//*
	@RequestMapping(value = "/selectAllRole.do", method = RequestMethod.POST)
	@ResponseBody
	public Map selectAllRole(Role role,Paper<Role> paper,Integer page,Integer rows) {  
		try {
			Map res=new HashMap();
			paper.setOffset(page);
			Map<String, Object> querycount = new HashMap<String, Object>();
			Paper<Role> rolePage=this.roleService.selectListPage(paper, querycount);
			res.put("total", rolePage.getCount()); 
			res.put("rows", rolePage.getDatas());
			res.put("page", rolePage.getOffset());
			return res;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}*/
	/**
	 * 查找菜单
	 * 
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/selectFunction.do")
	public String selectFunction(Model model, Functionrole vo)
			throws Exception {
		List menuRs = functionService.selectList(null);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roleid", vo.getRoleid());
		List roleMenu = functionroleService.selectList(map); 
		model.addAttribute("menuRs", menuRs);
		model.addAttribute("roleMenu", roleMenu);
		model.addAttribute("roleId", vo.getRoleid());
		return "role/functionlist";
	}*/

	 
	 
	
	
	
	
	
/*	*//**
	 * 分配菜单
	 * 
	 * @param vo
	 * @return
	 *//*
	@RequestMapping(value = "/addFunctionRole.do")
	@ResponseBody
	public String addFunctionRole(Functionrole vo) {
		String message = "";
		try {  
			 int i=this.functionroleService.addFunctionRole(vo);//批量插入数据 
			message = "ok";
		} catch (Throwable e) {
			message = "failed";
		}
		return message;
	}

	
 
 
 
 */
 
 
 
 
 
 
 
 
 
 
 
}
