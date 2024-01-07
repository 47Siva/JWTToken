package com.Sampleproject.LogiPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Sampleproject.LogiPage.Common.APIResponse;
import com.Sampleproject.LogiPage.dto.LoginRequestDto;
import com.Sampleproject.LogiPage.dto.SignUpRequestDto;
import com.Sampleproject.LogiPage.service.LoginService;
import com.Sampleproject.LogiPage.util.JwtUtil;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/signUp")
	public ResponseEntity<APIResponse> singUp(@RequestBody SignUpRequestDto signUpRequestDto) {
		// hwbk
		APIResponse apiResponse = loginService.signUp(signUpRequestDto);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> singUp(@RequestBody LoginRequestDto loginRequestDto) {

		APIResponse apiResponse = loginService.login(loginRequestDto);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("/private")
	public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "Authorization") String auth)
			throws Exception {
		APIResponse apiResponse = new APIResponse();

//		String authorization = "auth";
		jwtUtil.verify(auth);

		apiResponse.setStatus(HttpStatus.ACCEPTED.value());
		apiResponse.setData("its worke");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("getbyGender")
	public ResponseEntity<APIResponse> getByGender(
			@RequestParam(value = "gender", required = false) List<String> gender) {

		APIResponse apiResponse = loginService.getByGender(gender);

		return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
	}
}
