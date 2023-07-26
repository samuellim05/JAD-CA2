package com.example.bookstore_ws.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore_ws.dbaccess.Orders;
import com.example.bookstore_ws.dbaccess.OrdersDAO;


@RestController
@RequestMapping("/orders")
public class Orders_Controller {
	@PostMapping("/createOrder")
	public int createOrder(@RequestBody Orders orders) {
		int rec =0;
		try {
			OrdersDAO db = new OrdersDAO();
			rec=db.createOrder(orders);
			System.out.print("...done create order"+rec);
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	}
}
