package com.example.desserts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.desserts.model.Dessert;
import com.example.desserts.repository.DessertRepository;


@Component
public class DessertService {
	@Autowired(required=true)
	private DessertRepository dessertRepository;
	
	public List<Dessert> getAllDesserts() {
		List<Dessert> list = (List<Dessert>) this.dessertRepository.findAll();
		return list;

	}
	
	public Dessert addDessert(Dessert d) {
		// list.add(v);
		Dessert result = this.dessertRepository.save(d);
		return result;
	}

	public void updateDessert(Dessert dessert, Long id) {
		dessert.setId(id);
		dessertRepository.save(dessert);
	}

	public void deleteDessert(Long id) {
		dessertRepository.deleteById((Long) id);
		
	}

	
}
