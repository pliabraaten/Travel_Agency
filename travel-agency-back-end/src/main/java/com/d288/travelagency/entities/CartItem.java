package com.d288.travelagency.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Collection of excursions from one vacation
@Entity
@Table(name="cart_items")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacation;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    // "Owner" side excursion_cartitem join table
    @ManyToMany
    @JoinTable(
        name = "excursion_cartitem",  // Name of join table in db
        joinColumns = @JoinColumn(name = "cart_item_id"),  // Column in join table for cartItem - Foreign key
        inverseJoinColumns = @JoinColumn(name = "excursion_id")  // Column in join table for excursion - Foreign key
    )
    private Set<Excursion> excursions = new HashSet<>();


    public void setCart(Cart cart) {
        this.cart = cart;
    }


}
