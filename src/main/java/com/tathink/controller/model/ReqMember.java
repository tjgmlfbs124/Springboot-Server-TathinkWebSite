package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.Member;

public class ReqMember {

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
    private LocalDate regDate;
    private boolean bDelete;
    
    
	public String getUsername() {
		// TODO Auto-generated method stub
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

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(String teacherInfo) {
		this.teacherInfo = teacherInfo;
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

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public boolean isbDelete() {
		return bDelete;
	}

	public void setbDelete(boolean bDelete) {
		this.bDelete = bDelete;
	}
	
	public Member ConvertToMember()
	{
		Member member = new Member();
		member.setUsername(getUsername());
		member.setRealname(getRealname());
		member.setPassword(getPassword());
		member.setLevel("LEVEL_MEMBER");
		member.setImgPath(getImgPath());
		member.setEmail(getEmail());
		member.setbRevEmail(isbRevEmail());
		member.setMobile(getMobile());
		member.setbRevSms(isbRevSms());
		member.setIdNumber(getIdNumber());
		member.setbMan(isbMan());
		member.setMemberCategory(getMemberCategory());
		member.setCompany(getCompany());
		member.setGrade(getGrade());
		member.setProtector(getProtector());
		member.setProtectorMobile(getProtectorMobile());
		member.setbRevProtecterSms(isbRevProtecterSms());
		member.setJoinPath(getJoinPath());
		member.setZipCode(getZipCode());
		member.setAddress01(getAddress01());
		member.setAddress02(getAddress02());
		member.setJobPosition("");
		member.setTeacherInfo("");
		member.setRegDate(LocalDate.now());
		member.setbDelete(false);
        
		return member;
	}
	
	public Member UpdateToMember(Member member)
	{
		
		member.setPassword(getPassword());
		member.setImgPath(getImgPath());
		member.setEmail(getEmail());
		member.setbRevEmail(isbRevEmail());
		member.setMobile(getMobile());
		member.setbRevSms(isbRevSms());
		member.setMemberCategory(getMemberCategory());
		member.setCompany(getCompany());
		member.setGrade(getGrade());
		member.setProtector(getProtector());
		member.setProtectorMobile(getProtectorMobile());
		member.setbRevProtecterSms(isbRevProtecterSms());
		member.setJoinPath(getJoinPath());
		member.setZipCode(getZipCode());
		member.setAddress01(getAddress01());
		member.setAddress02(getAddress02());
		member.setJobPosition(getJobPosition());
		member.setTeacherInfo(getTeacherInfo());
		member.setUpdateDate(LocalDate.now());
		
		return member;
	}

}
