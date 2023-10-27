package fr.istic.prg1.tp3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2022-09-23
 * 
 *        Partie II
 *
 *        Lecture, au clavier, d'une suite de doublets d'entiers terminée par -1
 *
 *        Cette suite peut comporter des doublets identiques.
 * 
 *        Construction et affichage d'un tableau contenant les doublets
 *        distincts triés selon l'ordre croissant des doublets, à savoir : (x1,
 *        y1) < (x2, y2) <==> (x1<x2) ou (x1==x2 et y1<y2)
 * 
 *        En entrée : 3 8 1 4 3 8 1 3 -1 ==> En sortie : [1 3] [1 4] [3 8]
 */

public class InsertionPair {

	private static final int SIZE_MAX = 10;
	private static final int END_MARKER = -1;
	/**
	 * nombre de doublets présents dans t, 0 <= size <= SIZE_MAX
	 */
	private int size;
	private Pair[] array = new Pair[0];

	/**
	 * @return copie de la partie remplie du tableau
	 */
	public Pair[] toArray() {
		return array;
	}

	/**
	 * array est rempli, par ordre croissant, en utilisant la fonction insert, avec
	 * les valeurs lues par scanner.
	 * 
	 * @param scanner
	 */
	public void createArray(Scanner scanner) {
		size = 0;
		int nb1 = scanner.nextInt(); // lit le premier int écrit
		int nb2 = 0;
		if (nb1 != END_MARKER) { // lit le deuxième int si le premier est différent de -1
			nb2 = scanner.nextInt();
		}
		while(nb1 != END_MARKER && nb2 != END_MARKER) { // s'arrête à la lecture d'un -1
			Pair paire = new Pair(nb1, nb2);
			insert(paire);
			nb1 = scanner.nextInt();
			if (nb1 != END_MARKER) {
				nb2 = scanner.nextInt();
			}
		}
	}

	/**
	 * Si pair n'appartient pas à array[0..size-1] et size < SIZE_MAX, size est
	 * incrémenté de 1, pair est inséré dans array et les entiers array[0..size]
	 * sont triés par ordre croissant. Sinon array est inchangé.
	 * 
	 * @param pair doublet à insérer
	 * 
	 * @pre les doublets de array[0..size-1] sont triés par ordre croissant
	 * 
	 * @return false si pair appartient à array[0..size-1] ou si array est
	 *         complètement rempli; true si pair n’appartient pas à array[0..size-1]
	 */
	public boolean insert(Pair pair) {
		if (!contains(pair) && size < SIZE_MAX) {
			size++;
			array = Arrays.copyOf(array, size); // crée une copie d'array avec une taille supérieure
			array[size-1] = pair;
			tri(array);
			return true;
		}
		return false;
	}
	
	/**
	 * @param value paire à chercher
	 * 
	 * @return false si la paire n'est pas contenue dans array, et true si inversement
	 */
	public boolean contains(Pair value) {
	    for (Pair i : array) {
	        if (i.equals(value)) {
	            return true;
	        }
	    }
	    return false;
	}


	/**
	 * Trie un tableau de paires par valeur croissante
	 * 
	 * @param tab tableau à trier
	 */
	public static void tri(Pair[] tab) {
		for (int i=0; i<=tab.length-2; i++) {
			int rangmin=i;
			for(int j = i+1;j<=tab.length-1;j++) {
				if(tab[j].compareTo(tab[rangmin]) == -1) {
					rangmin=j;
				}
			}
			Pair aux = tab[i].copyOf();
			tab[i] = tab[rangmin].copyOf();
			tab[rangmin] = aux.copyOf();
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Array:");
		for(Pair i: array) {
			stringBuilder.append(i.toString());
		}
		return stringBuilder.toString();
	}
}