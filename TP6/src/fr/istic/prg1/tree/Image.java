package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree.util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2023-09-23
 * 
 *        Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);
	private static final int TAILLE_IMG = 255;
	private static final int ZOOM_OUT_LIMIT = 14; // taille maximum de l'arbre pour zoom out
												  // (2^8 = 256, 8*2 = 16, sachant que l'on zoome 2 fois avant la méthode)

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * this devient identique à image2.
	 *
	 * @param image2 image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		if(!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg = image2.iterator();
			it.clear();
			affectRec(it, itImg);
		}
	}
	
	/**
	 * @param it itérateur sur this
	 * @param itImg itérateur sur image2
	 */
	private void affectRec(Iterator<Node> it, Iterator<Node> itImg) {
		int state = itImg.getValue().state;
		it.addValue(Node.valueOf(state));
		
		if(state == 2) { // state vaut 2 si le noeud est double, sinon c'est une feuille
			it.goLeft();
			itImg.goLeft();
			affectRec(it, itImg);
			
			it.goUp();
			itImg.goUp();
			
			it.goRight();
			itImg.goRight();
			affectRec(it, itImg);
			
			it.goUp();
			itImg.goUp();
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2 image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		if(!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg = image2.iterator();
			it.clear();
			rotate180Rec(it, itImg);
		}
	}
	
	/**
	 * @param it itérateur sur this
	 * @param itImg itérateur sur image2
	 */
	private void rotate180Rec(Iterator<Node> it, Iterator<Node> itImg) {
		int state = itImg.getValue().state;
		it.addValue(Node.valueOf(state));
		
		if(state == 2) { // state vaut 2 si le noeud est double, sinon c'est une feuille
			it.goLeft();
			itImg.goRight();
			rotate180Rec(it, itImg);
			
			it.goUp();
			itImg.goUp();
			
			it.goRight();
			itImg.goLeft();
			rotate180Rec(it, itImg);
			
			it.goUp();
			itImg.goUp();
		}
	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		Iterator<Node> it = iterator();
		
		videoInverseRec(it);
	}
	
	public void videoInverseRec(Iterator<Node> it) {
		int state = it.getValue().state;
		
		if(state != 2) {
			if(state == 0) it.setValue(Node.valueOf(1));
			else it.setValue(Node.valueOf(0));
		}
		else {
			it.goLeft();
			videoInverseRec(it);
			it.goUp();
			it.goRight();
			videoInverseRec(it);
			it.goUp();
		}
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		if(!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg = image2.iterator();
			it.clear();
			mirrorVRec(it, itImg, true);
		}
	}
	
	/**
	 * @param it itérateur sur this
	 * @param itImg itérateur sur image2
	 * @param isVertical l'itérateur est sur une découpe verticale
	 */
	private void mirrorVRec(Iterator<Node> it, Iterator<Node> itImg, boolean isVertical) {
		int state = itImg.getValue().state;
		it.addValue(Node.valueOf(state));
		
		if(state == 2) { // state vaut 2 si le noeud est double, sinon c'est une feuille	
			it.goLeft();
			if(isVertical) {
				itImg.goRight();
			}
			else {
				itImg.goLeft();
			}
			mirrorVRec(it, itImg, !isVertical);

			it.goUp();
			itImg.goUp();
			
			it.goRight();
			if(isVertical) {
				itImg.goLeft();
			}
			else {
				itImg.goRight();
			}
			mirrorVRec(it, itImg, !isVertical);
			
			it.goUp();
			itImg.goUp();
		}
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		if(!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg = image2.iterator();
			it.clear();
			mirrorHRec(it, itImg, false);
		}
	}
	
	/**
	 * @param it itérateur sur this
	 * @param itImg itérateur sur image2
	 * @param isHorizontal l'itérateur est sur une découpe horizontale
	 */
	private void mirrorHRec(Iterator<Node> it, Iterator<Node> itImg, boolean isHorizontal) {
		int state = itImg.getValue().state;
		it.addValue(Node.valueOf(state));
		
		if(state == 2) { // state vaut 2 si le noeud est double, sinon c'est une feuille	
			it.goLeft();
			if(isHorizontal) {
				itImg.goRight();
			}
			else {
				itImg.goLeft();
			}
			mirrorHRec(it, itImg, !isHorizontal);

			it.goUp();
			itImg.goUp();
			
			it.goRight();
			if(isHorizontal) {
				itImg.goLeft();
			}
			else {
				itImg.goRight();
			}
			mirrorHRec(it, itImg, !isHorizontal);
			
			it.goUp();
			itImg.goUp();
		}
	}

	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2 image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		if(!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg = image2.iterator();
			it.clear();
			for(int i = 0; i < 2; i++) {
				if(itImg.getValue().state != 2) {
					it.addValue(Node.valueOf(itImg.getValue().state));
					break;
				}
				itImg.goLeft();
			}
			if(!itImg.isEmpty() && it.isEmpty()) affectRec(it, itImg);
		}
	}

	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this devient
	 * éteint.
	 * 
	 * @param image2 image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		if(!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg = image2.iterator();
			it.clear();
			if(itImg.getValue().state == 0) {
				it.addValue(Node.valueOf(0));
			}
			else {
				for(int i = 0; i < 2; i++) {
					it.addValue(Node.valueOf(2));
					it.goRight();
					it.addValue(Node.valueOf(0));
					it.goUp();
					it.goLeft();
				}
				zoomOutRec(it, itImg, 0);
				
				it.goRoot();
				it.goLeft();
				it.goLeft();
				int gauche = it.getValue().state;
				it.goUp();
				it.goRight();
				int droite = it.getValue().state;
				it.goUp();
				if(gauche == droite && gauche != 2) {
					it.clear();
					it.addValue(Node.valueOf(gauche));
				}
				if(it.getValue().state == 0) {
					it.goUp();
					it.clear();
					it.addValue(Node.valueOf(0));
				}
			}
		}
	}
	
	/**
	 * @param it itérateur sur this
	 * @param itImg itérateur sur image2
	 */
	private void zoomOutRec(Iterator<Node> it, Iterator<Node> itImg, int limit) {
		int gauche = -1;
		int droite = -1;
		if(limit < ZOOM_OUT_LIMIT) {
			int state = itImg.getValue().state;
			it.addValue(Node.valueOf(state));
			
			if(state == 2) { // state vaut 2 si le noeud est double, sinon c'est une feuille
				++limit;
				it.goLeft();
				itImg.goLeft();
				zoomOutRec(it, itImg, limit);
				gauche = it.getValue().state;
				
				it.goUp();
				itImg.goUp();
				
				it.goRight();
				itImg.goRight();
				zoomOutRec(it, itImg, limit);
				if(!it.isEmpty()) droite = it.getValue().state;
				
				it.goUp();
				itImg.goUp();

				if(gauche == droite && gauche != 2 && gauche != -1) {
					it.clear();
					it.addValue(Node.valueOf(gauche));
				}
			}
		}
		else {
			int count = countPixelOn(itImg);
			if(count >= 2) it.addValue(Node.valueOf(1));
			else it.addValue(Node.valueOf(0));
		}
	}
	
	/**
	 * Compte le nombre de pixels allumés dans la branche qui doit être simplifiée
	 * 
	 * @param it itérateur sur image2
	 * @return nombre de pixels allumés dans la branche qui doit être simplifiée
	 */
	private int countPixelOn(Iterator<Node> it) {
		if(it.getValue().state == 1) {
			return 4;
		}
		else if(it.getValue().state == 0) {
			return 0;
		}
		int count = 0;
		it.goLeft();
		if(it.getValue().state == 1) count+=2;
		else if(it.getValue().state == 2) {
			it.goLeft();
			if(it.getValue().state == 1) count++;
			it.goUp();
			it.goRight();
			if(it.getValue().state == 1) count++;
			it.goUp();
		}
		it.goUp();
		it.goRight();
		if(it.getValue().state == 1) count+=2;
		else if(it.getValue().state == 2) {
			it.goLeft();
			if(it.getValue().state == 1) count++;
			it.goUp();
			it.goRight();
			if(it.getValue().state == 1) count++;
			it.goUp();
		}
		it.goUp();
		return count;
	}

	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		if(!image1.isEmpty() && !image2.isEmpty() && this != image1 && this != image2) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg1 = image1.iterator();
			Iterator<Node> itImg2 = image2.iterator();
			it.clear();
			intersectionRec(it, itImg1, itImg2);
		}
	}

	/**
	 * @param it itérateur sur this
	 * @param itImg1 itérateur sur image1
	 * @param itImg2 itérateur sur image2
	 */
	private void intersectionRec(Iterator<Node> it, Iterator<Node> itImg1, Iterator<Node> itImg2) {
		int state1 = itImg1.getValue().state;
		int state2 = itImg2.getValue().state;
		
		if(state1 == 0 || state2 == 0) {
			it.addValue(Node.valueOf(0));
		}
		else if(state1 == 1 && state2 == 1) {
			it.addValue(Node.valueOf(1));
		}
		else if(state1 == 1) {
			affectRec(it, itImg2);
		}
		else if(state2 == 1) {
			affectRec(it, itImg1);
		}
		else if(state1 == 2 && state2 == 2) {
			it.addValue(Node.valueOf(2));
			
			it.goLeft();
			itImg1.goLeft();
			itImg2.goLeft();
			intersectionRec(it, itImg1, itImg2);
			int gauche = it.getValue().state;
			
			it.goUp();
			itImg1.goUp();
			itImg2.goUp();
			
			it.goRight();
			itImg1.goRight();
			itImg2.goRight();
			intersectionRec(it, itImg1, itImg2);
			int droite = it.getValue().state;
			
			it.goUp();
			itImg1.goUp();
			itImg2.goUp();
			
			if(gauche == droite && gauche != 2) {
				it.clear();
				it.addValue(Node.valueOf(gauche));
			}
		}
	}

	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		if(!image1.isEmpty() && !image2.isEmpty() && this != image1 && this != image2) {
			Iterator<Node> it = this.iterator();
			Iterator<Node> itImg1 = image1.iterator();
			Iterator<Node> itImg2 = image2.iterator();
			it.clear();
			unionRec(it, itImg1, itImg2);
		}
	}
	
	/**
	 * @param it itérateur sur this
	 * @param itImg1 itérateur sur image1
	 * @param itImg2 itérateur sur image2
	 */
	private void unionRec(Iterator<Node> it, Iterator<Node> itImg1, Iterator<Node> itImg2) {
		int state1 = itImg1.getValue().state;
		int state2 = itImg2.getValue().state;
		int gauche = -1;
		int droite = -1;
		
		if(state1 == 0 && state2 == 0) {
			it.addValue(Node.valueOf(0));
		}
		else if(state1 == 0) {
			affectRec(it, itImg2);
		}
		else if(state2 == 0) {
			affectRec(it, itImg1);
		}
		else if(state1 == 1 || state2 == 1) {
			it.addValue(Node.valueOf(1));
		}
		
		else {
			it.addValue(Node.valueOf(2));
			
			it.goLeft();
			itImg1.goLeft();
			itImg2.goLeft();
			unionRec(it, itImg1, itImg2);
			gauche = it.getValue().state;
			
			it.goUp();
			itImg1.goUp();
			itImg2.goUp();
			
			it.goRight();
			itImg1.goRight();
			itImg2.goRight();
			unionRec(it, itImg1, itImg2);
			if(!it.isEmpty()) droite = it.getValue().state;
			
			it.goUp();
			itImg1.goUp();
			itImg2.goUp();
			if(gauche == droite && gauche != 2 && gauche != -1) {
				it.clear();
				it.addValue(Node.valueOf(gauche));
			}
		}
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		Iterator<Node> it = iterator();
		
		return testDiagonalRec(it);
	}
	
	public boolean testDiagonalRec(Iterator<Node> it) {
		boolean res = false;
		boolean gauche = false;
		boolean droite = false;
		
		if(it.getValue().state == 1) {
			res = true;
		}
		else if(it.getValue().state == 2) {
			it.goLeft();
			if(it.getValue().state == 2) { // pour éviter les nullpo
				it.goLeft();
				gauche = testDiagonalRec(it);
				it.goUp();
			}
			else {
				gauche = it.getValue().state == 1;
			}
			
			it.goUp();
			
			it.goRight();
			if(it.getValue().state == 2) {
				it.goRight();
				droite = testDiagonalRec(it);
				it.goUp();
			}
			else {
				droite = it.getValue().state == 1;
			}
			
			it.goUp();
			
			res = gauche && droite;
		}
		
		return res;
	}

	/**
	 * @param x abscisse du point
	 * @param y ordonnée du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		if(this.isEmpty()) {
			return false;
		}
		Iterator<Node> it = this.iterator();
		return isPixelOnRec(it, x, y, 0, TAILLE_IMG, 0, TAILLE_IMG);
	}

	/**
	 * @param it itérateur
	 * @param x abscisse du point
	 * @param y ordonnée du point
	 * @param xmin abscisse minimum du point
	 * @param ymin ordonnée minimum du point
	 * @param xmax abscisse maximum du point
	 * @param ymax ordonnée maximum du point
	 * 
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	public boolean isPixelOnRec(Iterator<Node> it, int x, int y, int xmin, int xmax, int ymin, int ymax) {
		if(it.getValue().state != 2) {
			return it.getValue().state == 1;
		}
		
		if(y <= (ymin+ymax)/2) {
			ymax = (ymin+ymax)/2;
			it.goLeft();
		}
		else {
			ymin = (ymin+ymax)/2+1;
			it.goRight();
		}
		
		if(it.getValue().state != 2) {
			return it.getValue().state == 1;
		}
		
		if(x <= (xmin+xmax)/2) {
			xmax = (xmin+xmax)/2;
			it.goLeft();
		}
		else {
			xmin = (xmin+xmax)/2+1;
			it.goRight();
		}
		
		return isPixelOnRec(it, x, y, xmin, xmax, ymin, ymax);
	}

	/**
	 * @param x1 abscisse du premier point
	 * @param y1 ordonnée du premier point
	 * @param x2 abscisse du deuxième point
	 * @param y2 ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par la
	 *         même feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		if(this.isEmpty()) {
			return false;
		}
		int xmin = 0;
		int ymin = 0;
		int xmax = TAILLE_IMG;
		int ymax = TAILLE_IMG;
		Iterator<Node> it = this.iterator();
		
		while(it.getValue().state == 2) {
			if(y1 <= (ymin+ymax)/2) {
				ymax = (ymin+ymax)/2;
				it.goLeft();
			}
			else {
				ymin = (ymin+ymax)/2+1;
				it.goRight();
			}
			
			if(y2 < ymin || y2 > ymax) {
				return false;
			}
			else if(it.getValue().state != 2) {
				break;
			}
			
			if(x1 <= (xmin+xmax)/2) {
				xmax = (xmin+xmax)/2;
				it.goLeft();
			}
			else {
				xmin = (xmin+xmax)/2+1;
				it.goRight();
			}
			
			if(x2 < xmin || x2 > xmax) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @param image2 autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		if(this.isEmpty() || image2.isEmpty()) {
			return false;
		}
		Iterator<Node> it = iterator();
		Iterator<Node> it2 = image2.iterator();
		return isIncludedInRec(it, it2);
	}
	
	/**
	 * @param it itérateur sur this
	 * @param it2 itérateur sur image2
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
	 */
	public boolean isIncludedInRec(Iterator<Node> it, Iterator<Node> it2) {
		if(it.getValue().state == 0 || it2.getValue().state == 1) {
			return true;
		}
		if((it.getValue().state != 0 && it2.getValue().state == 0) ||
				(it.getValue().state == 1 && it2.getValue().state != 1)) {
			return false;
		}
		
		it.goLeft();
		it2.goLeft();
		
		boolean bool = isIncludedInRec(it, it2);
		
		it.goUp();
		it2.goUp();
		
		if(!bool) return bool;
		
		it.goRight();
		it2.goRight();
		
		bool = isIncludedInRec(it, it2);
		
		it.goUp();
		it2.goUp();
		
		return bool;
	}
}