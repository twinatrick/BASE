package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HAw {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping("/x")
	public String hello(){
		return "Hey, Spring Boot 的 Hello World !";
	}

}
