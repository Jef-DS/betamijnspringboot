package org.betavzw.mijnenveger.engine;

public class BordVakje implements Vakje {
	private VeldToestandEnum toestand = VeldToestandEnum.start;
	@Override
	public VeldToestandEnum getToestand() {
		return toestand;
	}
	protected void setToestand(VeldToestandEnum toestand) {
		this.toestand = toestand;
	}
	@Override
	public boolean klik() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

	}

}
