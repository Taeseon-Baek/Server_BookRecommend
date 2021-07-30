package com.my.bookmarker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.bookmarker.service.testService;
import com.my.bookmarker.vo.vanilla.Writer;

@RestController
@RequestMapping("/first")
public class FirstController {
	
	@Autowired
	private testService testService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Writer> selectTest01() {
		return testService.selectTest01();
	}
	
}
