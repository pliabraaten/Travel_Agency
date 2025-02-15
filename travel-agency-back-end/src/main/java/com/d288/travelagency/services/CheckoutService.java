package com.d288.travelagency.services;


import com.d288.travelagency.dto.Purchase;
import com.d288.travelagency.dto.PurchaseResponse;


// Service interface for handling checkout operations
// Defines the "rules" for handling a purchase (but doesn't implement it)
public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);  // Defined in impl class
}
