package com.example.bookstore_ws.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore_ws.dbaccess.Orders;
import com.example.bookstore_ws.dbaccess.OrdersDAO;
import com.example.bookstore_ws.dbaccess.UserDAO;

import assignment.User;


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
	
    @GetMapping("/getAllOrders")
    public ArrayList<Orders> getAllOrders() {
        ArrayList<Orders> orderList = new ArrayList<Orders>();
        try {
            OrdersDAO db = new OrdersDAO();
            orderList = db.listAllOrders();
        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
        return orderList;
    }
    
    @GetMapping(path = "/getOrdersByUserId/{uid}")
    public ArrayList<Orders> getOrdersByUserId(@PathVariable("uid") int uid) {
        ArrayList<Orders> ordersList = new ArrayList<Orders>();
        try {
            OrdersDAO db = new OrdersDAO();
            ordersList = db.getOrdersByUserId(uid);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return ordersList;
    }
    
    @PutMapping("/updateOrder/{oid}")
	public int updateOrder(@PathVariable int oid, @RequestBody Orders orders) {
	    int rec = 0;
	    try {
	        OrdersDAO db = new OrdersDAO();
	        rec = db.updateOrder(orders, oid);
	        System.out.print(oid);
	        System.out.print("...done update order" + oid + "rec count:" + rec);
	    } catch (Exception e) {
	        System.out.print(e.toString());
	    }
	    return rec;
	}
    
    @PutMapping("/updateStatus/{oid}")
	public int updateStatus(@PathVariable int oid, @RequestBody String status) {
	    int rec = 0;
	    try {
	        OrdersDAO db = new OrdersDAO();
	        rec = db.updateStatus(oid, status);
	        System.out.print(oid);
	        System.out.print("...done update Status" + oid + "rec count:" + rec);
	    } catch (Exception e) {
	        System.out.print(e.toString());
	    }
	    return rec;
	}
    
    @PutMapping("/cancelOrder/{oid}")
	public boolean cancelOrder(@PathVariable int oid) {
	    Boolean canceled = false;
	    try {
	        OrdersDAO db = new OrdersDAO();
	        canceled = db.cancelOrder(oid);
	        System.out.print(oid);
	        System.out.print("...done update Status" + oid);
	    } catch (Exception e) {
	        System.out.print(e.toString());
	    }
	    return canceled;
	}
}
