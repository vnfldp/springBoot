package com.example.manitech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class testController {
	@RequestMapping(value="/rest/testvalue", method = RequestMethod.GET)
	public String GetTestValue() {
		return "OOKK";
	}
	
}
