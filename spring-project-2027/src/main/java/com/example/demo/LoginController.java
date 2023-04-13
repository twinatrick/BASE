package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.Cookie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;




import com.example.*;

@RestController
public class LoginController {
	
	private static final RowCallbackHandler StaffModelMapper = null;	
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostMapping("/login")
	public String login( @RequestParam String username, @RequestParam String password) {
		StaffModel userdata=new StaffModel();
		userdata.setEmail(username);
		userdata.setPassword(password);
		StaffModel TRY=new StaffModel(); 		
		if(getData(userdata)==null) {
			 return "fail";
		}
		TRY=getData(userdata);
		if (username.equals(TRY.getEmail()) && password.equals(TRY.getPassword())) {
			httpSession.setAttribute("email",TRY.getEmail()); // 設置 Session 屬性值
			String sessionId = SessionUtil.generateSessionId();
			httpSession.setAttribute("sessionId", sessionId); // 將生成的隨機值設置為 Session 的屬性值
		    return "success";
		    } else {
		    return "fail";
		    }
	 		} 	
	 	@PostMapping("/logout")
	 	public String logout(@RequestParam String username) {
			httpSession.removeAttribute("email");
			httpSession.removeAttribute("sessionId");
	 		return "success";
	 	}
	 	
	 	@PostMapping("/sign")
		public String sign(@RequestParam String username, @RequestParam String password){ 				
	 		 StaffModel userdata=new StaffModel();
			 userdata.setEmail(username);
			 userdata.setPassword(password);			
	 		if(getData(userdata)==null) {
	 		String sql="INSERT INTO staffinfo (email,password) VALUES (\'"
	 				+ username
	 				+ "\',\'"
	 				+ password
	 				+ "\');";	 		
	 		jdbcTemplate.update(sql,new Object[] {});	 				
	 		httpSession.setAttribute("email",username); // 設置 Session 屬性值
			String sessionId = SessionUtil.generateSessionId();
			httpSession.setAttribute("sessionId", sessionId); // 將生成的隨機值設置為 Session 的屬性值
			return "success";		
	 		}
	 		else{
	 			return "fail";}
		}	 	
	 	public StaffModel getData(StaffModel sm) {			
			 String selectQuery = "SELECT * FROM staffInfo WHERE EMAIL = \'" + sm.getEmail() + "\' ; ";
		    List<StaffModel> result = jdbcTemplate.query(selectQuery, new StaffModelMapper());
		    if (result.size() > 0) {
		        return result.get(0);
		    } else {
		        return null;
		    }			
		}
	 	@ResponseBody
	 	@GetMapping("/all")
	 	public List<StaffModel> getallData(StaffModel sm){
	 		String selectQuery = "SELECT * FROM staffInfo ";	 		
		    List<StaffModel> result = jdbcTemplate.query(selectQuery, new StaffModelMapper());
		    softallData(result);
		    result = jdbcTemplate.query(selectQuery, new StaffModelMapper());
		    return result;	 			 
	 		} 	 	
	 	public void softallData(List<StaffModel> sm){
	 		String selectQuery="";
	 		int a=sm.size()+1;
	 		int b=0;	 		
	 		StaffModel userdata=new StaffModel();
	 		for(b=1;b<a;b++ ) {
	 			if(b!=sm.get(b-1).getId()) {
	 				selectQuery = "update staffInfo set id =\'"
	 					+b
	 					+"\' where id = \'"
	 					+sm.get(b-1).getId()
	 					+"\'"
	 					;	
	 				jdbcTemplate.update(selectQuery);
	 			}	
	 		}
	 	}
	 	@PostMapping("/delete")
	 	public String del(@RequestParam int id,@RequestParam String email){	 		 		
	 		 StaffModel userdata=new StaffModel();
			 userdata.setId(id);
			 userdata.setEmail(email);			 			
			 if(email.equals((String)httpSession.getAttribute("email"))){
				 httpSession.removeAttribute("email");
				httpSession.removeAttribute("sessionId");
				System.out.printf("remove old session\n");				
			 }
	 		if(getData(userdata)!=null) {
	 		String sql="DELETE FROM staffInfo WHERE id =\'"
	 				+ id
	 				+"\';";	 		
	 		jdbcTemplate.update(sql);	 				
			return "success";		
	 		}
	 		else
	 		{return "fail";}
		}	 	
	 	@PostMapping("/edit1")
	 	public String edit1(@RequestParam int id,@RequestParam String email){
	 		httpSession.setAttribute("changid",id);
	 		httpSession.setAttribute("changemail",email);
			return "success";			 		
		}@PostMapping("/edit2")
	 	public String edit2(@RequestParam String id,@RequestParam String username,@RequestParam String password){
	 		int oldid=(int)httpSession.getAttribute("changid");
	 		String oldmail=(String)httpSession.getAttribute("changemail");
	 		httpSession.removeAttribute("changid");
	 		httpSession.removeAttribute("changemail");
	 		String selectQuery = 
	 				"update staffInfo set id =\'"
	 						+id
	 						+"\' ,email= \'"
	 						+username
	 						+ "\',password= \'"
	 						+password
	 						+ "\' where (id = \'"
	 						+oldid
	 						+ "\');";	 		
	 		 StaffModel userdata=new StaffModel();
			 userdata.setId(oldid);
			 userdata.setEmail(oldmail);	
	 		if(getData(userdata)!=null) {
	 			jdbcTemplate.update(selectQuery);	
	 			return "success";		
	 		}
	 		return "fail";
		}	
		
		
		
}
