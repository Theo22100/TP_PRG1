package main;

import java.util.ArrayList;

public class tp2 {
	
	
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
	public static boolean recherchedicho(int[] tab, int a) {
		int deb=0;
		int fin=tab.length-1;
		int milieu=(deb+fin)/2;
		while(deb<=fin && a!=tab[milieu]) {
			if(a<tab[milieu]) fin=milieu-1;
			if(a>tab[milieu]) deb=milieu+1;
			milieu=(deb+fin)/2;
		}
		
		return deb <=fin;
	}
	
	public static ArrayList<Integer> nbMysterieux() {
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		
		for(int n=1; n<1000; n++) {
			boolean[] tab = {false, false, false, false, false, false, false, false, false, false};
			Integer carre = n*n;
			Integer cube = n*n*n;
			String chiffres = carre.toString() + cube.toString();
			if(chiffres.length() == 10) {
				for(Character c : chiffres.toCharArray()) {
					int chiffre = Integer.parseInt(c.toString());
					if(!tab[chiffre]) {
						tab[chiffre] = true;
					}
					else if (tab[chiffre]) {
						tab[chiffre] = false;
						break;
					}
				}
			}
			int nbTrue = 0;
			for (boolean b : tab) {
				if (b) nbTrue++;
			}
			if (nbTrue==10) {
				resultat.add(n);
			}
		}
		
		return resultat;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] abc = {5,1,3,8,4};
		tri(abc);
		for(int i:abc) {
			System.out.print(i+" ");
		}
		System.out.println();
		int[] vide = {};
		System.out.println(recherchedicho(vide,0));
		System.out.println(recherchedicho(abc,1));
		System.out.println(recherchedicho(abc,3));
		System.out.println(recherchedicho(abc,8));
		System.out.println(recherchedicho(abc,69));
		
		System.out.println("Le résultat est : " + nbMysterieux());
	}

}
