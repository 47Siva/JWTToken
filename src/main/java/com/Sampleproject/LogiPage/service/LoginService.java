package com.Sampleproject.LogiPage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sampleproject.LogiPage.Common.APIResponse;
import com.Sampleproject.LogiPage.dto.LoginRequestDto;
import com.Sampleproject.LogiPage.dto.SignUpRequestDto;
import com.Sampleproject.LogiPage.entity.UserLogin;
import com.Sampleproject.LogiPage.repository.UserLoginRepository;
import com.Sampleproject.LogiPage.util.JwtUtil;

@Service
public class LoginService {

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	JwtUtil jwtutil;

	// singup api service
	public APIResponse signUp(SignUpRequestDto signUpRequestDto) {
		APIResponse apiResponse = new APIResponse();

		// validation

		// Dto to entity
		UserLogin userLoginEntity = new UserLogin();
		userLoginEntity.setName(signUpRequestDto.getName());
		userLoginEntity.setEmailId(signUpRequestDto.getEmailId());
		userLoginEntity.setGender(signUpRequestDto.getGender());
		userLoginEntity.setId(signUpRequestDto.getId());
		userLoginEntity.setPnoneNumber(signUpRequestDto.getPhoneNumber());
		userLoginEntity.setPassward(signUpRequestDto.getPassward());

		// store entity
		userLoginEntity = userLoginRepository.save(userLoginEntity);

		// return
		if (userLoginEntity != null) {
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setData(userLoginEntity);
		} else {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setError("bad request");
		}

		return apiResponse;
	}

	// login api service
	public APIResponse login(LoginRequestDto loginRequestDto) {

		APIResponse apiResponse = new APIResponse();

		// validation

		// verfiy user exist with given email and possward
		UserLogin userLogin = userLoginRepository.findOneByEmilIdAndPassward(loginRequestDto.getEmailId(),
				loginRequestDto.getPassward());

		// response
		if (userLogin != null) {
			apiResponse.setData("Loged in");
			apiResponse.setStatus(HttpStatus.OK.value());
			String token = jwtutil.generateJwt(userLogin);

			Map<Object, Object> data = new HashMap<Object, Object>();
			data.put(userLogin, token);

			apiResponse.setData(data);
			return apiResponse;
		} else {
			apiResponse.setData("User login failed");
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setError("incorrect passward or emailId");
			return apiResponse;
		}

	}

	// getByGender
	public APIResponse getByGender(List<String> gender) {
		APIResponse apiResponse = new APIResponse();

//		List<UserLogin> userLoginEntity = userLoginRepository.findAllByGender(gender);
//		apiResponse.setStatus(200);
//		apiResponse.setData(userLoginEntity);
//		apiResponse.setError(null);
//		return apiResponse;

		// normal entity with requerd
//		if (gender == null) {
//
//			List<UserLogin> userLoginEntity = userLoginRepository.findAll();
//
//			apiResponse.setStatus(HttpStatus.OK.value());
//			apiResponse.setData(userLoginEntity);
//			return apiResponse;
//		} else {
//			List<UserLogin> userLoginEntity = userLoginRepository.findAllByGender(gender);
//			Object object = userLoginEntity;
//			apiResponse.setStatus(HttpStatus.OK.value());
//			apiResponse.setData(object);
//			return apiResponse;
//		}

		// DTO method
		if (gender == null) {
			List<UserLogin> userLoginEntity = userLoginRepository.findAll();
			List<SignUpRequestDto> signUpDtoList = new ArrayList<>();
			for (UserLogin i : userLoginEntity) {
				SignUpRequestDto signUpDto = new SignUpRequestDto();
				signUpDto.setEmailId(i.getEmailId());
				signUpDto.setGender(i.getGender());
				signUpDto.setId(i.getId());
				signUpDto.setName(i.getName());
				signUpDto.setPassward(i.getPassward());
				signUpDto.setPhoneNumber(i.getPnoneNumber());
				signUpDtoList.add(signUpDto);
			}
			apiResponse.setData(signUpDtoList);
			return apiResponse;
		} else {
			apiResponse.setData(userLoginRepository.findAllByGender(gender));
			return apiResponse;

		}
	}

}
