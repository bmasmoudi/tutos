package com.bmasmoudi.tutos.model.generics;

import com.bmasmoudi.tutos.model.inheritence.Golf;
import com.bmasmoudi.tutos.model.inheritence.Voiture;

public class GenericPrinter2<T extends Voiture> {

	String print(final T t) {
		return t.marque();
	}

	public static void main(final String[] args) {
		GenericPrinter2<Golf> booleanPrinter = new GenericPrinter2<Golf>();
		String str = booleanPrinter.print(new Golf());
		System.out.println(str);

	}
}
