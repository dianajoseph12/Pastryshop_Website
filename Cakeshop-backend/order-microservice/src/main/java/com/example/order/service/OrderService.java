package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;

@Component
public class OrderService {
	@Autowired(required=true)
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders() {
		List<Order> list = (List<Order>) this.orderRepository.findAll();
		return list;

	}
	
	public Order addOrder(Order o) {
		// list.add(v);
		Order result = this.orderRepository.save(o);
		return result;
	}

	public void updateOrder(Order order, Long id) {
		order.setId(id);
		orderRepository.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById((Long) id);
		
	}


}
