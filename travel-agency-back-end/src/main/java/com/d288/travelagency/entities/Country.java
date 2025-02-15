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
@Table(name="countries")
@Setter
@Getter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "country")
    private String country_name;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    // Contains collection of divisions
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<Division> divisions = new HashSet<>();


    // TODO: WHY DOES THE LOMBOK @DATA OR @GETTER/@SETTER NOT WORK?
    public Long getId() {
        return id;
    }

    public void setCountry_id(Long id) {
        this.id = id;
    }
}
