package com.designingmicroservices.dockerized;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.designingmicroservices.dockerized.controller.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DockerizedApplicationTests {

	@Autowired
	private TestRestTemplate client;

	@Test
	public void greetingTest() {

		ResponseEntity<Greeting> greeting = client.getForEntity("/greeting?name=pravsingh", Greeting.class);

		Assert.assertEquals(HttpStatus.OK.value(), greeting.getStatusCode().value());

		Assert.assertEquals("Hello, pravsingh!", greeting.getBody().getContent());
	}

	@Test
	public void swaggerDocsTest() {

		ResponseEntity<String> response = client.getForEntity("/v2/api-docs", String.class);

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());

		Assert.assertTrue(response.getBody().toString().contains("\"swagger\":\"2.0\""));
	}

}
