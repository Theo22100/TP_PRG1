package fr.istic.prg1.tp4;

import java.util.Arrays;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2022-09-23
 */

public class SmallSet {

	/**
	 * taille de l'ensemble
	 */
	private static final int SET_SIZE = 256;

	/**
	 * Ensemble est représenté comme un tableau de booléens.
	 */
	private boolean[] tab = new boolean[SET_SIZE];

	public SmallSet() {
		for (int i = 0; i < SET_SIZE; ++i) {
			tab[i] = false;
		}
	}

	public SmallSet(boolean[] array) {
		tab = Arrays.copyOf(array, SET_SIZE);
	}

	/**
	 * @return nombre de valeurs appartenant à l’ensemble
	 */
	public int size() {
		int size = 0;
		for (int i = 0; i < SET_SIZE; ++i) {
			if(tab[i]) size++;
		}
		return size;
	}

	/**
	 * @param x valeur à tester
	 * @return true, si l’entier x appartient à l’ensemble, false sinon
	 */
	public boolean contains(int x) {
		return tab[x];
	}

	/**
	 * @return true, si l’ensemble est vide, false sinon
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Ajoute x à l’ensemble (sans effet si x déjà présent)
	 *
	 * @param x valeur à ajouter
	 * @pre 0 <= x <= 255
	 */
	public void add(int x) {
		if(!contains(x)) {
			if(x >= 0 && x < SET_SIZE) {
				tab[x] = true;
			}
			else {
				System.out.println("x n'est pas compris entre 0 et 255");
			}
		}
		else {
			System.out.println("x appartient déjà à l'ensemble");
		}
	}

	/**
	 * Retire x de l’ensemble (sans effet si x n’est pas présent)
	 *
	 * @param x valeur à supprimer
	 * @pre 0 <= x <= 255
	 */
	public void remove(int x) {
		if(contains(x)) {
			if(x >= 0 && x < SET_SIZE) {
				tab[x] = false;
			}
			else {
				System.out.println("x n'est pas compris entre 0 et 255");
			}
		}
		else {
			System.out.println("x n'appartient pas à l'ensemble");
		}
	}

	/**
	 * Ajoute à l’ensemble les valeurs deb, deb+1, deb+2, ..., fin.
	 *
	 * @param begin début de l’intervalle
	 * @param end   fin de l’intervalle
	 * @pre 0 <= begin <= end <= 255
	 */
	public void addInterval(int deb, int fin) {
		for(int i = deb; i <= fin; i++) {
			add(i);
		}
	}

	/**
	 * Retire de l’ensemble les valeurs deb, deb+1, deb+2, ..., fin.
	 *
	 * @param begin début de l’intervalle
	 * @param end   fin de l’intervalle
	 * @pre 0 <= begin <= end <= 255
	 */
	public void removeInterval(int deb, int fin) {
		for(int i = deb; i <= fin; i++) {
			remove(i);
		}
	}

	/**
	 * this devient l'union de this et set2
	 * 
	 * @param set2 deuxième ensemble
	 */
	public void union(SmallSet set2) {
		for(int i = 0; i < SET_SIZE; i++) {
			if(!tab[i] && set2.tab[i]) add(i);
		}
	}

	/**
	 * this devient l'intersection de this et set2
	 * 
	 * @param set2 deuxième ensemble
	 */
	public void intersection(SmallSet set2) {
		for(int i = 0; i < SET_SIZE; i++) {
			if(tab[i] && !set2.tab[i]) {
				remove(i);
			}
		}
	}

	/**
	 * this devient la différence de this et set2
	 * 
	 * @param set2 deuxième ensemble
	 */
	public void difference(SmallSet set2) {
		for(int i = 0; i < SET_SIZE; i++) {
			if(tab[i] && set2.tab[i]) {
				remove(i);
			}
		}
	}

	/**
	 * this devient la différence symétrique de this et set2
	 * 
	 * @param set2 deuxième ensemble
	 */
	public void symmetricDifference(SmallSet set2) {
		SmallSet set2copy = new SmallSet(set2.tab);
		difference(set2); // this devient la différence de this et set2
		set2copy.difference(this); // set2copy devient la différence de set2 et this
		union(set2copy); // union des this et set2copy
	}

	/**
	 * this devient le complément de this.
	 */
	public void complement() {
		for(int i = 0; i < SET_SIZE; i++) {
			tab[i] = !tab[i];
		}
	}

	/**
	 * this devient l'ensemble vide.
	 */
	public void clear() {
		for(int i = 0; i < SET_SIZE; i++) {
			tab[i] = false;
		}
	}

	/**
	 * @param set2 second ensemble
	 * @return true, si this est inclus dans set2, false sinon
	 */
	public boolean isIncludedIn(SmallSet set2) {
		int i = 0;
		while(i < SET_SIZE) {
			if(tab[i] && !set2.tab[i]) {
				return false;
			}
			i++;
		}
		return true;
	}

	@Override
	public int hashCode() {
		// pour respecter les bonnes pratiques
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(tab);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		else {
			return isIncludedIn((SmallSet) obj) && ((SmallSet) obj).isIncludedIn(this);
		}
	}

	/**
	 * @return copie de this
	 */
	public SmallSet copyOf() {
		return new SmallSet(tab);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("elements presents : ");
		for (int i = 0; i < SET_SIZE; ++i) {
			if (tab[i]) {
				result.append(i + " ");
			}
		}
		return result.toString();
	}
}