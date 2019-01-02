package org.betavzw.mijnenveger.engine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BordImpl implements Bord {
	private Vakje[][] vakjes;
	private BordToestandEnum toestand = BordToestandEnum.gestart;

	public BordImpl(VeldGenerator generator) {
		vakjes = new Vakje[generator.getAantalRijen()][generator.getAantalKolommen()];
		for (int rij = 0; rij < getAantalRijen(); rij++) {
			for (int kolom = 0; kolom < getAantalKolommen(); kolom++) {
				vakjes[rij][kolom] = Vakje.MaakVakje(this, generator.isBom(rij, kolom));
			}
		}
		for (int rij = 0; rij < getAantalRijen(); rij++) {
			for (int kolom = 0; kolom < getAantalKolommen(); kolom++) {
				vakjes[rij][kolom].setBuren(getBuren(rij, kolom));
			}
		}

	}

	@Override
	public void klikVakje(int rij, int kolom) {
		if (vakjes[rij][kolom].getToestand() == VeldToestandEnum.start) {
			if (vakjes[rij][kolom].klik()) {
				toestand = BordToestandEnum.verloren;
			}
		}

	}

	@Override
	public void markeerVakje(int rij, int kolom) {
		if (vakjes[rij][kolom].getToestand() == VeldToestandEnum.start
				|| vakjes[rij][kolom].getToestand() == VeldToestandEnum.gemarkeerd) {
			vakjes[rij][kolom].markeer();
		}

	}

	@Override
	public BordToestandEnum getToestand() {
		return toestand;
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

	@Override
	public Collection<Vakje> getBuren(int rij, int kolom) {
		int minRij = Math.max(0, rij - 1);
		int maxRij = Math.min(rij + 2, getAantalRijen());
		int minKolom = Math.max(0, kolom - 1);
		int maxKolom = Math.min(kolom + 2, getAantalKolommen());
		Set<Vakje> v = new HashSet<>();
		for (int r = minRij; r < maxRij; r++) {
			for (int k = minKolom; k < maxKolom; k++) {
				if (r == rij && k == kolom)
					continue;
				v.add(vakjes[r][k]);
			}
		}
		return v;
	}

}
