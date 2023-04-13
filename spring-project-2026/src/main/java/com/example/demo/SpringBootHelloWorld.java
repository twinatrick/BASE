package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringBootHelloWorld {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	@GetMapping("/index")
	public String helloIndex(){
		return "index";
	}
	@GetMapping("/login")
	public String helloIssadaesx(){
		return "login";
	}
}