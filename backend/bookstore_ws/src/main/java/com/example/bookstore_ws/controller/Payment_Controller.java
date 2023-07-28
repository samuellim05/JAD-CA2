package com.example.bookstore_ws.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Payment_Controller {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeApiKey;

    //@Value("${YOUR_DOMAIN}")
    private String YOUR_DOMAIN = "http://localhost:8080/JAD_CA2/JAD-main/";

    @PostMapping(path = "/create-checkout-session")
    public RedirectView createCheckoutSession(
            @RequestParam(name = "priceId") String priceId,
            @RequestParam(name = "quantity", defaultValue = "1") Long quantity
    ) {
        Stripe.apiKey = stripeApiKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/paymentSuccess.html")
                .setCancelUrl(YOUR_DOMAIN + "/paymentCancel.html")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(quantity)
                                .setPrice(priceId) // Use the provided price ID from the request parameters
                                .build())
                .build();

        try {
            Session session = Session.create(params);

            // Perform the redirect to the Stripe Checkout page using RedirectView
            RedirectView redirectView = new RedirectView(session.getUrl());
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);

            return redirectView;
        } catch (StripeException e) {
            // Handle the StripeException here (log it or return an error message)
            e.printStackTrace();
            // You can return an error page or show an error message to the user
            return null; // or return a different RedirectView for an error page
        }
    }
}

