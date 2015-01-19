package com.bmasmoudi.tutos.mockito;

//guva
import static com.google.common.collect.Lists.newArrayList;
//fest
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;

import com.bmasmoudi.tutos.model.Order;
import com.bmasmoudi.tutos.model.Product;
//mockito

public class Mockito3ExampleTest {

	/**
	 * maintenant que l’on sait correctement créer nos Mocks, on va simuler le comportement d’un produit pour qu’il
	 * retourne un prix concret et donc tester notre méthode getTotalPrice()
	 * 
	 * @throws Exception
	 */
	@Test
	public void should_have_a_total_price_equal_to_8_99() throws Exception {
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);
		when(product1.getPrice()).thenReturn(new BigDecimal("3.99"));
		when(product2.getPrice()).thenReturn(new BigDecimal("5.00"));
		Order order = new Order(newArrayList(product1, product2));
		assertThat(order.getTotalPrice()).isEqualTo(new BigDecimal("8.99"));
	}

	/**
	 * simuler le déclenchement d’une exception
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalStateException.class)
	public void should_throws_exception_when_calling_get_price() throws Exception {
		Product product1 = mock(Product.class);
		when(product1.getPrice()).thenThrow(new IllegalStateException());
		product1.getPrice();
	}

}
