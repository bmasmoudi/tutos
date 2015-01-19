package com.bmasmoudi.tutos.model;

import com.bmasmoudi.tutos.model.builder.PersonneBuilder;

public class Personne {

	protected String nom;
	protected String preNom;
	protected Integer age;

	public Personne() {
		super();
	}

	public Personne(final String nom, final String preNom, final Integer age) {
		super();
		this.nom = nom;
		this.preNom = preNom;
		this.age = age;
	}

	public Personne(final PersonneBuilder personneBuilder) {
		this.nom = personneBuilder.getNom();
		this.preNom = personneBuilder.getPreNom();
		this.age = personneBuilder.getAge();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPreNom() {
		return preNom;
	}

	public void setPreNom(final String preNom) {
		this.preNom = preNom;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", preNom=" + preNom + ", age=" + age + "]";
	}

}
