package com.bmasmoudi.tutos.guava.iterable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.bmasmoudi.tutos.model.Personne;
import com.bmasmoudi.tutos.model.builder.PersonneBuilder;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Flitrer_Any_Test {

	@Test
	public void test_filter() {
		Personne p1 = PersonneBuilder.newInstance().withNom("Mas")//
				.withPreNom("Omar")//
				.withAge(21)//
				.build();
		Personne p2 = PersonneBuilder.newInstance().withNom("Kan")//
				.withPreNom("Khaled")//
				.withAge(30)//
				.build();
		System.out.println(p1);
		System.out.println(p2);
		Iterable<Personne> matchingPersonneWithAge = getMatchingPersonneWithAge(Lists.newArrayList(p1, p2), 300);
		assertEquals(matchingPersonneWithAge.iterator().next().getNom(), "Kan");

	}

	@Test
	public void test_any() {
		Personne p1 = PersonneBuilder.newInstance().withAge(20).withNom("Mas")//
				.withPreNom("Omar")//
				.withAge(21)//
				.build();
		Personne p2 = PersonneBuilder.newInstance().withAge(30).withNom("Kan")//
				.withPreNom("Khaled")//
				.withAge(30)//
				.build();
		System.out.println(p1);
		System.out.println(p2);
		boolean res = existMatchingPersonneWithAge(Lists.newArrayList(p1, p2), 30);
		assertTrue(res);

	}

	Iterable<Personne> getMatchingPersonneWithAge(final Collection<Personne> personnes, final Integer age) {
		// fliter les paersonnes ayant l'age 30
		Predicate<Personne> predicate = new Predicate<Personne>() {
			public boolean apply(final Personne input) {
				return age.equals(input.getAge());
			}
		};
		Iterable<Personne> matchingPersonnes = Iterables.filter(personnes, predicate);
		return matchingPersonnes;
	}

	boolean existMatchingPersonneWithAge(final Collection<Personne> personnes, final Integer age) {
		// fliter les paersonnes ayant l'age 30
		Predicate<Personne> predicate = new Predicate<Personne>() {
			public boolean apply(final Personne input) {
				return age.equals(input.getAge());
			}
		};
		return Iterables.any(personnes, predicate);
	}

}
