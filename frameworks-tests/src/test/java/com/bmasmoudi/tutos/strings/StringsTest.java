package com.bmasmoudi.tutos.strings;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class StringsTest {

	@Test
	public void test() {
		String x = "01234";
		System.out.println(x.substring(0, 3));

	}

	@Test
	public void test_String_To_Integer() {
		System.out.println(Integer.parseInt("12"));
	}

	@Test
	public void test_StringUtils_Equals() {
		assertTrue(StringUtils.equals(null, returnNull()));
	}

	String returnNull() {
		return null;
	}

}