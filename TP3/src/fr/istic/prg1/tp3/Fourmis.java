package fr.istic.prg1.tp3;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2022-09-23
 * 
 */

public class Fourmis {

	/**
	 * @param s un terme de la suite des fourmis
	 * @pre s.length() > 0
	 * @return le terme suivant de la suite des fourmis
	 */
	public static String next(String s) {
		if (s.length() <= 0) {
			System.out.println("La chaîne est vide");
			return null;
		}
		char[] charArray = s.toCharArray();
		char chiffre = charArray[0];
		Integer nb = 1;
		StringBuilder resultat = new StringBuilder("");
		for (int i = 1; i < s.length(); i++) { // On boucle sur tous les caractères sauf le 1er
			if (chiffre == charArray[i]) { //Si on tombe sur le même chiffre que le précédent
				nb++;
			}
			else {
				resultat.append(nb.toString() + chiffre); //On rajoute au résultat le nombre d'occurences et le dernier chiffre 
				chiffre = charArray[i];
				nb = 1;
			}
		}
		resultat.append(nb.toString() + chiffre);
		return resultat.toString();
	}
}