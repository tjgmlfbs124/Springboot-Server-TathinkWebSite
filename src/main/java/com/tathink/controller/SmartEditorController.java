package com.tathink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.database.model.Member;

@Controller
public class SmartEditorController {
	
	@RequestMapping("/smartEditor/SmartEditor2Skin")
	public String SmartEditor2Skin(Member member, Model model)
	{	
		System.out.println("SmartEditor2Skin");
		
		return "smartEditor/SmartEditor2Skin";
	}
	
	@RequestMapping("/smartEditor/smart_editor2_inputarea")
	public String SmartEditor2Inputarea(Member member, Model model)
	{	
		System.out.println("smart_editor2_inputarea");
		
		return "smartEditor/smart_editor2_inputarea";
	}
	
	@RequestMapping("/smartEditor/photo_uploader")
	public String photoUploader(Member member, Model model)
	{	
		System.out.println("photo_uploader");
	
		return "smartEditor/photo_uploader";
	}
	
	@RequestMapping(value="/smartEditor/boardFileUploader", method = RequestMethod.POST)
	@ResponseBody
	public String boardFileUploader()
	{	
		System.out.println("boardFileUploader");
		
		return "";
	}
}
