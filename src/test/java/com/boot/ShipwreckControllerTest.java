package com.boot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

public class ShipwreckControllerTest {
	@InjectMocks
	private ShipwreckController sc;
	
	@Mock
	private ShipwreckRepository shipwreckRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Shipwreck sw = new Shipwreck();
		sw.setId(1L);
		when(shipwreckRepository.findOne(1L)).thenReturn(sw);
		
		Shipwreck sw2 = new Shipwreck();
		sw2.setId(2L);
		when(shipwreckRepository.findOne(2L)).thenReturn(sw2);
		
		Shipwreck wreck = sc.get(1L);
		Shipwreck wreck2 = sc.get(2L);
		
		verify(shipwreckRepository).findOne(1L);
		verify(shipwreckRepository).findOne(2L);
		
		assertEquals(1L, wreck.getId().longValue());  // jUnit assertion
		assertThat(wreck2.getId(), is(2L)); // Hamcrest assertion
	}
}
