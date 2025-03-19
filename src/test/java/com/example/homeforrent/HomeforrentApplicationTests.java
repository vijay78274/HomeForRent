package com.example.homeforrent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeforrentApplicationTests {

	@Autowired TenetService service;
	@Test
	void contextLoads() {
	}
	@Test
	void createAccount(){
		service.createTenet("Vijay Singh", "23", "male", "student", "unmarried", "single", "Individual", "null", "vijay328", "2001");
	}
}
