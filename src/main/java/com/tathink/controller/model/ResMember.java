package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.Member;

public class ResMember 
{
	private String username;
    private String realname;
    private String imgPath;
    private String jobPosition;
    private String teacherInfo;
    private String level;
    private String email;
    private String password;
    private String mobile;
    private boolean bRevEmail;
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
    private LocalDate regDate;
    private LocalDate updateDate;
	private String error;

	public ResMember(String strError)
	{
		error = strError;
	}
	
	public ResMember(Member member)
	{
		error = null;
		username = member.getUsername();
	    realname = member.getRealname();
	    imgPath = member.getImgPath();
	    jobPosition = member.getJobPosition();
	    teacherInfo = member.getTeacherInfo();
	    level = member.getLevel();
	    password = member.getPassword();
	    email = member.getEmail();
	    mobile = member.getMobile();
	    bRevEmail = member.isbRevEmail();
	    bRevSms = member.isbRevSms();
	    idNumber = member.getIdNumber();
	    bMan = member.isbMan();
	    memberCategory = member.getMemberCategory();
	    company = member.getCompany();
	    grade = member.getGrade();
	    protector = member.getProtector();
	    protectorMobile = member.getProtectorMobile();
	    bRevProtecterSms = member.isbRevProtecterSms();
	    joinPath = member.getJoinPath();
	    zipCode = member.getZipCode();
	    address01 = member.getAddress01();
	    address02 = member.getAddress02();
	    regDate = member.getRegDate();
	    updateDate = member.getUpdateDate();
	}

	public void setAllInfo(Member member)
	{
		error = null;
		username = member.getUsername();
	    realname = member.getRealname();
	    imgPath = member.getImgPath();
	    jobPosition = member.getJobPosition();
	    teacherInfo = member.getTeacherInfo();
	    level = member.getLevel();
	    password = member.getPassword();
	    email = member.getEmail();
	    mobile = member.getMobile();
	    bRevEmail = member.isbRevEmail();
	    bRevSms = member.isbRevSms();
	    idNumber = member.getIdNumber();
	    bMan = member.isbMan();
	    memberCategory = member.getMemberCategory();
	    company = member.getCompany();
	    grade = member.getGrade();
	    protector = member.getProtector();
	    protectorMobile = member.getProtectorMobile();
	    bRevProtecterSms = member.isbRevProtecterSms();
	    joinPath = member.getJoinPath();
	    zipCode = member.getZipCode();
	    address01 = member.getAddress01();
	    address02 = member.getAddress02();
	    regDate = member.getRegDate();
	    updateDate = member.getUpdateDate();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isbRevEmail() {
		return bRevEmail;
	}

	public void setbRevEmail(boolean bRevEmail) {
		this.bRevEmail = bRevEmail;
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
	
}
