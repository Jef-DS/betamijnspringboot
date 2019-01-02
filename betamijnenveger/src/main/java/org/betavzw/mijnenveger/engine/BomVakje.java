package org.betavzw.mijnenveger.engine;

public class BomVakje extends BordVakje {
	@Override
	public boolean klik() {
		setToestand(VeldToestandEnum.mijn);
		return true;
	}
}
