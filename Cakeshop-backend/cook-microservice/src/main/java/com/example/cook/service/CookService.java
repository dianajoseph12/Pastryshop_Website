package com.example.cook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cook.model.Cook;
import com.example.cook.repository.CookRepository;

@Component
public class CookService {
	@Autowired(required=true)
	private CookRepository cookRepository;
	
	public List<Cook> getAllCooks() {
		List<Cook> list = (List<Cook>) this.cookRepository.findAll();
		return list;

	}
	
	public Cook addDessert(Cook c) {
		// list.add(v);
		Cook result = this.cookRepository.save(c);
		return result;
	}

	public void updateCook(Cook cook, Long id) {
		cook.setId(id);
		cookRepository.save(cook);
	}

	public void deleteCook(Long id) {
		cookRepository.deleteById((Long) id);
		
	}


}
