package com.Sampleproject.LogiPage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sampleproject.LogiPage.entity.LoginPage;

@Repository
public interface LoginPageRepository extends JpaRepository<LoginPage, UUID> {

	LoginPage findByUsername(String username);

	

}
