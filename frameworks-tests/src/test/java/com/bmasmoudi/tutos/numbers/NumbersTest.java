package com.bmasmoudi.tutos.numbers;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class NumbersTest {

	@Test
	public void testNumeric() {
		String string_12 = "12";
		assertThat(org.apache.commons.lang3.math.NumberUtils.isNumber(string_12)).isTrue();

		assertThat(Long.valueOf(string_12)).isEqualTo(12L);

		assertThat(Integer.valueOf(string_12)).isEqualTo(12);
	}
}
