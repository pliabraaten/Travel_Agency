package com.d288.travelagency.services;

import com.d288.travelagency.dao.CartRepository;
import com.d288.travelagency.dao.CustomerRespository;
import com.d288.travelagency.dto.Purchase;
import com.d288.travelagency.dto.PurchaseResponse;
import com.d288.travelagency.entities.Cart;
import com.d288.travelagency.entities.CartItem;
import com.d288.travelagency.entities.Customer;
import com.d288.travelagency.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;


// Implements the CheckoutService to actually process the purchase
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRespository customerRespository;
    private CartRepository cartRepository;

    // Constructor
    public CheckoutServiceImpl(CustomerRespository customerRespository, CartRepository cartRepository) {
        this.customerRespository = customerRespository;
        this.cartRepository = cartRepository;
    }

    // Implements placeOrder method declared in CheckoutService
    @Override
    @Transactional  // for JPA
    public PurchaseResponse placeOrder(Purchase purchase) {  // Injects purchase, Returns the purchaseResponse (orderTrackingNumber)

        // Retrieve the cart info from DTO (DTO populated from JSON)
        Cart cart = purchase.getCart();

        // Retrieve cartItems from the Purchase DTO
        Set<CartItem> cartItems = purchase.getCartItems();

        // Populate cart with cartItems from DTO purchase
        cartItems.forEach(item -> cart.add(item));  // Adds items to cart

        // Populate customer with cart
//        Customer customer = purchase.getCustomer();
//        customer.add(cart);

        // Set cart status
        cart.setStatus(StatusType.ordered);

        // Generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Return the response with the generated tracking number
        // If cart is empty: return message, otherwise: save and return tracking number
        if (cartItems.isEmpty()) {
            return new PurchaseResponse("Cart is empty");
        }
        else {
            // Save cart to the database
            cartRepository.save(cart);

            return new PurchaseResponse(orderTrackingNumber);
        }
    }

    // Generate a random UUID number (universally unique identifier)
    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();  // (UUID version-4)
    }
}
