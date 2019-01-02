package org.betavzw.mijnenveger.engine;

public interface Vakje {
	VeldToestandEnum getToestand();
	boolean klik();
	void markeer();
	void toon();
	static Vakje MaakVakje(Bord speelbord, boolean heeftMijn) {
		return new BordVakje();
	}
}
