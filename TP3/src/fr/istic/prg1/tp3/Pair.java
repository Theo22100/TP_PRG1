package fr.istic.prg1.tp3;

/**
 * 
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2022-09-23
 * 
 *        Classe représentant des doublets *non modifiables*
 */

public class Pair implements Comparable<Pair> {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pair d) {
		if (this.x < d.x || (this.x == d.x && this.y < d.y)) {
			return -1;
		}
		else if (this.equals(d)) {
			return 0;
		}
		return 1;
	}

	public Pair copyOf() {
		return new Pair(x,y);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(x + y);
		return stringBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		// DERNIER CAS À TRAITER
		return (this.x == ((Pair) obj).x && this.y == ((Pair) obj).y);
	}
}