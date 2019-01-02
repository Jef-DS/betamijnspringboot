package org.betavzw.mijnenveger.engine;

public class BordImpl implements Bord {
	private Vakje[][] vakjes;
	public BordImpl(VeldGenerator generator) {
		vakjes = new Vakje[generator.getAantalRijen()][generator.getAantalKolommen()];
		for(int rij=0;rij < getAantalRijen();rij++) {
			for(int kolom=0;kolom<getAantalKolommen();kolom++) {
				vakjes[rij][kolom] = Vakje.MaakVakje(this, generator.isBom(rij, kolom));
			}
		}
	}
	@Override
	public void klikVakje(int rij, int kolom) {
		vakjes[rij][kolom].klik();

	}

	@Override
	public void markeerVakje(int rij, int kolom) {
		// TODO Auto-generated method stub

	}

	@Override
	public BordToestandEnum getToestand() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public VeldToestandEnum getVeldToestand(int rij, int kolom) {
		return vakjes[rij][kolom].getToestand();
	}
	@Override
	public int getAantalRijen() {
		return vakjes.length;
	}
	@Override
	public int getAantalKolommen() {
		return vakjes[0].length;
	}

}
