package com.example.desserts;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.desserts.model.JwtResponse;
import com.example.desserts.controller.DessertController;
import com.example.desserts.repository.DessertRepository;
import com.example.desserts.model.Dessert;


@SpringBootTest
class DessertsMicroserviceApplicationTests {

	@Autowired
	DessertController dc;
	
	@MockBean
	DessertRepository drepo;
	
	
	@Test
	public void testGetAllDesserts00() {
		List<Dessert> vll= new ArrayList<>();
		vll.add(new Dessert("100", "Chocofills", "pie"));
		vll.add(new Dessert("101", "furhalla", "cake"));
		
		Mockito.when(drepo.findAll()).thenReturn(vll);
		List<Dessert> vl = (List<Dessert>) drepo.findAll();
		assertEquals(vll, vl);
	}
	
	@Test
	public void testGetAllDesserts01() {
		List<Dessert> vll= new ArrayList<>();
		vll.add(new Dessert("100","Chocofills", "pie"));
		vll.add(new Dessert("101" ,"furhalala", "scone"));
		
		Mockito.when(drepo.findAll()).thenReturn(vll);
		List<Dessert> vl = (List<Dessert>) drepo.findAll();
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
	
//	@Test
//	public void testDeleteDessertByDessertNumber(){
//		List<Dessert> ve =new ArrayList<Dessert>();
//		ve.add(new Dessert("Chocofills", "pie"));
//		ve.add(new Dessert("creaofancy", "cake"));
//
//		Mockito.when(drepo.deleteDessert(1)).thenReturn((List<Dessert>) ve);
//		List<Dessert> vel = drepo.deleteDessert(1);
//		
//		assertEquals(ve,vel);
//		
//		
//}
}
