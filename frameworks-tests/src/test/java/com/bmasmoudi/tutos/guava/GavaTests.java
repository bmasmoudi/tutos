package com.bmasmoudi.tutos.guava;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Joiner;

public class GavaTests {
	/*
	 * Test de création de Listes avec gava
	 */
	@Test
	public void test() {
		// utilisation de Lists.newArrayList avec ou sans arguments
		List<Integer> list1 = com.google.common.collect.Lists.newArrayList();
		list1 = com.google.common.collect.Lists.newArrayList(1, 2, 5);
		List<Integer> list2 = com.google.common.collect.Lists.newArrayList(0, 8, 1);
		// utilisation de java.util.Collections.disjoint
		org.fest.assertions.Assertions.assertThat(java.util.Collections.disjoint(list1, list2)).isFalse();
	}

	/*
	 * Joiner
	 * 
	 * Test de com.google.common.base.Joiner qui permet d'"afficher" le contenu d'une collection en mettant le bon
	 * séparateur houhaité
	 */
	@Test
	public void test_Sets() {
		Set<String> set1 = new LinkedHashSet<String>();
		set1.add("Bilel");
		set1.add("Molka");
		set1.add("Mohammad");
		System.out.println(set1);
		String joinedSet = Joiner.on(" and ").join(set1);
		System.out.println(joinedSet);
	}

}
