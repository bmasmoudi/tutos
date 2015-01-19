package com.bmasmoudi.tutos.arrays;

import org.junit.Test;

public class ArraysTest {

	@Test
	public void test() {

		Integer[] old = { 1, 2, 3 };
		Integer[] arr = new Integer[old.length];
		System.arraycopy(old, 0, arr, 0, old.length);
		System.out.println(arr);

	}

}
