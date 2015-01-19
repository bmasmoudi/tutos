package com.bmasmoudi.tutos.mockito;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.bmasmoudi.tutos.model.Order;
import com.bmasmoudi.tutos.model.Product;
import com.bmasmoudi.tutos.service.OrderService;
import com.google.common.collect.Lists;

public class Mocito_When_ThenReturn_DeepMocking_Test {

	OrderService service;

	@Before
	public void init() {
		service = new OrderService();
	}

	@Test
	public void OrderService_getTotalPrice_test() throws Exception {

		// given
		Order order = mock(Order.class);

		// when; il faut mocker tous les appels faits à order dans le service getTotatlPrice
		when(order.getProducts()).thenReturn(Lists.newArrayList(new Product(BigDecimal.ZERO)));
		BigDecimal totatlPrice = service.getTotatlPrice(order);

		// then
		assertThat(totatlPrice).isEqualTo(BigDecimal.ZERO);

	}

	@Test
	public void OrderService_getExamplePrice_with_deep_mocking_test() throws Exception {

		// given => utilisation du deep mocking car le service appelle une methode en profondeur sur l'objet
		Order order = mock(Order.class, RETURNS_DEEP_STUBS);

		// when; il faut mocker tous les appels faits à order dans le service getTotatlPrice
		when(order.getFirstProduct().getPrice()).thenReturn(new BigDecimal(1));
		BigDecimal totatlPrice = service.getExamplePrice(order);

		// then
		assertThat(totatlPrice).isEqualTo(new BigDecimal(1));

	}
}
