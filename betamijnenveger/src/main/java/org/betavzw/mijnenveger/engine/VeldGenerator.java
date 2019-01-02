package org.betavzw.mijnenveger.engine;

public interface VeldGenerator  {
	int getAantalRijen();
	int getAantalKolommen();
	boolean isBom(int rij, int kolom) throws IllegalArgumentException;
}
