package com.taotao.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ��¼��Controller
 */
@Controller
public class LoginController {
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	@ResponseBody
	public String Login(String username,String password){
		
		
		return username + password;
	}
}
