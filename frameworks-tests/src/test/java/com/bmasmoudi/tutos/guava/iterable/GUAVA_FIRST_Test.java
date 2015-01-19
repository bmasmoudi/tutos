package com.bmasmoudi.tutos.guava.iterable;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class GUAVA_FIRST_Test {

	@Test
	public void test_transform() {
		Collection<String> coll = Lists.newArrayList("A", "B", "C");
		String firstElement = coll.iterator().next();
		String firstElementWithGava = Iterables.getFirst(coll, null);
		assertEquals(firstElement, firstElementWithGava);

	}

}
