package com.bmasmoudi.tutos.model.generics;

import java.util.List;

import com.bmasmoudi.tutos.model.inheritence.Golf;
import com.bmasmoudi.tutos.model.inheritence.Voiture;

/**
 * see http://gfx.developpez.com/tutoriel/java/generics/
 * 
 * @author bilel_masmoudi
 */
public class GenericPrinter<T> {

	String print(final T t) {
		return t.toString();
	}

	// Le type T sera déterminé à l'exécution au moyen d'etre le type le plus spécifique entre le tableau et la liste
	// si on utilise un tableau déclaré comme un tableau d'objets et une liste déclarée comme une liste de String, T
	// sera Object
	public static <T> void copy(final T[] objs, final List<T> l) {
		for (T t : objs) {
			l.add(t);
		}
	}

	public static void main(final String[] args) {
		GenericPrinter<Boolean> booleanPrinter = new GenericPrinter<Boolean>();
		String str = booleanPrinter.print(Boolean.TRUE);
		System.out.println(str);

		// Utilisation du mot clé super
		GenericPrinter<? super Voiture> voiturePrinter = new GenericPrinter();
		str = voiturePrinter.print(new Golf());
		System.out.println(str);

	}
}
