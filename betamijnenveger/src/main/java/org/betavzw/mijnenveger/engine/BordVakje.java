package org.betavzw.mijnenveger.engine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BordVakje implements Vakje {
	private VeldToestandEnum toestand = VeldToestandEnum.start;
	private int aantalBomBuren;
	@Override
	public VeldToestandEnum getToestand() {
		return toestand;
	}
	protected void setToestand(VeldToestandEnum toestand) {
		this.toestand = toestand;
	}
	@Override
	public boolean klik() {
		if (getToestand() != VeldToestandEnum.start) throw new IllegalArgumentException("Toestand is " + getToestand());
		toon();
		return false;
	}

	@Override
	public void markeer() {
		if (toestand == VeldToestandEnum.start) {
			toestand = VeldToestandEnum.gemarkeerd;
		} else {
			toestand = VeldToestandEnum.start;
		}

	}

	@Override
	public void toon() {
		setToestand(VeldToestandEnum.values()[2+aantalBomBuren]);

	}
	@Override
	public void setBuren(Collection<Vakje> buren) {
		aantalBomBuren = 0;
		for(Vakje v: buren) {
			if (v.getClass().equals(BomVakje.class)) aantalBomBuren++;
		}
		
	}

}
