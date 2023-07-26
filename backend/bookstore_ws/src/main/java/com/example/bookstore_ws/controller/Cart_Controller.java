package com.example.bookstore_ws.controller;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookstore_ws.dbaccess.CartDAO;
import com.example.bookstore_ws.dbaccess.UserDAO;

import assignment.Cart;
import assignment.User;

@RestController
@RequestMapping("/cart")
public class Cart_Controller {
	@GetMapping("/getCart/{uid}")
	public ArrayList<Cart> getCart(@PathVariable("uid") int uid) {
		ArrayList<Cart> cartList = new ArrayList<>();
		try {
			CartDAO db = new CartDAO();
			cartList = db.getCartByMemberId(uid);
			
		} catch(Exception e) {
			System.out.println("Error:" + e);
		}
		return cartList;
	}
	
	@PutMapping("/updateCart/{uid}/{bid}/{action}")
	public int updateCart(@PathVariable("uid") int uid, @PathVariable("bid") int bookId, @PathVariable String action) {
		int rec = 0;
	    try {
	        CartDAO db = new CartDAO();
	        rec = db.updateCart(uid, bookId, action);
	        System.out.print("...done update cart" + rec);
	    } catch (Exception e) {
	        System.out.print(e.toString());
	    }
	    return rec;
	}
}
