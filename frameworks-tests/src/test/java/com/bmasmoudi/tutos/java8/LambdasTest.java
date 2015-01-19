package com.bmasmoudi.tutos.java8;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Test;

public class LambdasTest {

	/*
	 * Une lambda expression est une implémentation d’une interface fonctionnelle
	 * 
	 * Principe des lambda, on sait que l'interface a une seule méthode abstraite à définir, donc pas la peine de
	 * déclarer son nom il suffit d'écrire ses paramètres (x1,x2...) -> {code}
	 * 
	 * si le code est une seule instruction on peut ne pas mettre les accomlades (x1,x2...) -> instruction Avantage:
	 * moins de code à écrire
	 */
	@Test
	public void testComparable() {

		// java7
		Comparator<String> cmp7 = new Comparator<String>() {
			@Override
			public int compare(final String s1, final String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		};

		Comparator<String> cmp8 = (final String s1, final String s2) -> Integer.compare(s1.length(), s2.length());

		assertEquals(cmp7.compare("AAAAA", "AZERTY"), cmp8.compare("AAAAA", "AZERTY"));
	}

	@Test
	public void testRunnable() {

		// java7
		Runnable r7 = new Runnable() {

			@Override
			public void run() {
				System.out.println("hello world");
			}
		};
		r7.run();

		// java8
		Runnable r8 = () -> System.out.println("hello world");
		r8.run();
	}

	/*
	 * Dans l'ecriture de la lambda expression, on peut même ne pas écrire le type car il est connu dans le "type" du
	 * Prediacte
	 */
	@Test
	public void testPredicate() {

		// java7
		Predicate<String> prediacte7 = new Predicate<String>() {
			@Override
			public boolean test(final String t) {
				return t != null && t.length() > 2;
			}
		};

		// java8
		Predicate<String> prediacte8 = (s) -> s != null & s.length() > 2;

		// test
		assertEquals(prediacte7.test("A"), prediacte8.test("A"));

	}

	@Test
	public void testConsumer() {
		Consumer<String> cons = (s) -> System.out.println(s);
		cons.accept("AA");
	}

}