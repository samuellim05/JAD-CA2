package com.example.bookstore_ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore_ws.dbaccess.Orders;
import com.example.bookstore_ws.dbaccess.OrdersDAO;
import com.example.bookstore_ws.dbaccess.ShippingAddress;
import com.example.bookstore_ws.dbaccess.ShippingAddressDAO;

@RestController
@RequestMapping("/shippingAddr")
public class ShippingAddress_Controller {
	@GetMapping("/getShippingAddrById/{sid}")
	public ShippingAddress getShippingAddrById(@PathVariable("sid") int sid) {
		ShippingAddress shippingAddr = new ShippingAddress();
		try {
			ShippingAddressDAO db = new ShippingAddressDAO();
			shippingAddr = db.getShippingAddressById(sid);
		} catch(Exception e){
			System.out.println("Error:" + e);
		}
		return shippingAddr;
	}
	
	@PostMapping("/createShippingAddr")
	public int createShippingAddr(@RequestBody ShippingAddress shippingAddr) {
		int rec =0;
		try {
			ShippingAddressDAO db = new ShippingAddressDAO();
			rec=db.createAddr(shippingAddr);
			System.out.print("...done create shippingAddr"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
}
