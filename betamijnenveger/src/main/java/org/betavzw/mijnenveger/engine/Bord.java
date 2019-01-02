package org.betavzw.mijnenveger.engine;

public interface Bord {
	void klikVakje(int rij, int kolom);
	void markeerVakje(int rij, int kolom);
	BordToestandEnum getToestand();
}
