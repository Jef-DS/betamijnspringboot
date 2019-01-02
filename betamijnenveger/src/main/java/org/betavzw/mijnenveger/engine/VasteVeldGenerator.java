package org.betavzw.mijnenveger.engine;

import java.util.Set;

public class VasteVeldGenerator implements VeldGenerator {

	private int rijen;
	private int kolommen;
	private Set<Positie> bomposities;
	public VasteVeldGenerator(int rijen, int kolommen, Set<Positie> bomposities) {
		this.rijen = rijen;
		this.kolommen = kolommen;
		this.bomposities = bomposities;
	}
	@Override
	public int getAantalRijen() {
		return rijen;
	}

	@Override
	public int getAantalKolommen() {
		return kolommen;
	}

	@Override
	public boolean isBom(int rij, int kolom) throws IllegalArgumentException {
		if (rij >= getAantalRijen()) throw new IllegalArgumentException(String.format("Rij %d is groter dan %d", rij, getAantalRijen()));
		if (kolom >= getAantalKolommen()) throw new IllegalArgumentException(String.format("Kolom %d is groter dan %d", kolom, getAantalKolommen()));
		if (rij < 0) throw new IllegalArgumentException(String.format("Rij %d kleiner dan 0", rij));
		if (kolom < 0) throw new IllegalArgumentException(String.format("Kolom %d kleiner dan 0", kolom));
		Positie p = new Positie(rij, kolom);
		return this.bomposities.contains(p);
	}

}
