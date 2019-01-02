package org.betavzw.mijnenveger.engine;

public class BomVakje extends BordVakje {
	@Override
	public boolean klik() {
		if (getToestand() != VeldToestandEnum.start) throw new IllegalArgumentException("Toestand: " + getToestand());
		setToestand(VeldToestandEnum.mijn);
		return true;
	}
}
