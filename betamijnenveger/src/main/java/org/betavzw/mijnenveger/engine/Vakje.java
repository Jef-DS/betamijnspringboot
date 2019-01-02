package org.betavzw.mijnenveger.engine;

import java.util.Collection;

public interface Vakje {
	VeldToestandEnum getToestand();
	boolean klik();
	void markeer();
	void toon();
	void setBuren(Collection<Vakje> buren);
	static Vakje MaakVakje(Bord speelbord, boolean heeftMijn) {
		Vakje v;
		if (!heeftMijn)
			v= new BordVakje();
		else
			v= new BomVakje();
		return v;
	}
}
