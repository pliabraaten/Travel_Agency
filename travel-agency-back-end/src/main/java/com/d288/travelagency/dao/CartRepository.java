package com.d288.travelagency.dao;

import com.d288.travelagency.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")  // Accept calls for this origin
public interface CartRepository extends JpaRepository<Cart, Long> {  // <Entity, Primary Key>
}
