package com.dtyunxi.dtplatform.rest;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.dtplatform.common.RestResponse;
import com.dtyunxi.dtplatform.service.IUserService;
import com.octo.captcha.service.image.ImageCaptchaService;

@RestController
@RequestMapping("api/v1/user")
public class LoginController {
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ImageCaptchaService imageCaptchaService;
	
	@RequestMapping("/login")
	public RestResponse doAdd(String username, String password, String captcha, HttpServletRequest request) throws Exception {

		RestResponse response = new RestResponse();
		Boolean isResponseCorrect = imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha);
		if (isResponseCorrect) {
			if(userService.login(username, password)){
				return response.success();
			}else{
				return response.failure("用户或密码错误1");
			}
		}
		return response.failure("验证码错误！");
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
