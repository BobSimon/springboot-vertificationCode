package com.dtyunxi.dtplatform.rest;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dtyunxi.dtplatform.dao.UserDAO;
import com.dtyunxi.dtplatform.domain.User;
import com.dtyunxi.dtplatform.service.IUserService;
import com.dtyunxi.dtplatform.service.impl.CustomGenericManageableCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@Autowired
	private CustomGenericManageableCaptchaService customGenericManageableCaptchaService;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("login")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	

	@RequestMapping("register")
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}
	
	@RequestMapping("userAdd")
	public ModelAndView doAdd(User user, String captcha, HttpServletRequest request) throws Exception {

		Boolean isResponseCorrect = imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha);
		if (isResponseCorrect) {
			userDAO.addUser(user);
			customGenericManageableCaptchaService.removeCaptcha(request.getSession().getId());
			ModelAndView mv = new ModelAndView("personal");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("register");
			return mv;
		}
	}
	
	@RequestMapping("personal")
	public ModelAndView index(String username,String password) throws Exception{
		if(userService.login(username, password)) {
			ModelAndView mv = new ModelAndView("personal");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("msg", "用户名或者密码错误");
			return mv;
		}
	}
	
	@RequestMapping("/checkEmail")
	public @ResponseBody int checkEmail(String email) throws Exception {
		return userService.checkEmailExist(email);
	}

	@RequestMapping("/checkUser")
	public @ResponseBody int checkUser(String username) throws Exception {
		System.out.println(username+"*******************************");
		return userService.checkUserExist(username);
	}

	@RequestMapping("/checkCaptcha")
	public @ResponseBody int checkCaptcha(String captcha, HttpServletRequest request) throws Exception {
		Boolean isResponseCorrect = imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha);

		if (isResponseCorrect == false) {
			return 0;
		} else {
			return 1;
		}
	}
}
