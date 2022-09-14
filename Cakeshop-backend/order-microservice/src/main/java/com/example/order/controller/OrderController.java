package com.example.order.controller;

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

import com.example.order.feign.AuthFeign;
import com.example.order.model.JwtResponse;
import com.example.order.OrderMicroserviceApplication;
import com.example.order.exception.ResourceNotFoundException;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@Slf4j

public class OrderController {
	private final static Logger log=LoggerFactory.getLogger(OrderMicroserviceApplication.class);
	@Autowired
	private OrderService os;

	@Autowired
	private AuthFeign authFeign;
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(
			@RequestHeader(name = "authorization", required = false) String token) {
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		List<Order> list = os.getAllOrders();

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


	@PostMapping("/addorder")
	public ResponseEntity<Order> addOrder(
			@RequestHeader(name = "authorization", required = false) String token, @RequestBody Order order) {
		log.info("START");
		Order orders = null;
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
			
				orderRepository.save(order);
				
                return ResponseEntity.status(HttpStatus.CREATED).build();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		

	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<Void> deleteOrder(@RequestHeader(name = "authorization", required = false) String token,
			@PathVariable("id") Long id) {
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
				this.os.deleteOrder(id);
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

	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(
			@RequestHeader(name = "authorization", required = false) String token, @RequestBody Order order,
			@PathVariable("id") Long id) {
		// this.vs.updateVehicle(vehicleEntity,registrationNo);
		log.info("START");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		try {
			if (jwtResponse.isValid()) {
				this.os.updateOrder(order, id);
				return ResponseEntity.ok().body(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		log.info("END");
		return ResponseEntity.ok().body(order);
	}
}
