package com.example.bookstore_ws.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookstore_ws.dbaccess.UserDAO;
import assignment.User;

@RestController
@RequestMapping("/user")
public class User_Controller {
	@GetMapping("/getUser/{uid}")
	public User getUser(@PathVariable("uid") String uid) {
		User user = new User();
		try {
			UserDAO db = new UserDAO();
			user = db.getMemberById(uid);
			
		} catch(Exception e) {
			System.out.println("Error:" + e);
		}
		return user;
	}
	
	@GetMapping("/getAllUsers")
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<>();
		try {

			UserDAO userDAO = new UserDAO();
			
	        userList = userDAO.getAllUsers();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return userList;
	}
	
	@PostMapping("/createUser")
	public int createUser(@RequestBody User user) {
		int rec =0;
		try {
			UserDAO db = new UserDAO();
			rec=db.createUser(user);
			System.out.print("...done create user"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
//	@PostMapping
//	public String createUser() {
//		return "createUser";
//	}
//	@PutMapping
//	public String updateUser() {
//		return "updateUser";
//	}
//	@DeleteMapping
//	public String deleteUser() {
//		return "deleteUser";
//	}
}
