package com.bmasmoudi.tutos.mockito;

//fest
//mockito
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.bmasmoudi.tutos.model.Order;

public class Mockito5VerificationTest {

	/**
	 */
	@Test
	public void should_format_total_price_to_10_00_euros_string() throws Exception {

		Order order = spy(new Order(mock(List.class)));
		doReturn(BigDecimal.TEN).when(order).getTotalPrice();
		// assertThat(order.formatTotalPrice(Locale.FRANCE)).isEqualTo("10,00 €");

		// verifier que la methode a été appelée une seule fois
		// verify(order, times(1)).getTotalPrice();

	}
}
