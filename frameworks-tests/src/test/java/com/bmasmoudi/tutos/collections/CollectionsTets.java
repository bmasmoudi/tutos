package com.bmasmoudi.tutos.collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class CollectionsTets {

	/*
	 * Test de la méthode equals sur des objets du type java.util.List Si les éléments sont insérés dans le même ordre
	 * c'est Equals Sinon les 2 listes ne sont pas égales
	 */
	@Test
	public void test_Equals() {
		List<Integer> list1 = com.google.common.collect.Lists.newArrayList(1, 5);
		List<Integer> list2 = new ArrayList<>();
		list2.add(1);
		list2.add(5);
		assertEquals(list1, list2);
		List<Integer> list3 = com.google.common.collect.Lists.newArrayList(5, 1);
		assertThat(list1).isNotEqualTo(list3);
	}

	/*
	 * Rappel: les sets en contiennent pas de doublons
	 * 
	 * Test des implémentations des sets,
	 * 
	 * le linkedHashset garde l'ordre d'insertion
	 * 
	 * HashSet does not maintain any order
	 * 
	 * TreeSet maintains sorting order or elements
	 */
	@Test
	public void test_Sets() {
		Set<Integer> set1 = new LinkedHashSet<Integer>();
		set1.add(2);
		set1.add(1);
		Set<Integer> set2 = new TreeSet<Integer>();
		set2.add(1);
		set2.add(2);
		System.out.println(set1);
		System.out.println(set2);
	}

	@Test(expected = NullPointerException.class)
	public void test_add_all_with_null_param() {
		Collection<String> col = new ArrayList<String>();
		col.addAll(null);

	}

	/*
	 * La methode contains utilise l'egalite des objets
	 */
	@Test
	public void test_contains() {
		List<Integer> list1 = com.google.common.collect.Lists.newArrayList(2, 1);
		boolean contains = list1.contains(Integer.parseInt("1"));
		System.out.println(contains);
	}

	@Test
	public void test_print() {

		// utilisation de Lists.newArrayList avec ou sans arguments
		List<Integer> list1 = com.google.common.collect.Lists.newArrayList(1, 2, 5);
		String string = list1.toString();
		System.out.println(string);
		String substring = string.substring(1, list1.size() - 2);
		System.out.println(substring);
	}
}
