package org.betavzw.mijnenveger.engine;

import java.util.Collection;

public interface Bord {
	void klikVakje(int rij, int kolom);
	void markeerVakje(int rij, int kolom);
	VeldToestandEnum getVeldToestand(int rij, int kolom);
	BordToestandEnum getToestand();
	int getAantalRijen();
	int getAantalKolommen();
	Collection<Vakje> getBuren(int rij, int kolom);
}
