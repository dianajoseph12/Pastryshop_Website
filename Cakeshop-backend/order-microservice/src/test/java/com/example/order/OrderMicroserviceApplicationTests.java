package com.example.order;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.order.controller.OrderController;
import com.example.order.model.Order;
import com.example.order.model.JwtResponse;
import com.example.order.repository.OrderRepository;

@SpringBootTest
class OrderMicroserviceApplicationTests {

	@Autowired
	OrderController oc;
	
	@MockBean
	OrderRepository orepo;
	
	
	@Test
	public void testGetAllCooks00() {
		List<Order> vll= new ArrayList<>();
		vll.add(new Order("300","Chocopie", "Ramya", 2, 500));
		vll.add(new Order("301","Waffleobash", "Des", 1, 200));
		
		Mockito.when(orepo.findAll()).thenReturn(vll);
		List<Order> vl = (List<Order>) orepo.findAll();
		assertEquals(vll, vl);
	}
	
	@Test
	public void testGetAllCooks01() {
		List<Order> vll= new ArrayList<>();
		vll.add(new Order("300","Chocopie", "Ramya", 2, 500));
		vll.add(new Order("301","Sprinkelemash", "Devi", 1, 250));
		
		Mockito.when(orepo.findAll()).thenReturn(vll);
		List<Order> vl = (List<Order>) orepo.findAll();
		assertEquals(vll, vl);
	}

	@Test
	public void testJwtrepo() {
		JwtResponse jwtr=new JwtResponse("root","root",true);
		jwtr.setValid(false);
		assertEquals("root",jwtr.getUsername());
		
	}
	@Test
	public void testJwtrepo2() {
		JwtResponse jwtr=new JwtResponse("root","root",true);
		jwtr.setUserid("admin");
		assertEquals("admin",jwtr.getUserid());
		
	}
	
	@Test
	public void testJwtrepo1() {
		JwtResponse jwtr=new JwtResponse("root","root",false);
		JwtResponse jj=new JwtResponse();
		
		jj.setUsername("admin");
		assertEquals(false,jwtr.isValid());
		
	}

}
