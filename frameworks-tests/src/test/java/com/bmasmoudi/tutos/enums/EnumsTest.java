package com.bmasmoudi.tutos.enums;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.bmasmoudi.tutos.model.enums.PrenomsEnum;

public class EnumsTest {

	@Test
	public void contain_test() {
		// given
		String x = "AD";
		// when
		boolean test1 = contains1(x);
		boolean test2 = contains2(x);
		// then
		assertThat(test1).isEqualTo(true);
		assertThat(test2).isEqualTo(true);

	}

	/**
	 * Premiere methode de tester qu'une chaine "appartient" à l'enum utiliser valueOf et cathcher s'il y a une
	 * exception
	 */
	public static boolean contains1(final String value) {
		try {
			PrenomsEnum.valueOf(value);
			return true;
		} catch (java.lang.IllegalArgumentException ex) {
			return false;
		}
	}

	/**
	 * Deuxieme methode de tester qu'une chaine "appartient" à l'enum boucler sur les caleurs de l'enum
	 * */
	public static boolean contains2(final String value) {

		PrenomsEnum[] values = PrenomsEnum.values();
		for (PrenomsEnum val : values) {
			if (val.toString().equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Les enums java etendent java.lang.Enum
	 */
	@Test
	public void test_Enum() {
		boolean test = PrenomsEnum.AD instanceof java.lang.Enum;
		assertThat(test).isEqualTo(true);
	}
}
