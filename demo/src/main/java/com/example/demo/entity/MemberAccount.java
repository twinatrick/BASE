package com.example.demo.entity;


import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAccount extends Base {

	private String id;
	private String username;
	private String password;
	private String salt;
	
	
	public void setUsername(String string) {
		username =string;
		// TODO Auto-generated method stub
		
	}
	public void getUsername(String string) {
		string=username;
		// TODO Auto-generated method stub
		
	}
	public void setPassword(String string) {
		password=string;
		// TODO Auto-generated method stub
		
	}
	public void setSalt(String salt2) {
		// TODO Auto-generated method stub
		salt=salt2;
		
	}
	
	public Object getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public void setCreate_by(Object username2) {
		// TODO Auto-generated method stub
		
	}
	public void setId(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setCreate_by(String username2) {
		// TODO Auto-generated method stub
		username=username2;
		
	}
	public void setUpdate_by(Object username2) {
		// TODO Auto-generated method stub
		
	}

}