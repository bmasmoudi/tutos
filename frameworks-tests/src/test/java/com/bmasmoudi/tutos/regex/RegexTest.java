package com.bmasmoudi.tutos.regex;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class RegexTest {

	@Test
	public void testNumeric() {
		assertThat("ab".matches("ab|xy")).isTrue();
		assertThat("DE".matches("(?!FR)\\w+")).isTrue();
		assertThat("DE".matches("(!?(DE|FE))")).isTrue();
		assertThat("DE".matches("DE")).isTrue();
		assertThat("DE".matches("FR|BE")).isFalse();
	}
}
