package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;



@Controller
public class SpringBootHelloWorld {

	private HttpSession httpSession;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	

	
	@GetMapping("/edit")
	public String helloedit(){
		return "edit";
	}
	
	@GetMapping("/index")
	public String helloIndex(){
		return "index";
	}
	
	@GetMapping("/login")
	public String hellologin(){
		return "login";
	}
	
	
	@GetMapping("/error")
	public String helloerror(){
		return "error";
	}
	@GetMapping("/logined")
	public String hellologined(){
		return "logined";
	}
	@GetMapping("/sign")
	public String hellosign(){
		return "sign";
	}
	
	
	
}