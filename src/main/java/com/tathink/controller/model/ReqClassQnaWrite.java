package com.tathink.controller.model;

public class ReqClassQnaWrite {
	private String seq;
	private String classSeq;
	private String qTitle;
	private String qContent;
	private String aContent;
	private boolean bSec;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getClassSeq() {
		return classSeq;
	}
	public void setClassSeq(String classSeq) {
		this.classSeq = classSeq;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public boolean isbSec() {
		return bSec;
	}
	public void setbSec(boolean bSec) {
		this.bSec = bSec;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}	
	
}
