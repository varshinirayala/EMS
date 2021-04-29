package com.mt.employeesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping(value = "/")
	public String homepage() {
		return "index";
	}

	@GetMapping(value = "/newForm")
	public String newForm() {
		return "newForm";
	}

	@GetMapping(value = "/view")
	public String view() {
		return "view";
	}

	@GetMapping(value = "/edit")
	public String edit() {
		return "edit";
	}

	@GetMapping(value = "/delete")
	public String delete() {
		return "delete";
	}

}