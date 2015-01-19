package com.bmasmoudi.tutos.java8;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import com.bmasmoudi.tutos.java8.functionalinterface.Predicate_Test;
import com.bmasmoudi.tutos.java8.functionalinterface.Printer;
import com.google.common.collect.Lists;

public class MethodeReferencesTest {

	/*
	 * On peut instantier un objet "implémentant" une interface fonctionnelle en utilisant des méthode référence la
	 * condition c'est que les params de la méthode à implémenter matchent avec les params de l'interface fonctionnelle
	 * ça revient à passer une méthode en paramètre
	 */
	@Test
	public void testMethodeReference() {
		System.out.println();

		List<String> chaines = Lists.newArrayList("s1", "s2");

		// java7
		for (String c : chaines) {
			System.out.println(c);
		}

		// java8 lambda
		Consumer<String> consumer = s -> System.out.println(s);
		chaines.forEach(consumer);

		// ou encore en une ligne
		chaines.forEach(s -> System.out.println(s));

		// ou encore java8 avec les méthodes références
		Consumer<String> consumer2 = System.out::println;
		chaines.forEach(consumer2); // Classe :: méthode d’instance

		// ou encore en une ligne
		chaines.forEach(System.out::println);
		// c'est comme ci on passait en paramètre la fonction println qui est un bon condidat pour implémenter la
		// méthode de l'interface consumer car elle prend un param Object et retourne void

		Printer<Integer> printer = s -> s.toString();
		Consumer<Integer> consumer_1 = printer::method;
		Consumer<Integer> consumer_2 = printer::print;
	}

	// String::compareTo signifie (String s1, String s2) -> s1.compareTo(s2)
	@Test
	public void testcompareTo() {
		// java7
		Comparator<String> comparator_java7 = new Comparator<String>() {

			@Override
			public int compare(final String o1, final String o2) {
				return o1.compareTo(o2);
			}
		};

		// en lambda
		Comparator<String> comparable_java8 = (o1, o2) -> o1.compareTo(o2);

		// en methode reference=>
		// String::compareTo signifie (String s1, String s2) -> s1.compareTo(s2)
		Comparator<String> comparable_java8_ref = String::compareTo;
	}

	// Référencer this => this::equals signifie (Object x) -> this.equals(x)
	@Test
	public void testEquals() {
		// java7
		Predicate_Test<Object> predicate_java7 = new Predicate_Test<Object>() {
			@Override
			public boolean test(final Object t) {
				return this.equals(t);
			}
		};

		// en lambda: Attention ce code est erroné!!!!!!!!! car this signifie l'objet de la classe MethodeReferencesTest
		// et non
		// pas de la classe Predicate_Test
		Predicate_Test<Object> predicate_java8_lambda = (o) -> this.equals(o);

		// en methode reference=>
		// this => this::equals signifie (Object x) -> this.equals(x)
		// Mais attention le this c'est toujours MethodeReferencesTest comme dans les lambdas
		Predicate_Test<Object> predicate_java8_ref = this::equals;

		// true
		assertTrue(predicate_java7.test(predicate_java7));
		// false
		assertFalse(predicate_java8_lambda.test(predicate_java8_lambda));
		// false
		assertFalse(predicate_java8_ref.test(predicate_java8_ref));
	}

	@Override
	public boolean equals(final Object obj) {
		return false;
	}

}