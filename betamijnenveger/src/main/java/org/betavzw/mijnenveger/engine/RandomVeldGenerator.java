package org.betavzw.mijnenveger.engine;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomVeldGenerator implements VeldGenerator {

	private Set<Positie> posities = new HashSet<Positie>();
	private int aantalRijen;
	private int aantalKolommen;
	public RandomVeldGenerator(int rijen, int kolommen, int aantalBommen) {
		this.aantalRijen = rijen;
		this.aantalKolommen = kolommen;
		Random rnd = new Random();
		while(posities.size() < aantalBommen){
			int rij = rnd.nextInt(rijen);
			int kolom = rnd.nextInt(kolommen);
			Positie pos = new Positie(rij, kolom);
			posities.add(pos);
		}
	}
	@Override
	public int getAantalRijen() {
		return aantalRijen;
	}

	@Override
	public int getAantalKolommen() {
		return aantalKolommen;
	}

	@Override
	public boolean isBom(int rij, int kolom) throws IllegalArgumentException {
		if (rij >= getAantalRijen()) throw new IllegalArgumentException(String.format("Rij %d is groter dan %d", rij, getAantalRijen()));
		if (kolom >= getAantalKolommen()) throw new IllegalArgumentException(String.format("Kolom %d is groter dan %d", kolom, getAantalKolommen()));
		if (rij < 0) throw new IllegalArgumentException(String.format("Rij %d kleiner dan 0", rij));
		if (kolom < 0) throw new IllegalArgumentException(String.format("Kolom %d kleiner dan 0", kolom));
		Positie p = new Positie(rij, kolom);
		boolean bevatPositie = this.posities.contains(p);
		return bevatPositie;
	}

}
