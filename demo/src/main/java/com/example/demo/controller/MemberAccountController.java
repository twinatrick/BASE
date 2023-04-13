package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.MemberAccount;

@Controller
public class MemberAccountController {

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(@ModelAttribute MemberAccount memberAccount) {
		
		return "login";
	}
	
}