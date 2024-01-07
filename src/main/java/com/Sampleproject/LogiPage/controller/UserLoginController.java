package com.Sampleproject.LogiPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sampleproject.LogiPage.Common.APIResponse;
import com.Sampleproject.LogiPage.entity.Student;
import com.Sampleproject.LogiPage.entity.Teacher;
import com.Sampleproject.LogiPage.entity.UserLoginSchool;
import com.Sampleproject.LogiPage.service.UserLoginService;
import com.Sampleproject.LogiPage.util.JwtUtilSchool;

@RestController
@RequestMapping("/api/login")
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;
	
	@Autowired
	JwtUtilSchool jwtUtil;
	
	@PostMapping("/student")
	public String store(@RequestBody Student student) {
		userLoginService.store(student);
		return "Successfully Stored";
	}
	

	@PostMapping("/teacher")
	public String store(@RequestBody Teacher teacher) {
		userLoginService.store(teacher);
		return "Successfully Stored";
	}
	
	@PostMapping("/schoolLogin")
	public ResponseEntity<Object> singUp(@RequestBody UserLoginSchool userLogin) {

		APIResponse apiResponse = userLoginService.login(userLogin);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("/schoolvalidate")
	public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "Authorization") String auth)
			throws Exception {
		APIResponse apiResponse = new APIResponse();

//		String authorization = "auth";
		jwtUtil.verify(auth);

		apiResponse.setStatus(HttpStatus.ACCEPTED.value());
		apiResponse.setData("its worke");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
