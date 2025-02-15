package com.d288.travelagency.controllers;

import com.d288.travelagency.dto.Purchase;
import com.d288.travelagency.dto.PurchaseResponse;
import com.d288.travelagency.services.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")  // Defines base URL path
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // Defines API endpoint at /api/checkout/purchase
    // This receives the request from Angular front end and passes it to the checkoutService, runs logic, then returns purchaseResponse to front end
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {  // Takes JSON request body and converts it into a Purchase object

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);  // Request body is deserialized into a Purchase DTO

        return purchaseResponse;
    }
}
