package com.bmasmoudi.tutos.java8.functionalinterface;

import java.util.function.Predicate;

@FunctionalInterface
public interface Predicate_Test<T> extends Predicate<T> {

	default public String print() {
		return "Predicate_Test";
	}
}
