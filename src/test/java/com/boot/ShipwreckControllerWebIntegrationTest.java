package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ShipwreckControllerWebIntegrationTest {	
	
	@Test
	public void testListAll() throws IOException {
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/shipwrecks", String.class);
				
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(response.getBody());
		
		assertThat(responseJson.isMissingNode(), is(false));
		System.out.println(responseJson.toString());
		assertThat(responseJson.toString(), equalTo("[{\"id\":1,\"name\":\"U869\",\"description\":\"A very deep German UBoat\",\"condition\":\"FAIR\",\"depth\":200,\"latitude\":44.12,\"longitude\":138.44,\"yearDiscovered\":1994},{\"id\":2,\"name\":\"Thistlegorm\",\"description\":\"British merchant boat in the Red Sea\",\"condition\":\"GOOD\",\"depth\":80,\"latitude\":44.12,\"longitude\":138.44,\"yearDiscovered\":1994},{\"id\":3,\"name\":\"S.S. Yongala\",\"description\":\"A luxury passenger ship wrecked on the great barrier reef\",\"condition\":\"FAIR\",\"depth\":50,\"latitude\":44.12,\"longitude\":138.44,\"yearDiscovered\":1994}]"));
	}
}
