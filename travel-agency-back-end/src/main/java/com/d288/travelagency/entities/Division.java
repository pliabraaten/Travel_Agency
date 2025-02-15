package com.d288.travelagency.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="divisions")
@Getter
@Setter
@NoArgsConstructor
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long id;

    @Column(name = "division")
    private String division_name;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)  // Will only be fetched from db when getCountry is called
    @JoinColumn(name="country_id", nullable = false, insertable = false, updatable = false)  // Hibernate won't insert or update this column - manually managed in setCountry
    private Country country;

    // Manually manages country ID to match the country object
    // Exposes country id in JSON
    @Column(name = "country_id")
    private Long country_id;
    public void setCountry(Country country) {
        setCountry_id(country.getId());  // Ensures country_id field stays the same as the country object
        this.country = country;
    }

    // Each division has a set of customers
    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "division")
    private Set<Customer> customers = new HashSet<>();


    // Constructor: BUG FIX FOR ADDING NEW CUSTOMER ON ANGULAR FRONT-END
    public Division(String url) {
        this.id = Long.parseLong(url.substring(url.lastIndexOf('/')+1));
    }

}

