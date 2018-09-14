package com.example.fbsample;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class SampleController {
	
	@Autowired
	SampleService ts;
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return ts.getUserList()
				.stream()
				.map(User::toString)
				.collect(Collectors.joining(","));
	}
}
