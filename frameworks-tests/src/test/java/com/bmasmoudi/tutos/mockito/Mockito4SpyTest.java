package com.bmasmoudi.tutos.mockito;

//guva
import static com.google.common.collect.Lists.newArrayList;
//fest
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.bmasmoudi.tutos.model.Order;
import com.bmasmoudi.tutos.model.Product;
//mockito

public class Mockito4SpyTest {

	/**
	 * Mauvais test : Le test n'est pas en isolation complète puisqu’il dépend de la méthode getTotalPrice() ==>
	 * utiliser les spy
	 * 
	 * @throws Exception
	 */
	@Test
	public void should_format_total_price_to_10_00_euros_string() throws Exception {
		Product product = mock(Product.class);
		when(product.getPrice()).thenReturn(BigDecimal.TEN);
		Order order = new Order(newArrayList(product));
		assertThat(order.formatTotalPrice(Locale.FRANCE)).isEqualTo("10,00 €");
	}

	/**
	 * Mauvais Code car on ne peut pas tester le comportement d'une méthode si elle est appelée sur un objet mock 100%
	 * ==> utiliser les spy
	 * 
	 * @throws Exception
	 */
	// @Test(expected = Exception.class)
	public void should_format_total_price_to_10_00_euros_string9999999999() throws Exception {
		Order order = mock(Order.class);
		when(order.getTotalPrice()).thenReturn(BigDecimal.TEN);
		// assertThat(order.formatTotalPrice(Locale.FRANCE)).isEqualTo("10,00 €");
	}

	/**
	 * Utilisation du spy = mock partiel La vari methode getTotalPrice n'est pas appelée Mais la vrai methode
	 * formatTotalPrice est appelee
	 * 
	 * @throws Exception
	 */
	@Test
	public void should_format_total_price_to_10_00_euros_string_SPY() throws Exception {
		Order order = spy(new Order(mock(List.class)) {
			@Override
			public BigDecimal getTotalPrice() throws Exception {
				return BigDecimal.TEN;
			}
		});
		// OU bien ecrire ce code :doReturn(BigDecimal.TEN).when(order).getTotalPrice();
		assertThat(order.formatTotalPrice(Locale.FRANCE)).isEqualTo("10,00 €");
	}

}
