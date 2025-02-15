package com.d288.travelagency.dto;

import com.d288.travelagency.entities.Cart;
import com.d288.travelagency.entities.CartItem;
import com.d288.travelagency.entities.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

// Represents purchase request
// Responsible for holding the details of the purchase from the front-end (acts as container when user sends request)
/* Steps:
1. User submits order through form on front-end
2. POST request with order details in JSON
3. RestController in Spring maps request body to this Purchase DTO
4. Spring converts JSON body to a Purchase object
5. Controller passes the Purchase object to the CheckoutService
*/

@Data  // Gives constructors and getters/setters
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;

}
