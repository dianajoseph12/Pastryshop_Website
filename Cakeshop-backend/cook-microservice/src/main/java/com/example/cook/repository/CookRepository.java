package com.example.cook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cook.model.Cook;

@Repository
public interface CookRepository extends JpaRepository<Cook, Long> {

}
