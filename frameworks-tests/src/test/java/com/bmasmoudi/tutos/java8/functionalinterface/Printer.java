package com.bmasmoudi.tutos.java8.functionalinterface;

@FunctionalInterface
public interface Printer<T> {

	String print(T t);

	default String method(final T t) {
		return t.toString();
	}
}
