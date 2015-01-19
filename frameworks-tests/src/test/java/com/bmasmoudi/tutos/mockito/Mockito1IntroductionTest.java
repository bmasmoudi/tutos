package com.bmasmoudi.tutos.mockito;

//guva
import static com.google.common.collect.Lists.newArrayList;
//fest
import static org.fest.assertions.Assertions.assertThat;
//mockito
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Test;

import com.bmasmoudi.tutos.model.Order;
import com.bmasmoudi.tutos.model.Product;

public class Mockito1IntroductionTest {

	/**
	 * 
	 * Article: http://blog.xebia.fr/2013/02/19/craftsman-recipes-happy-hour-pour-vos-tests-avec-mockito/#more-13324
	 * 
	 * Utilisation de al méthode mock de Mocito
	 * 
	 * @throws Exception
	 */
	@Test
	public void should_instantiate_an_order_with_2_products() throws Exception {
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);
		Order order = new Order(newArrayList(product1, product2));
		assertThat(product1).isNotNull();
		assertThat(product2).isNotNull();
		assertThat(order.getProducts()).hasSize(2);
	}

	/**
	 * Tets de la méthode mock en lui passant les arguments RETURNS_DEFAULTS, RETURNS_SMART_NULLS & RETURNS_MOCKS
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_default_answer_mocking() throws Exception {
		// RETURNS_DEFAULTS
		Product product1 = mock(Product.class, RETURNS_DEFAULTS);
		assertThat(product1.getPrice()).isNull();
		// product1.getPrice().abs() -> NPE avec un message d'exception non explicite

		// RETURNS_SMART_NULLS
		Product product2 = mock(Product.class, RETURNS_SMART_NULLS);
		assertThat(product2.getPrice()).isNotNull();
		// product2.getPrice().abs() -> NPE avec un message d'exception explicite

		// RETURNS_MOCKS
		Product product3 = mock(Product.class, RETURNS_MOCKS);
		assertThat(product3.getPrice()).isNotNull();
		assertThat(product3.getPrice().abs()).isNotNull();
		assertThat(product3.getPrice().getClass().getName()).contains(BigDecimal.class.getName());
	}

}
