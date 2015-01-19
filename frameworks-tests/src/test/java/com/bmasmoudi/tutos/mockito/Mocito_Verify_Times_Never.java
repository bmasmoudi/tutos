package com.bmasmoudi.tutos.mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;

import com.bmasmoudi.tutos.model.Order;

public class Mocito_Verify_Times_Never {

	@Test
	public void test() throws Exception {
		// case
		Order order = mock(Order.class);
		// when
		metier(false, order);
		// then
		verify(order, never()).getFirstProduct();
		verify(order, times(0)).getProductsByPrice(any(BigDecimal.class));

	}

	private void metier(final Boolean test, final Order order) throws Exception {
		if (test) {
			order.getFirstProduct();
		}
	}
}
