package com.Sampleproject.LogiPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sampleproject.LogiPage.entity.LoginPage;
import com.Sampleproject.LogiPage.entity.User;
import com.Sampleproject.LogiPage.service.LoginPageService;

@RestController
@RequestMapping("/api/loginpage")
public class LoginpageController {

	@Autowired
	LoginPageService loginpageservice;

	@PostMapping("/loginpost")
	public String store(@RequestBody User user) {
		loginpageservice.store(user);
		return "Successfully Stored";
	}

	@PostMapping("/login")
	public Object store(@RequestBody LoginPage loginpage) {
		return loginpageservice.store(loginpage);

	}

}
