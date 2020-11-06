package com.lti.nspapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.controller.SchemeController;
import com.lti.entity.Scheme;
import com.lti.repository.NspRepository;
import com.lti.repository.NspRepositoryImpl;
import com.lti.service.NspService;
import com.lti.service.NspServiceImpl;

@SpringBootTest
class CustomerApp1ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void addAScheme() {
		//NspService nsp = new NspServiceImpl();
		NspRepository nsp = new NspRepositoryImpl();
		Scheme s = new Scheme();
		s.setSchemeName("Scheme 2");
		s.setSchemeMinistry("Central");
		nsp.saveAScheme(s);
	}

}
