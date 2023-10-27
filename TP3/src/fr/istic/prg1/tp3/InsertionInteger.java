package fr.istic.prg1.tp3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2022-09-23
 * 
 *        Partie I
 * 
 *        Lecture, au clavier, d'une suite d'entiers terminée par -1
 * 
 *        Cette suite peut comporter des valeurs doubles.
 * 
 *        Construction et affichage d'un tableau contenant les entiers distincts
 *        triés par valeur croissante.
 * 
 *        Exemple. En entrée : 3 8 1 4 3 2 1 3 -1 ==> En sortie : 1 2 3 4 8
 */

public class InsertionInteger {

	private static final int SIZE_MAX = 10;
	private static final int END_MARKER = -1;
	/**
	 * nombre d’entiers présents dans t, 0 <= size <= SIZE_MAX
	 */
	private int size;
	private int[] array = new int[0];

	/**
	 * @return copie de la partie remplie du tableau
	 */
	public int[] toArray() {
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
		int nombre = scanner.nextInt(); // lit le premier int écrit
		while(nombre != END_MARKER) { // s'arrête à la lecture de -1
			insert(nombre);
			nombre = scanner.nextInt();
		}
	}

	/**
	 * Si value n'appartient pas à array[0..size-1] et size < SIZE_MAX, size est
	 * incrémenté de 1, value est inséré dans array et les entiers array[0..size]
	 * sont triés par ordre croissant. Sinon array est inchangé.
	 * 
	 * Exemple :
	 * 
	 * pour x = 5, size = 3, array[0] = 1, array[1] = 6, array[2] = 8
	 * 
	 * insertion délivre true, size = 4,
	 * 
	 * array[0] = 1, array[1] = 5, array[2] = 6, array[3] = 8
	 * 
	 * @param value valeur à insérer
	 * 
	 * @pre les valeurs de array[0..size-1] sont triées par ordre croissant
	 * 
	 * @return false si value appartient à array[0..size-1] ou si array est
	 *         complètement rempli; true si value n’appartient pas à
	 *         array[0..size-1]
	 */
	public boolean insert(int value) {
		if (!contains(value) && size < SIZE_MAX) {
			size++;
			array = Arrays.copyOf(array, size); // crée une copie d'array avec une taille supérieure
			array[size-1] = value;
			tri(array);
			return true;
		}
		return false;
	}

	/**
	 * @param value valeur à chercher
	 * 
	 * @return false si la value n'est pas contenue dans array, et true si inversement
	 */
	public boolean contains(int value) {
	    for (int i : array) {
	        if (i == value) {
	            return true;
	        }
	    }
	    return false;
	}


	/**
	 * Trie un tableau d'entier par valeur croissante
	 * 
	 * @param tab tableau à trier
	 */
	public static void tri(int[] tab) {
		for (int i=0; i<=tab.length-2; i++) {
			int rangmin=i;
			for(int j = i+1;j<=tab.length-1;j++) {
				if(tab[j]<tab[rangmin]) {
					rangmin=j;
				}
			}
			int aux = tab[i];
			tab[i]=tab[rangmin];
			tab[rangmin]=aux;
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Array:");
		for(Integer i: array) {
			stringBuilder.append(i.toString());
		}
		return stringBuilder.toString();
	}
}
