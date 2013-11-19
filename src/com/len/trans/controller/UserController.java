package com.len.trans.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.len.trans.pojo.User;
import com.len.trans.service.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	

	/**
	 * 进入新增
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_new")
	public ModelAndView _new(HttpServletRequest request,HttpServletResponse response)throws Exception{		
		return new ModelAndView("/user-add");
	}
	
	/**
	 * 新增
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,User user)throws Exception{
		System.out.println("add***************");
		userService.add(user);
		return new ModelAndView("/success","user",user);
	}
	
	@RequestMapping(value="/UserLogin",method=RequestMethod.POST)
	public String UserLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam String userName, @RequestParam String userPwd){
		HttpSession session = request.getSession();
		//判断是否存在用户，且密码正确
		//如果正确
		//session.setAttribute("userName", userName);
		//如果错误
		session.setAttribute("userName", "_null");
		response.setContentType("text/html; charset=gb2312");

		ServletContext sc = request.getServletContext();

		return "pphs";
	
	}
	@RequestMapping(value="/UserLogout")
	public void UserLogout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		//判断是否存在用户，且密码正确
		//如果正确
		//session.setAttribute("userName", userName);
		//如果错误
		session.setAttribute("userName", null);
		
		response.setContentType("text/html; charset=gb2312");

		ServletContext sc = request.getServletContext();

		RequestDispatcher rd = null;

		rd = sc.getRequestDispatcher("/"); //定向的页面

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
