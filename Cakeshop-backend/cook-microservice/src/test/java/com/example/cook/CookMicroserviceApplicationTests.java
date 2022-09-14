package com.example.cook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.cook.controller.CookController;
import com.example.cook.model.Cook;
import com.example.cook.model.JwtResponse;
import com.example.cook.repository.CookRepository;

@SpringBootTest
class CookMicroserviceApplicationTests {

	@Autowired
	CookController cc;
	
	@MockBean
	CookRepository crepo;
	
	
	@Test
	public void testGetAllCooks00() {
		List<Cook> vll= new ArrayList<>();
		vll.add(new Cook("200","Ramya", "pie"));
		vll.add(new Cook("201" ,"Des", "cake"));
		
		Mockito.when(crepo.findAll()).thenReturn(vll);
		List<Cook> vl = (List<Cook>) crepo.findAll();
		assertEquals(vll, vl);
	}
	
	@Test
	public void testGetAllCooks01() {
		List<Cook> vll= new ArrayList<>();
		vll.add(new Cook("200" ,"Ramya", "pie"));
		vll.add(new Cook("201","ghar", "cake"));
		
		Mockito.when(crepo.findAll()).thenReturn(vll);
		List<Cook> vl = (List<Cook>) crepo.findAll();
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
