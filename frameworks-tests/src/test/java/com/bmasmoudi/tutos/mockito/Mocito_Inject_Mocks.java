package com.bmasmoudi.tutos.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.fop.render.txt.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/*
 * Utilisation des annotaion: 
 * @Mock: pour injecter des mcoks à la classe testée
 * @InjectMocks: pour injecter les mcoks déclarées avec "@Mock" à la classe testée
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class Mocito_Inject_Mocks {

	@Mock
	private Helper helperMock;

	@InjectMocks
	private Service service;

	@Test
	public void test() throws Exception {
		// case
		when(helperMock.toString()).thenReturn("value1");

		// when
		String doService = service.doService();

		// then
		assertEquals(doService, "value1");

	}

	// Calss UNDER TEST
	public class Service {

		// helper to inject
		private Helper helper;

		public String doService() {
			return helper.toString();
		}

		public void setHelper(final Helper helper) {
			this.helper = helper;
		}
	}
}
