package com.d288.travelagency.dao;

import com.d288.travelagency.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")  // Accept calls for this origin
public interface VacationRepository extends JpaRepository<Vacation, Long> {  // <Entity, Primary Key>
}
