package com.bmasmoudi.tutos.guava.iterable;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;

import com.bmasmoudi.tutos.model.Personne;
import com.bmasmoudi.tutos.model.builder.PersonneBuilder;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class GUAVA_TRANSFORM_Test {

	@Test
	public void test_transform() {
		Personne p1 = PersonneBuilder.newInstance().withNom("Masmoudi")//
				.build();
		Personne p2 = PersonneBuilder.newInstance().withNom("Ayadi")//
				.build();
		List<String> listOfNoms = (List<String>) getNomsFromPersonnes(Lists.newArrayList(p1, p2));

		assertEquals(listOfNoms.get(0), "Masmoudi");

	}

	/**
	 * @param personnes
	 *            : une liste de personnes, chaque poduit ayant un nom
	 * @return la liste des noms construite à partir de la liste des personnes
	 */
	public List<? extends String> getNomsFromPersonnes(final List<Personne> personnes) {
		// transform List<Personnes> to Iterable<String>
		Function<? super Personne, ? extends String> function = new Function<Personne, String>() {
			public String apply(final Personne personne) {
				return personne.getNom();
			}
		};
		Iterable<String> pass = Iterables.transform(personnes, function);

		// transform Iterable<String> to
		List<String> listOfNoms = IteratorUtils.toList(pass.iterator());
		return listOfNoms;
	}

}
