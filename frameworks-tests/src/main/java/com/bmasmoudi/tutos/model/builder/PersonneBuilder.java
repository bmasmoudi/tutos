package com.bmasmoudi.tutos.model.builder;

import com.bmasmoudi.tutos.model.Personne;


public class PersonneBuilder extends Personne {

	public PersonneBuilder(final String nom, final String preNom, final Integer age) {
		super(nom, preNom, age);
		// TODO Auto-generated constructor stub
	}

	public PersonneBuilder() {
	}

	public static PersonneBuilder newInstance() {
		return new PersonneBuilder();
	}

	public Personne build() {
		return new Personne(this);
	}

	public PersonneBuilder withNom(final String nom) {
		super.nom = nom;
		return this;
	}

	public PersonneBuilder withPreNom(final String preNom) {
		super.preNom = preNom;
		return this;
	}

	public PersonneBuilder withAge(final Integer age) {
		super.age = age;
		return this;
	}

}
