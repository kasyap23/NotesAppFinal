package com.notes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.notes.repos.UserRepository;
import com.notes.responses.LoginJson;

@RestController
public class ClassController {


	
	@Autowired
	UserRepository repo;
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	@RequestMapping(value = "/hello")
	public String loginReturn() {
		LoginJson loginJson = new LoginJson();
		loginJson.setMessage("Hello Deepak");
		loginJson.setSuccess(true);
		loginJson.setData(this.repo.findAll());
		//System.out.println(new Gson().toJson(loginJson).toString());
		
		return new Gson().toJson(loginJson).toString();
	}
}
