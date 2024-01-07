package com.Sampleproject.LogiPage.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Sampleproject.LogiPage.Common.APIResponse;
import com.Sampleproject.LogiPage.entity.Student;
import com.Sampleproject.LogiPage.entity.Teacher;
import com.Sampleproject.LogiPage.entity.UserLoginSchool;
import com.Sampleproject.LogiPage.repository.StudentRepository;
import com.Sampleproject.LogiPage.repository.TeacherRepository;
import com.Sampleproject.LogiPage.repository.UserLoginSchoolRepository;
import com.Sampleproject.LogiPage.util.JwtUtilSchool;

@Service
public class UserLoginService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	UserLoginSchoolRepository userLoginRepository;
	
	@Autowired
	JwtUtilSchool jwtutil;

	public String store(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
		UserLoginSchool userLogin = new UserLoginSchool();

		userLogin.setUsername(student.getEmail());
		userLogin.setPassword(student.getPassward());

		userLoginRepository.save(userLogin);

		return "Successfully saved";
	}

	public String store(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherRepository.save(teacher);
		UserLoginSchool userLogin = new UserLoginSchool();

		userLogin.setUsername(teacher.getEmail());
		userLogin.setPassword(teacher.getPassward());

		userLoginRepository.save(userLogin);

		return "Successfully saved";
	}

	// login api service
	public APIResponse login(UserLoginSchool userLogin) {

		APIResponse apiResponse = new APIResponse();

		// validation

		// verfiy user exist with given email and possward
		userLogin = userLoginRepository.findOneByEmilIdAndPassward(userLogin.getUsername(), userLogin.getPassword());

		// response
		if (userLogin != null) {
			apiResponse.setData("Loged in");
			apiResponse.setStatus(HttpStatus.OK.value());
			String token = jwtutil.generateJwt(userLogin);

			Map<Object, Object> data = new HashMap<Object, Object>();
			data.put("Token", token);
			
			apiResponse.setData(data);
			return apiResponse;
		} else {
			apiResponse.setData("User login failed");
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setError("incorrect passward or emailId");
			return apiResponse;
		}
	}
}
