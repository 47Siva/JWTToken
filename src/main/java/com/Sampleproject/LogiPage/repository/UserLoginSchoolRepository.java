package com.Sampleproject.LogiPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Sampleproject.LogiPage.entity.UserLoginSchool;

@Repository

public interface UserLoginSchoolRepository extends JpaRepository<UserLoginSchool, Long> {
	
	@Query(value = "select * from user_login_school as u where u.username=:username and u.password=:password", nativeQuery = true)
	UserLoginSchool findOneByEmilIdAndPassward(String username, String password);

}
