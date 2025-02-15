package com.d288.travelagency.bootstrap;

import com.d288.travelagency.dao.CustomerRespository;
import com.d288.travelagency.dao.DivisionRepository;
import com.d288.travelagency.entities.Customer;
import com.d288.travelagency.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    // Reference the customer and division respositories since they handle db interactions
    private final CustomerRespository customerRespository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRespository customerRespository, DivisionRepository divisionRespository) {
        this.customerRespository = customerRespository;
        this.divisionRepository = divisionRespository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Prepopulate database with five customers, if only one exists
        if (customerRespository.count() == 1) {

            // Retrieve the division objects to be added to the customer
            Division divEngland = divisionRepository.findById(101L).orElse(null);
            // findByID (JPA repo) returns an optional<> that might be empty if no results, but customer.setDivision requires a Division object
            // .orElse(null) unwraps the optional<> results into a Division IF a result was found; if not it returns null
            Division divWales = divisionRepository.findById(102L).orElse(null);  // L casts the int to a Long since the id is a Long


            // Create new customers and set values
            Customer customer1 = new Customer();
            customer1.setFirstName("John");
            customer1.setLastName("Lennon");
            customer1.setAddress("1964");
            customer1.setPostal_code("111");
            customer1.setPhone("1111111111");
            customer1.setDivision(divEngland);  // Set the customer division field to the retrieved division object
            customerRespository.save(customer1);

            Customer customer2 = new Customer();
            customer2.setFirstName("Paul");
            customer2.setLastName("McCartney");
            customer2.setAddress("1961");
            customer2.setPostal_code("222");
            customer2.setPhone("2222222222");
            customer2.setDivision(divEngland);  // Set the customer division field to the retrieved division object
            customerRespository.save(customer2);

            Customer customer3 = new Customer();
            customer3.setFirstName("George");
            customer3.setLastName("Harrison");
            customer3.setAddress("1962");
            customer3.setPostal_code("333");
            customer3.setPhone("3333333333");
            customer3.setDivision(divEngland);  // Set the customer division field to the retrieved division object
            customerRespository.save(customer3);

            Customer customer4 = new Customer();
            customer4.setFirstName("Ringo");
            customer4.setLastName("Star");
            customer4.setAddress("1963");
            customer4.setPostal_code("444");
            customer4.setPhone("4444444444");
            customer4.setDivision(divEngland);  // Set the customer division field to the retrieved division object
            customerRespository.save(customer4);

            Customer customer5 = new Customer();
            customer5.setFirstName("Pete");
            customer5.setLastName("Best");
            customer5.setAddress("1960");
            customer5.setPostal_code("555");
            customer5.setPhone("5555555555");
            customer5.setDivision(divWales);  // Set the customer division field to the retrieved division object
            customerRespository.save(customer5);

        }
    }
}
