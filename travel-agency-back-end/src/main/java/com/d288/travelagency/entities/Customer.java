package com.d288.travelagency.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "customer_first_name", nullable = false)
    private String firstName;

    @Column(name = "customer_last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postal_code", nullable = false)
    private String postal_code;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;

    // Contains collection of carts
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Cart> carts = new HashSet<>();


    // Helper method to add carts for this customer
    // Used in CheckoutServiceImpl to add cart to customer
    public void add(Cart cart) {

        // If no carts, add cart set named carts, then add cart to the set
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }
    }
}
