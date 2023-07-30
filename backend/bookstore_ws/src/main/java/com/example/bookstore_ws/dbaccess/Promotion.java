package com.example.bookstore_ws.dbaccess;

public class Promotion {
	private int promoid;
	private String promocode;
	private double discount;
	private String start_date;
	private String end_date;
	private int max_usage;
	private int catid;
	public int getPromoid() {
		return promoid;
	}
	public void setPromoid(int promoid) {
		this.promoid = promoid;
	}
	public String getPromocode() {
		return promocode;
	}
	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getMax_usage() {
		return max_usage;
	}
	public void setMax_usage(int max_usage) {
		this.max_usage = max_usage;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	
}
