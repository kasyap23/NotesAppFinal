package com.notes.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.repos.UserRepository;
import com.notes.services.ServiceClass;
import com.notes.tables.Note;
import com.notes.tables.User;

@RestController
public class ClassController {

	@Autowired
	UserRepository repo;
	@Autowired
	ServiceClass userService;
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	
	
	@GetMapping("/notes/getNotes/{uid}")  
	private Set<Note> getNotes(@PathVariable("uid") int uid)   
	{  
		return userService.getNotesById(uid); 
	}
	
	@PostMapping("/notes/{uid}/save")  
	private int saveNotes(@RequestBody Note note,@PathVariable("uid") int uid) throws Exception   {  
		userService.saveOrUpdate(note,uid);  
		return note.getNote_id();
	}  
	@PostMapping("/user/save")  
	private int saveUser(@RequestBody User user)   {  
		userService.saveUser(user);  
		return user.getUid();
	}
 
	@DeleteMapping("/notes/delete/{uid}/{nid}")  
	private void deleteNotes(@PathVariable("uid") int uid,@PathVariable("nid") int nid)   
	{  
		System.out.println("uid : "+uid+" Nid : "+nid);
		userService.delete(uid,nid);  
	}  
	
	
	@RequestMapping(value = "/getAllUsers")
	public Iterable<User> loginReturn() {
//		LoginJson loginJson = new LoginJson();
//		loginJson.setMessage("Hello Deepak");
//		loginJson.setSuccess(true);
//		loginJson.setData(this.repo.findAll());
		//System.out.println(new Gson().toJson(loginJson).toString());
		
		return this.repo.findAll();
		//return new Gson().toJson(loginJson).toString();
	}
}
