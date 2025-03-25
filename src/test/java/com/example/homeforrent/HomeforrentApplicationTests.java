package com.example.homeforrent;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homeforrent.LandLord.LandLordService;
import com.example.homeforrent.LandLord.Landlord;
import com.example.homeforrent.Tenet.TenetService;

@SpringBootTest
class HomeforrentApplicationTests {

	@Autowired TenetService service;
	@Autowired LandLordService service2;
	@Test
	void contextLoads() {
	}
	@Test
	void createAccount(){
		service.createTenet("Vijay Singh", "23", "male", "student", "unmarried", "single", "Individual", "null", "vijay328", "2001");
	}
	@Test
	void createAccount2(){
		service2.createLandLord("Deepak Sharma", "single", "Individual", "deepak123", "Deepak@123"," ");
	}
	@Test
	void getLandlords(){
		List<Landlord> list = service.getAll();
		System.out.println(list);
	}
	@Test
	void getLandLordById(){
		Landlord landlord = service.getLandlordbyId("tina123");
		System.out.println(landlord);
	}
}
