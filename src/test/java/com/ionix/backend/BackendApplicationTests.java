package com.ionix.backend;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(BackEndApplication.class)
public class BackendApplicationTests {

	@Test
	public void backendAppTest() {
		BackEndApplication.main(new String[0]);
		assertTrue(Boolean.TRUE);
	}

}
