package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.tathink.database.model.Kit;

public class ResKitInfoPage {
	private ArrayList<ResKit> kitList = new ArrayList<ResKit>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResKitInfoPage(String strError)
	{
		error = strError;
	}
	
	public ResKitInfoPage(Page<Kit> pageKit)
	{		
		for(Kit kit : pageKit.getContent())
		{
			ResKit resKit = new ResKit(kit);
			kitList.add(resKit);
		}
		
		currPageIdx = pageKit.getNumber() + 1;
		startPageIdx = ((pageKit.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageKit.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageKit.getTotalPages();
	}

	public ArrayList<ResKit> getKitList() {
		return kitList;
	}

	public void setKitList(ArrayList<ResKit> kitList) {
		this.kitList = kitList;
	}

	public int getCurrPageIdx() {
		return currPageIdx;
	}

	public void setCurrPageIdx(int currPageIdx) {
		this.currPageIdx = currPageIdx;
	}

	public int getStartPageIdx() {
		return startPageIdx;
	}

	public void setStartPageIdx(int startPageIdx) {
		this.startPageIdx = startPageIdx;
	}

	public int getEndPageIdx() {
		return endPageIdx;
	}

	public void setEndPageIdx(int endPageIdx) {
		this.endPageIdx = endPageIdx;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
