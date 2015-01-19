package com.bmasmoudi.tutos.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class Order {

	List<Product> products;

	public Order(final List<Product> products) {
		this.products = products != null ? products : new ArrayList<Product>();
	}

	public BigDecimal getTotalPrice() throws Exception {
		BigDecimal total = BigDecimal.ZERO;
		for (Product product : products) {
			total = total.add(product.getPrice());
		}
		return total;
	}

	public String formatTotalPrice(final Locale locale) {
		try {
			return NumberFormat.getCurrencyInstance(locale).format(getTotalPrice());
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(final List<Product> products) {
		this.products = products;
	}

	public Product getFirstProduct() {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(products)) {
			return products.get(0);
		} else {
			return null;
		}
	}

	public List<Product> getProductsByPrice(final BigDecimal price) throws Exception {
		List<Product> res = Lists.newArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(products)) {
			for (Product product : products) {
				if (product.getPrice().equals(price)) {
					res.add(product);
				}
			}
		}
		return res;

	}
}
