package org.betavzw.mijnenveger.engine;

public class Positie {
	private int rij;
	private int kolom;
	public Positie(int rij, int kolom) {
		this.rij = rij;
		this.kolom = kolom;
	}
	public int getRij() {
		return rij;
	}
	public int getKolom() {
		return kolom;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kolom;
		result = prime * result + rij;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Positie other = (Positie) obj;
		if (kolom != other.kolom)
			return false;
		if (rij != other.rij)
			return false;
		return true;
	}
	
}
