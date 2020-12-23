package com.tathink.database.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="member")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Member implements UserDetails
{

	private static final long serialVersionUID = 1L;
	
    @Id
    private String username;
    private String realname;
    private String password;
    private String level;
    private String imgPath;
    private String email;
    private boolean bRevEmail;
    private String mobile;
    private boolean bRevSms;
    private String  idNumber;
    private boolean bMan;
    private String memberCategory;
    private String company;
    private String grade;
    private String protector;
    private String protectorMobile;
    private boolean bRevProtecterSms;
    private String joinPath;
    private String zipCode;
    private String address01;
    private String address02;
    private String jobPosition;
    private String teacherInfo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;
    private boolean bDelete;
    
    
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isbRevEmail() {
		return bRevEmail;
	}

	public void setbRevEmail(boolean bRevEmail) {
		this.bRevEmail = bRevEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isbRevSms() {
		return bRevSms;
	}

	public void setbRevSms(boolean bRevSms) {
		this.bRevSms = bRevSms;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public boolean isbMan() {
		return bMan;
	}

	public void setbMan(boolean bMan) {
		this.bMan = bMan;
	}

	public String getMemberCategory() {
		return memberCategory;
	}

	public void setMemberCategory(String memberCategory) {
		this.memberCategory = memberCategory;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProtector() {
		return protector;
	}

	public void setProtector(String protector) {
		this.protector = protector;
	}

	public String getProtectorMobile() {
		return protectorMobile;
	}

	public void setProtectorMobile(String protectorMobile) {
		this.protectorMobile = protectorMobile;
	}

	public boolean isbRevProtecterSms() {
		return bRevProtecterSms;
	}

	public void setbRevProtecterSms(boolean bRevProtecterSms) {
		this.bRevProtecterSms = bRevProtecterSms;
	}

	public String getJoinPath() {
		return joinPath;
	}

	public void setJoinPath(String joinPath) {
		this.joinPath = joinPath;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress01() {
		return address01;
	}

	public void setAddress01(String address01) {
		this.address01 = address01;
	}

	public String getAddress02() {
		return address02;
	}

	public void setAddress02(String address02) {
		this.address02 = address02;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(String teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	
	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isbDelete() {
		return bDelete;
	}

	public void setbDelete(boolean bDelete) {
		this.bDelete = bDelete;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return username;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(level));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}