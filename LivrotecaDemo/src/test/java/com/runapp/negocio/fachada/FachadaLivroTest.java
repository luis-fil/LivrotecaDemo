package com.runapp.negocio.fachada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class FachadaLivroTest {
	@Autowired
	private Fachada fachada;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
