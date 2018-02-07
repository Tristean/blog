package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blog.ajax.AjaxResult;
import com.blog.dto.input.UserEditDetails;
import com.blog.dto.output.UserDetails;
import com.blog.entity.User;
import com.blog.exception.MyException;
import com.blog.service.imp.BaseRoleContext;
import com.blog.services.UserBaseService;
import com.blog.util.MD5Util;
import com.blog.util.Page;
import com.blog.util.StringUtil;

@Controller("userController")
@RequestMapping("/user")
public class UserBaseConstroller extends BaseConstroller {
	@Autowired
	UserBaseService userBaseService;
	@Autowired
	BaseRoleContext userRoleBaseService;
	//得到用户信息
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public ModelAndView getUserModel(Integer id){
		ModelAndView modelAndView=new ModelAndView();
		User user=userBaseService.selectByPrimaryKey(id);
		modelAndView.addObject("userName",user.getName());
		modelAndView.addObject("userDesc",user.getDes());
		modelAndView.setViewName("user");
		return modelAndView;
	}
	//通过Id的到用户
	@ResponseBody
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public AjaxResult getById(Integer id){
		User user=userBaseService.selectByPrimaryKey(id);
		if(user!=null)
			return AjaxResult.getOK(user);
		return null;
	}
	//用户注册
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public AjaxResult addUser(@RequestBody User user){
		if(user.getPassword()==null)
			throw new MyException("密码不能为空");
		user.setPassword(MD5Util.getMD5(user.getPassword().getBytes()));
		userBaseService.insertSelective(user);
		return AjaxResult.getOk();
	}
	//用户登录
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public AjaxResult login(String username,String password){
		UserDetails userDetails=userBaseService.login(username, password);
		if(userDetails==null)
			return AjaxResult.getOK("用户名或密码错误", userDetails);
		return AjaxResult.getOK(userDetails);
	}
	//用户个人资料编辑
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public AjaxResult edit(@RequestBody UserEditDetails editDetails){
		 User user=editDetails.getUser();
		 userBaseService.insertSelective(user);
		 return AjaxResult.getOK("修改成功", user);
	}
	//管理员删除用户
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public AjaxResult delete(@RequestBody User user){
		userRoleBaseService.deleteByPrimaryKey(user.getId());
		userBaseService.deleteByPrimaryKey(user.getId());
		return AjaxResult.getOK("删除成功");
	}
	//升级会员
	@ResponseBody
	@RequestMapping(value="/vip",method=RequestMethod.POST)
	public AjaxResult addVip(List<Integer>roleList,Integer userId){
		List<Integer>roleLists=userRoleBaseService.getRoleIdListByUserId(userId);
		if(!roleLists.contains(2)){
			return AjaxResult.getOK("已经是会员无需重复");
		}
		userRoleBaseService.addUserRoleList(userId, roleList);
		return AjaxResult.getOK("升级成功");
	}
	//用户分页查询
	@ResponseBody
	@RequestMapping(value="/page",method=RequestMethod.POST)
	public AjaxResult selectByPage(int start,int size){
		Page<User>page=userBaseService.selectByPage(start, size);
		return AjaxResult.getOK(page);
	}
}
