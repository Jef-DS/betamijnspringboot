package org.betavzw.mijnenveger.engine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BordVakje implements Vakje {
	private VeldToestandEnum toestand = VeldToestandEnum.start;
	private Set<Vakje> buren = new HashSet<Vakje>();

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
		if (getAantalBomBuren() == 0) {
		for(Vakje v: buren) {
			if (v.getToestand() == VeldToestandEnum.start) {
				v.klik();
			}
		}
		}
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
	public boolean isBom() {
		return false;
	}

	@Override
	public void toon() {
		setToestand(VeldToestandEnum.values()[2+getAantalBomBuren()]);

	}
	@Override
	public void setBuren(Collection<Vakje> buren) {
		this.buren.addAll(buren);
		
	}
	private int getAantalBomBuren() {
		return (int) this.buren.stream().filter(v -> v.getClass().equals(BomVakje.class)).count();
	}

}
