package com.Sampleproject.LogiPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sampleproject.LogiPage.entity.LoginPage;
import com.Sampleproject.LogiPage.entity.User;
import com.Sampleproject.LogiPage.repository.LoginPageRepository;
import com.Sampleproject.LogiPage.repository.UserRepository;

@Service
public class LoginPageService {

	@Autowired
	LoginPageRepository loginpagerepository;
	
	@Autowired
	UserRepository userrepository;
	
	
	
	
	public String store(User user) {
		// TODO Auto-generated method stub
		userrepository.save(user);
		LoginPage loginpage = new LoginPage();
		
		loginpage.setUsername(user.getUsername());
		loginpage.setPassword(user.getPassword());
		
		loginpagerepository.save(loginpage);
		
		return "Successfully saved";
	}

	public Object store(LoginPage login) {
		// TODO Auto-generated method stub
		LoginPage loginpage = loginpagerepository.findByUsername(login.getUsername());
		if(loginpage != null)
		{
			if(loginpage.getUsername().equals(login.getUsername()) && loginpage.getPassword().equals(login.getPassword())) {
				//loginrepository.save(login);
				return "Successfully loging In"; 
			}
			else {
				return "Bad Request";
			}
			
		}
		else {
			return "Incorret username or password";
		}
		
		
	}

}
