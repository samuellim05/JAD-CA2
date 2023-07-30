package com.example.bookstore_ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore_ws.dbaccess.Promotion;
import com.example.bookstore_ws.dbaccess.PromotionDAO;

@RestController
@RequestMapping("/promo")
public class PromotionController {
	@GetMapping("/checkPromoExist/{promo_code}")
	public boolean checkPromoExist(@PathVariable("promo_code") String promo_code) {
		boolean exist = false;
		try {
			PromotionDAO db = new PromotionDAO();
			exist = db.checkPromoExist(promo_code);
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return exist;

	}
	
	@GetMapping("/getPromoDetails/{promo_code}")
	public Promotion getPromotionDetails(@PathVariable("promo_code") String promo_code) {
		Promotion promotion = null;
		try {
			PromotionDAO db = new PromotionDAO();
			promotion = db.getPromoDetails(promo_code);
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return promotion;

	}
	
	
}
