package com.example.bookstore_ws.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore_ws.dbaccess.Book;
import com.example.bookstore_ws.dbaccess.Orders;
import com.example.bookstore_ws.dbaccess.OrdersDAO;
import com.example.bookstore_ws.dbaccess.UserDAO;

import com.example.bookstore_ws.dbaccess.User;


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
    
    @GetMapping(path = "/getOrdersByStatus")
    public ArrayList<Orders> getOrdersByUserId(@RequestBody String status) {
        ArrayList<Orders> ordersList = new ArrayList<Orders>();
        try {
            OrdersDAO db = new OrdersDAO();
            ordersList = db.getOrdersByStatus(status);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return ordersList;
    }
    
    @GetMapping(path = "/getTotalPriceByOrderId/{oid}")
    public double getTotalPrice(@PathVariable("oid") int oid) {
        double totalPrice = 0.0;
        try {
            OrdersDAO db = new OrdersDAO();
            totalPrice = db.getTotalPrice(oid);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return totalPrice;
    }
    
    @GetMapping("/getTopOrders")
    public ArrayList<Orders> getTopOrders(@RequestBody int limit) {
        ArrayList<Orders> orderList = new ArrayList<Orders>();
        try {
            OrdersDAO db = new OrdersDAO();
            orderList = db.getTopOrders(limit);
        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
        return orderList;
    }
    
    @GetMapping("/getTopCustomers")
    public ArrayList<User> getTopCustomers() {
        ArrayList<User> usersList = new ArrayList<User>();
        try {
            OrdersDAO db = new OrdersDAO();
            usersList = db.getTopCustomers();
        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
        return usersList;
    }
    
    @GetMapping("/getBookByDate")
    public HashMap<String, Object> getBookByDate(@RequestParam String date) {
    	HashMap<String, Object> booksMap = null;
        try {
            OrdersDAO db = new OrdersDAO();
            booksMap = db.getSaleOfBookByDate(date);
        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
        return booksMap;
    }
    
    @GetMapping("/getBookByPeriod")
    public HashMap<String, Object> getBookByPeriod(@RequestParam String start_date, @RequestParam String end_date) {
    	HashMap<String, Object> booksMap = null;
        try {
            OrdersDAO db = new OrdersDAO();
            booksMap = db.getSaleOfBookByPeriod(start_date, end_date);
        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
        return booksMap;
    }
    
    @GetMapping("/getCustomersByBookId/{bid}")
    public ArrayList<User> getCustomersByBookId(@PathVariable("bid") int bid) {
    	ArrayList<User> usersList = new ArrayList<>();
        try {
            OrdersDAO db = new OrdersDAO();
            usersList = db.getCustomersByBookId(bid);
        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
        return usersList;
    }
}
