package com.bmasmoudi.tutos.java8;

import java.util.function.Consumer;

import org.junit.Test;

public class ThisTest {

	@Test
	public void testMethodeReference() {
		System.out.println();

		// java7
		Consumer<String> consumer7 = new Consumer<String>() {
			@Override
			public void accept(final String s) {
				System.out.println(s + "--> this = " + this);
			}

			@Override
			public String toString() {
				return "je suis dans consumer7, classe anonyme java7";
			}
		};

		//
		consumer7.accept("A");

	}

}