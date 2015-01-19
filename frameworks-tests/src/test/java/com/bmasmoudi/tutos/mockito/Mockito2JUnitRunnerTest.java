package com.bmasmoudi.tutos.mockito;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bmasmoudi.tutos.model.Order;
import com.bmasmoudi.tutos.model.Product;

/**
 * Si on utiliser l'annotation @Mock il faut annoter la classe par @RunWith(MockitoJUnitRunner.class) ou bien appeler la
 * méthode org.mockito.MockitoAnnotations.initMocks dans le setup (@Before)
 * 
 * 
 * @author bilel_masmoudi
 */

@RunWith(MockitoJUnitRunner.class)
public class Mockito2JUnitRunnerTest {

	@Mock
	Product product1;
	@Mock
	Product product2;

	@Test
	public void should_instantiate_an_order_with_2_products() throws Exception {
		Order order = new Order(newArrayList(product1, product2));
		assertThat(product1).isNotNull();
		assertThat(product2).isNotNull();
		assertThat(order.getProducts()).hasSize(2);
	}
}
