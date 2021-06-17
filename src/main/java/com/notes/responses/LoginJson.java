package com.notes.responses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.notes.tables.User;

@Component
public class LoginJson {
	String message="";
	Boolean success=false;
	List<User> data = new ArrayList<User>();
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public ArrayList<User> getData() {
		return (ArrayList<User>) data;
	}
	public void setData(Iterable<User> iterable) {
		this.data = (List<User>) iterable;
	}
	
	
}
