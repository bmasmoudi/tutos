package com.bmasmoudi.tutos.model;

import java.math.BigDecimal;

public class Product {

	private final BigDecimal price;

	public Product(final BigDecimal price) {
		this.price = price != null ? price : BigDecimal.ZERO;
	}

	public BigDecimal getPrice() throws Exception {
		return price;
	}

}