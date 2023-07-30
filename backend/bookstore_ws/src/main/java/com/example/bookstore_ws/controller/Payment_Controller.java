//package com.example.bookstore_ws.controller;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.view.RedirectView;
//
//@RestController
//public class Payment_Controller {
//
//    @Value("${STRIPE_SECRET_KEY}")
//    private String stripeApiKey;
//
//    private String YOUR_DOMAIN = "http://localhost:8080/JAD_CA2/JAD-main/";
//
//    @PostMapping(path = "/create-checkout-session")
//    public RedirectView createCheckoutSession(@RequestParam("priceId[]") String[] priceIds,
//                                              @RequestParam("quantity[]") int[] quantities) {
//
//        Stripe.apiKey = stripeApiKey;
//
//        SessionCreateParams.Builder sessionParamsBuilder = SessionCreateParams.builder()
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setBillingAddressCollection(SessionCreateParams.BillingAddressCollection.REQUIRED)
//                .setAutomaticTax(
//                	      SessionCreateParams.AutomaticTax.builder()
//                	        .setEnabled(true)
//                	        .build())
//                .setSuccessUrl(YOUR_DOMAIN + "/paymentSuccess.html")
//                .setCancelUrl(YOUR_DOMAIN + "/paymentCancel.html");
//        // Build line items based on the received product information
//        for (int i = 0; i < priceIds.length; i++) {
//            sessionParamsBuilder.addLineItem(
//                    SessionCreateParams.LineItem.builder()
//                            .setQuantity((long) quantities[i])
//                            .setPrice(priceIds[i])
//                            .build()
//            );
//        }
//
//        SessionCreateParams params = sessionParamsBuilder.build();
//
//        try {
//            Session session = Session.create(params);
//
//            RedirectView redirectView = new RedirectView(session.getUrl());
//            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//            
//            
//
//            return redirectView;
//        } catch (StripeException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}