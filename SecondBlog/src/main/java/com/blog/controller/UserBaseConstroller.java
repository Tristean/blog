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
	//�õ��û���Ϣ
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public ModelAndView getUserModel(Integer id){
		ModelAndView modelAndView=new ModelAndView();
		User user=userBaseService.selectByPrimaryKey(id);
		modelAndView.addObject("userName",user.getName());
		modelAndView.addObject("userDesc",user.getDes());
		modelAndView.setViewName("user");
		return modelAndView;
	}
	//ͨ��Id�ĵ��û�
	@ResponseBody
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public AjaxResult getById(Integer id){
		User user=userBaseService.selectByPrimaryKey(id);
		if(user!=null)
			return AjaxResult.getOK(user);
		return null;
	}
	//�û�ע��
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public AjaxResult addUser(@RequestBody User user){
		if(user.getPassword()==null)
			throw new MyException("���벻��Ϊ��");
		user.setPassword(MD5Util.getMD5(user.getPassword().getBytes()));
		userBaseService.insertSelective(user);
		return AjaxResult.getOk();
	}
	//�û���¼
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public AjaxResult login(String username,String password){
		UserDetails userDetails=userBaseService.login(username, password);
		if(userDetails==null)
			return AjaxResult.getOK("�û������������", userDetails);
		return AjaxResult.getOK(userDetails);
	}
	//�û��������ϱ༭
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public AjaxResult edit(@RequestBody UserEditDetails editDetails){
		 User user=editDetails.getUser();
		 userBaseService.insertSelective(user);
		 return AjaxResult.getOK("�޸ĳɹ�", user);
	}
	//����Աɾ���û�
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public AjaxResult delete(@RequestBody User user){
		userRoleBaseService.deleteByPrimaryKey(user.getId());
		userBaseService.deleteByPrimaryKey(user.getId());
		return AjaxResult.getOK("ɾ���ɹ�");
	}
	//������Ա
	@ResponseBody
	@RequestMapping(value="/vip",method=RequestMethod.POST)
	public AjaxResult addVip(List<Integer>roleList,Integer userId){
		List<Integer>roleLists=userRoleBaseService.getRoleIdListByUserId(userId);
		if(!roleLists.contains(2)){
			return AjaxResult.getOK("�Ѿ��ǻ�Ա�����ظ�");
		}
		userRoleBaseService.addUserRoleList(userId, roleList);
		return AjaxResult.getOK("�����ɹ�");
	}
	//�û���ҳ��ѯ
	@ResponseBody
	@RequestMapping(value="/page",method=RequestMethod.POST)
	public AjaxResult selectByPage(int start,int size){
		Page<User>page=userBaseService.selectByPage(start, size);
		return AjaxResult.getOK(page);
	}
}
