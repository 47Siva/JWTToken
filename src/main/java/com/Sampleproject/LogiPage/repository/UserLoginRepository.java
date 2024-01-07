package com.Sampleproject.LogiPage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Sampleproject.LogiPage.dto.SignUpRequestDto;
import com.Sampleproject.LogiPage.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
	
	//Raw Query
	@Query(value ="select * from user_login as u where u.email_id=:emailId and u.passward=:passward",nativeQuery = true)
	UserLogin findOneByEmilIdAndPassward(String emailId, String passward);

	@Query(value = "select * from user_login as ul where ul.gender =:gender",nativeQuery = true)
	List<UserLogin> findAllByGender(List<String> gender);

	
}
