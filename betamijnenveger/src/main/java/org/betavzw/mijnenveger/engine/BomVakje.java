package org.betavzw.mijnenveger.engine;

public class BomVakje extends BordVakje {
	@Override
	public boolean klik() {
		if (getToestand() != VeldToestandEnum.start) throw new IllegalArgumentException("Toestand: " + getToestand());
		toon();
		return true;
	}
	@Override
	public void toon() {
		setToestand(VeldToestandEnum.mijn);
	}
	@Override
	public boolean isBom() {
		return true;
	}
}
