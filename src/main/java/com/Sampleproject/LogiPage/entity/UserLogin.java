package com.Sampleproject.LogiPage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.Sampleproject.LogiPage.Common.Constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String gender;
	
	private String emailId;
	
	private Long pnoneNumber;
	
	private String userType = Constant.USER_TYPE.NORMAL;
	
	private String passward;
	
	
	private Boolean isActive = true;
	
	private Integer loginCount ;
	
	private String sseType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPnoneNumber() {
		return pnoneNumber;
	}

	public void setPnoneNumber(Long pnoneNumber) {
		this.pnoneNumber = pnoneNumber;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getSseType() {
		return sseType;
	}

	public void setSseType(String sseType) {
		this.sseType = sseType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
