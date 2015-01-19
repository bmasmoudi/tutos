package com.bmasmoudi.tutos.collections;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

public class MapsTets {

	@Test
	public void test_empty_map() {
		Map<Integer, String> map = com.google.common.collect.Maps.newHashMap();
		assertThat(map.keySet()).isEmpty();
	}

	@Test
	public void test_on_peut_rajouter_un_element_null_dans_la_map() {
		Map<Integer, String> map = com.google.common.collect.Maps.newHashMap();
		map.put(1, null);
		// attention map.get(elt) == null, ne veut pas dire que elt n'existe pas dans la map
		assertThat(map.containsKey(1)).isTrue();
	}

}
