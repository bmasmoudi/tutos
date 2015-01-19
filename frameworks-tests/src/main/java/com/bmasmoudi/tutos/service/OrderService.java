package com.bmasmoudi.tutos.service;

import java.math.BigDecimal;
import java.util.List;

import com.bmasmoudi.tutos.model.Order;
import com.bmasmoudi.tutos.model.Product;

public class OrderService {

	public BigDecimal getTotatlPrice(final Order order) throws Exception {

		// varuiables
		List<Product> products = order.getProducts();

		// implementation
		BigDecimal res = BigDecimal.ZERO;
		for (Product product : products) {
			res = res.add(product.getPrice());
		}
		return res;
	}

	public BigDecimal getExamplePrice(final Order order) throws Exception {
		BigDecimal price = order.getFirstProduct().getPrice();

		// traitement maitier//
		// price = xxxx;
		// fin traitement maitier//

		return price;
	}

}
