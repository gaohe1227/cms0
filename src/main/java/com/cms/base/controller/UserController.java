package com.cms.base.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.model.User;
import com.cms.base.service.RoleService;
import com.cms.base.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Result;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	 @Autowired
	private UserService userService; 
	 @RequestMapping("/basic")
	   public String basic(){
		   return "basic/user";
	   }
		/**
		 * 列表管理
		 * @param userName:用户名称
		 * @return
		 */
	   @RequestMapping("/list")
	   @ResponseBody
	   public JSONObject list(@RequestParam(value="userName",required=false)String userName,@RequestParam(value="page",defaultValue="1")Integer currentPage,@RequestParam(value="rows",defaultValue="10")Integer pageSize){
		   Page page=PageHelper.startPage(currentPage, pageSize);
		   List<User> list= this.userService.list(userName,currentPage);  
		   JSONObject jsonObject=new JSONObject();
		   jsonObject.put("total", page.getTotal());
		   jsonObject.put("rows", list);
		   jsonObject.put("page", currentPage);
		   
		   return jsonObject;
	   }
	 
	   /**
		 * 编辑管理
		 * @param roleName:角色名称
		 * @return
		 */
	 @RequestMapping(value="edit",method = RequestMethod.POST)
	 @ResponseBody
	  public Result edit (User user){  
		  
		  try{ 
				 int i=	 userService.edit(user);
				 if(i>0){
					 return  new Result(1, "操作成功", null);
				 }
				 }catch(Exception e){
					 System.err.println(e.getMessage());
						logger.error(e.getMessage());
						 return  new Result(0, "操作失败,后台报错了", e.getMessage());
				 }
			 return  new Result(0, "操作失败", null);
		  
	  }
	 
	 
		/**
		 * 删除角色
		 * 
		 * @param roleVo
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
		@ResponseBody
		public boolean deleteUser(Long userid)  {
			try{
		  Integer i=this.userService.deleteById(userid);
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
	 * 验证
	 * 
	 * @param request
	 * @param model
	 * @return
	 *//*
	@RequestMapping("/systemOut.do")
	@ResponseBody
	public String systemOut(HttpServletRequest request, Model model) {
		request.getSession().invalidate();
		return "logout";
	}

	 
	 *//**
	  * 验证用户名密码是否唯一
	  * @param userVo
	  * @param request
	  * @return
	  *//*
		@RequestMapping(value = "/loginVal.do") 
		public @ResponseBody Integer loginVal(User user, HttpServletRequest request) {
			try {
				Integer count = userService.selectCountLogin(user);
				if (count==1) {
					request.getSession().setAttribute("loginUser", user);
					log.info("用户"+user.getUsercode()+"登陆成功");
				}
				return count;
			} catch (Exception e) {
				log.error(e.getMessage());
				 
			}
			return 0;

		}
		*//**
		 * 用户登陆
		 * @param request
		 * @param userVo
		 * @return
		 *//*
		@RequestMapping(value = "/loginBackstage.do", method = RequestMethod.POST)
		public String loginBackstage(HttpServletRequest request, User user) {
			try {
				if (user.getUsercode() == null || "".equals(user.getUsercode().trim())) {
					user = (User) request.getSession().getAttribute("loginUser");
					request.getSession().removeAttribute("loginUser");
				}
				user = userService.findUser(user);
				request.getSession().setAttribute("loginUser", user);
				
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
				return null;
			} 
			return "main/main";
		}
		*//**
		 * 功能列表
		 * @param model
		 * @param request
		 * @return
		 *//*
		@RequestMapping(value = "/showTreeMenu.do", method = RequestMethod.GET)
		public String zTreeShow(Model model, HttpServletRequest request) {
			try {
				User	user = (User) request.getSession().getAttribute("loginUser");
				String userid=user.getUserid();
				List<Function> parentList = userService.selectParentZtree(userid); // 根据用户Id,查询该用户拥有的一级菜单
				List<String> childList = userService.selectChildZtree(parentList,request, userid); // 根据用户ID,一级菜单获取该用户拥有的二级,三级菜单
				model.addAttribute("pList", parentList);
				model.addAttribute("cList", childList);
				
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
				return null;
			}
			return "main/west";
		}
		 *//**
		  * 获取用户列表
		  * @param pager
		  * @param page:当前页
		  * @param rows:每页显示条数
		  * @return
		  *//*
		@RequestMapping(value = "/selectAllUser.do", method = RequestMethod.POST)
		@ResponseBody
		public Map selectAllUser(Paper<User> paper ,Integer page,Integer rows) {
			try {
				Map res=new HashMap();
				paper.setOffset(page);
				paper.setSize(rows);
				Map<String, Object> querycount = new HashMap<String, Object>();
				Paper<User> userPage=this.userService.selectListPage(paper, querycount); 
				res.put("total", userPage.getCount()); 
				res.put("rows", userPage.getDatas());
				res.put("page", userPage.getOffset());
				return res;
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}

		}
		*//**
		 * 插入
		 * 
		 * @param user
		 * @param response
		 * @throws Exception
		 *//*
		@RequestMapping(value = "/insertUser.do", method = RequestMethod.POST)
		@ResponseBody
		public void insertUser(User user, HttpServletResponse response) {
			try {
				user.setUsername(new String(user.getUsername().getBytes("iso-8859-1"),"utf-8"));
				user.setUsercode(new String(user.getUsercode().getBytes("iso-8859-1"),"utf-8"));
				user.setUserid(Params.getCode());
			int i=	userService.insert(user);
			if(i>=1){
				 Params.getSuccess(response);
			}else{
				 Params.getFail(response);
			}
			      
			} catch (Exception e) {
				log.error(e.getMessage());
				 Params.getFail(response);
			}

		}
		*//**
		 * 按Id查询
		 * 
		 * @param userVo
		 * @return
		 * @throws Exception
		 *//*
		@RequestMapping(value = "/selectUserById.do", method = RequestMethod.POST)
		@ResponseBody
		public User selectUserById(User vo) {
			try {
				return userService.selectById(vo.getUserid());
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}

		}

		*//**
		 * 修改
		 * 
		 * @param userVo
		 * @param response
		 * @throws Exception
		 *//*
		@RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
		@ResponseBody
		public void updateUser(User userVo, HttpServletResponse response) {
			try {
				userVo.setUsername(new String(userVo.getUsername().getBytes("iso-8859-1"),"utf-8"));
				userVo.setUsercode(new String(userVo.getUsercode().getBytes("iso-8859-1"),"utf-8"));
				int i = userService.updateById(userVo);
				if (i >= 1) {
					Params.getSuccess(response);
				}
	
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
				Params.getFail(response);
			}
			Params.getFail(response);
		}

		*//**
		 * 查询
		 * 
		 * @return
		 * @throws Exception
		 *//*
		@RequestMapping(value = "/selectAllRolesName.do", method = RequestMethod.POST)
		@ResponseBody
		public List<Role> selectAllRolesName() {
			try {
				List<Role> role = roleService.selectList(null);
				if (role.size() == 0) {
					role = null;
				}
				return role;
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			return null;
	
		}
		*//**
		 * 查询
		 * 
		 * @param request
		 *            userId用户ID
		 * @param vo
		 * @return
		 *//*
		@RequestMapping(value = "/selectRoleName.do", method = RequestMethod.POST)
		@ResponseBody
		public Map selectRoleName(HttpServletRequest request,UserRole userrole ) {
			Map<String, Object> res = new HashMap<String, Object>();  
			String userdata=null;
			try {
				UserRole	role = userRoleService.selecByUserRole(userrole);
				if(role!=null){
					userdata=role.getRoleid();
				}
			} catch (Exception e) { 
				e.printStackTrace();
			}
			res.put("userdata", userdata); 
			return res;
		}
		*//**
		 * 分配角色
		 * 
		 * @param request
		 *            roleid角色ID userid用户ID
		 * @param vo
		 * @return
		 *//*
		@RequestMapping(value = "/addRolesName.do", method = RequestMethod.POST)
		@ResponseBody
		public void addRolesName(HttpServletRequest request, UserRole vo,
				HttpServletResponse response) {
			try {  
			 int i=	userRoleService.insert(vo);
			 Params.getUserself(response, "ok");
			} catch (Throwable e) {
				log.error(e.getMessage()); 
			 Params.getUserself(response, "failed");
			}
		}
	 
		*//**
		 * 删除
		 * 
		 * @param vo
		 * @param response
		 * @return
		 * @throws Exception
		 *//*
		@RequestMapping(value = "/deleteAllUser.do", method = RequestMethod.POST)
		@ResponseBody
		public String deleteAllUser(User vo, HttpServletResponse response) {
			try {
		         this.userService.delete(vo);
				return  "ok";
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}

		}*/

	
	

}
