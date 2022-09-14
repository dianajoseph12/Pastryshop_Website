package com.example.cook.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cook.model.Cook;
import com.example.cook.repository.CookRepository;
import com.example.cook.CookMicroserviceApplication;
import com.example.cook.model.Cook;
import com.example.cook.repository.CookRepository;
import com.example.cook.service.CookService;

import lombok.extern.slf4j.Slf4j;

import com.example.cook.feign.AuthFeign;
import com.example.cook.model.JwtResponse;
import com.example.cook.controller.CookController;
import com.example.cook.exception.ResourceNotFoundException;



@CrossOrigin(origins = "*")
@RestController
@Slf4j

public class CookController {
	private final static Logger log=LoggerFactory.getLogger(CookMicroserviceApplication.class);
	@Autowired
	private CookService cs;

	@Autowired
	private AuthFeign authFeign;
	@Autowired
	private CookRepository cookRepository;

	@GetMapping("/cooks")
	public ResponseEntity<List<Cook>> getAllCooks(
			@RequestHeader(name = "authorization", required = false) String token) {
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		List<Cook> list = cs.getAllCooks();

		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			if (jwtResponse.isValid()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		log.info("END");
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}


	@PostMapping("/addcook")
	public ResponseEntity<Cook> addDessert(
			@RequestHeader(name = "authorization", required = false) String token, @RequestBody Cook cook) {
		log.info("START");
		Cook cooks = null;
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
			
				cookRepository.save(cook);
				
                return ResponseEntity.status(HttpStatus.CREATED).build();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		

	}

	@DeleteMapping("/cooks/{id}")
	public ResponseEntity<Void> deleteCook(@RequestHeader(name = "authorization", required = false) String token,
			@PathVariable("id") Long id) {
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
				this.cs.deleteCook(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		log.info("END");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		// this.vs.deleteVehicle(registrationNo);
	}

	@PutMapping("/cooks/{id}")
	public ResponseEntity<Cook> updateCook(
			@RequestHeader(name = "authorization", required = false) String token, @RequestBody Cook cook,
			@PathVariable("id") Long id) {
		// this.vs.updateVehicle(vehicleEntity,registrationNo);
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
				this.cs.updateCook(cook, id);
				return ResponseEntity.ok().body(cook);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		log.info("END");
		return ResponseEntity.ok().body(cook);
	}
}

