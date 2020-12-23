package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.Kit;

public class ReqKit 
{
	private int seq;
	private String code;
	private String name;
  	private String descript;
  	private String kitImg01Path;
  	private String kitImg02Path;
  	private String kitImg03Path;
  	private String kitImg04Path;
  	private String kitImg05Path;
  	private String username;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getKitImg01Path() {
		return kitImg01Path;
	}

	public void setKitImg01Path(String kitImg01Path) {
		this.kitImg01Path = kitImg01Path;
	}

	public String getKitImg02Path() {
		return kitImg02Path;
	}

	public void setKitImg02Path(String kitImg02Path) {
		this.kitImg02Path = kitImg02Path;
	}

	public String getKitImg03Path() {
		return kitImg03Path;
	}

	public void setKitImg03Path(String kitImg03Path) {
		this.kitImg03Path = kitImg03Path;
	}

	public String getKitImg04Path() {
		return kitImg04Path;
	}

	public void setKitImg04Path(String kitImg04Path) {
		this.kitImg04Path = kitImg04Path;
	}

	public String getKitImg05Path() {
		return kitImg05Path;
	}

	public void setKitImg05Path(String kitImg05Path) {
		this.kitImg05Path = kitImg05Path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Kit convertToKit() {
		Kit kit = new Kit();
		
		kit.setSeq(getSeq());
		kit.setCode(getCode());
		kit.setName(getName());
		kit.setDescript(getDescript());
		kit.setKitImg01Path(getKitImg01Path());
		kit.setKitImg02Path(getKitImg02Path());
		kit.setKitImg03Path(getKitImg03Path());
		kit.setKitImg04Path(getKitImg04Path());
		kit.setKitImg05Path(getKitImg05Path());
		kit.setUsername(getUsername());
		kit.setRegDate(LocalDate.now());
		kit.setbDelete(false);
	
		return kit;
	}
	
}
