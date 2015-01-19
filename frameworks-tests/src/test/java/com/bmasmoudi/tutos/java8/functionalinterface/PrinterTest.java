package com.bmasmoudi.tutos.java8.functionalinterface;

import java.util.function.Consumer;

import org.junit.Test;

public class PrinterTest {

	@Test
	public void testPrinter() {
		Printer<Integer> printer = s -> s.toString();
		System.out.println(printer.print(25));

		Consumer<Integer> consumer1 = printer::method;
		Consumer<Integer> consumer2 = printer::print;
	}
}
