package com.example.desserts.controller;

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

import com.example.desserts.feign.AuthFeign;
import com.example.desserts.model.JwtResponse;
import com.example.desserts.DessertsMicroserviceApplication;
import com.example.desserts.controller.DessertController;
import com.example.desserts.exception.ResourceNotFoundException;
import com.example.desserts.model.Dessert;
import com.example.desserts.repository.DessertRepository;
import com.example.desserts.service.DessertService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@Slf4j

public class DessertController {
	private final static Logger log=LoggerFactory.getLogger(DessertsMicroserviceApplication.class);
	@Autowired
	private DessertService ds;

	@Autowired
	private AuthFeign authFeign;
	@Autowired
	private DessertRepository dessertRepository;

	@GetMapping("/desserts")
	public ResponseEntity<List<Dessert>> getAllDesserts(
			@RequestHeader(name = "authorization", required = false) String token) {
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		List<Dessert> list = ds.getAllDesserts();

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


	@PostMapping("/adddessert")
	public ResponseEntity<Dessert> addDessert(
			@RequestHeader(name = "authorization", required = false) String token, @RequestBody Dessert dessert) {
		log.info("START");
		Dessert desserts = null;
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
			
				dessertRepository.save(dessert);
				
                return ResponseEntity.status(HttpStatus.CREATED).build();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		

	}

	@DeleteMapping("/desserts/{id}")
	public ResponseEntity<Void> deleteDessert(@RequestHeader(name = "authorization", required = false) String token,
			@PathVariable("id") Long id) {
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
				this.ds.deleteDessert(id);
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

	@PutMapping("/desserts/{id}")
	public ResponseEntity<Dessert> updateDessert(
			@RequestHeader(name = "authorization", required = false) String token, @RequestBody Dessert dessert,
			@PathVariable("id") Long id) {
		// this.vs.updateVehicle(vehicleEntity,registrationNo);
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
				this.ds.updateDessert(dessert, id);
				return ResponseEntity.ok().body(dessert);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		log.info("END");
		return ResponseEntity.ok().body(dessert);
	}
}

