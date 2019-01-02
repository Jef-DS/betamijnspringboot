package org.betavzw.mijnenveger.engine;

public interface Vakje {
	VeldToestandEnum getToestand();
	boolean klik();
	void markeer();
	void toon();
}
