package com.tathink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CodingController {
	@GetMapping("/coding")
	public String codingPage(Model model) {
		return "/coding/index";
	}
}
