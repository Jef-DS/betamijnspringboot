package org.betavzw.mijnenveger.engine;

public class BordVakje implements Vakje {
	private VeldToestandEnum toestand = VeldToestandEnum.start;
	@Override
	public VeldToestandEnum getToestand() {
		return toestand;
	}

	@Override
	public boolean klik() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markeer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void toon() {
		// TODO Auto-generated method stub

	}

}
