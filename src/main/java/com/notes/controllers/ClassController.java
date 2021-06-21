package com.notes.controllers;

import com.google.gson.JsonObject;
import com.notes.jwt.JwtTokenProvider;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.repository.UserRepository;
//import com.notes.services.ServiceClass;
import com.notes.services.UserService;
import com.notes.tables.Note;
import com.notes.tables.User;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ClassController {

	@Autowired
	UserRepository repo;
	@Autowired 
	UserService userService;
        @Autowired
        JwtTokenProvider jwtTokenProvider;
        @Bean
        PasswordEncoder encoder()
        {
            return new BCryptPasswordEncoder();
        }

        @GetMapping("/test")
        public String test()
        {
            return "test";
        }
        
        @GetMapping("/signin")
        public ResponseEntity login(Principal principal)
        {


//            User userDetails = userService.getUserByEmail(user.getEmail());
            if(principal==null)
            {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

//            if(encoder().matches(userDetails.getPassword(),user.getPassword()))
//            {
                

                      Map<String,Object> map = new HashMap<>();

                    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;

                    System.out.println(token.getName());

                    User u = userService.getUserByEmail(token.getName());
                    
                    map.put("uid",u.getUid());
                    map.put("first_name",u.getFirst_name());
                    map.put("last_name",u.getLast_name());
                    map.put("email",u.getEmail());

                    
                   System.out.println(u);
                   System.out.println(new Date(System.currentTimeMillis()+1000));
                   
                   String JwtToken = jwtTokenProvider.generateToken(token);
                   
                   System.out.println("true");
                    map.put("token",JwtToken);

                    return new ResponseEntity(map,HttpStatus.OK);
                
//                catch(Exception e){
//                    System.out.println(8);
//                    System.out.println(e);
//                }
//                
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                
        }
        @PostMapping("/register")
        public ResponseEntity register(@RequestBody User u)
        {
            User tempUser = userService.getUserByEmail(u.getEmail());
            if(tempUser!=null)
            {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
                
            }
            try
            {
                u.setPassword(encoder().encode(u.getPassword()));
                this.repo.save(u);
                return new ResponseEntity(HttpStatus.OK);
            }
            catch(Exception e){}
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	
	
	@GetMapping("/notes/getNotes/{uid}")  
	private ResponseEntity getNotes(@PathVariable("uid") int uid)   
	{  
            try{
                Map<String,Set> map = new LinkedHashMap<>();
                map.put("notes", userService.getNotesById(uid));
		return new ResponseEntity(map,HttpStatus.OK);
            }
            catch(Exception e){}
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/notes/{uid}/save")  
	private ResponseEntity saveNotes(@RequestBody Note note,@PathVariable("uid") int uid) throws Exception   { 
              try
              {
                    userService.saveOrUpdate(note,uid);  
                    Map<String,Integer> map = new HashMap<>();
                    map.put("note_id",note.getNote_id());
                    return new ResponseEntity(map,HttpStatus.OK);
              }
              catch(Exception e){}
              return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}  
//	@PostMapping("/user/save")  
//	private ResponseEntity saveUser(@RequestBody User user)   {  
//		try
//                {
//                userService.saveUser(user);
//                Map<String,Integer> map = new HashMap<String,Integer>();
//                map.put("user_id",user.getUid());
//		return new ResponseEntity(map,HttpStatus.OK);
//                }
//                catch(Exception e){}
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//	}
 
	@DeleteMapping("/notes/delete/{uid}/{nid}")  
	private ResponseEntity deleteNotes(@PathVariable("uid") int uid,@PathVariable("nid") int nid)   
	{  
		try
                {
		userService.delete(uid,nid);
                return new ResponseEntity(HttpStatus.OK);
                }
                catch(Exception e){}
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                
	}  
	
	
	@RequestMapping(value = "/getAllUsers")
	public ResponseEntity loginReturn() {

		Map<String,Object> map = new HashMap<>();
                map.put("data",this.repo.findAll());
		return new ResponseEntity(map,HttpStatus.OK);
	}
}
