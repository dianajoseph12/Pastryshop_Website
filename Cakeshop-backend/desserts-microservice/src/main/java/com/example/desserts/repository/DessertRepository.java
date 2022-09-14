package com.example.desserts.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.example.desserts.model.Dessert;

import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
@Repository
public interface DessertRepository extends JpaRepository<Dessert, Long> {

	
	

	

}
