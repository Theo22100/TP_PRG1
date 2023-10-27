package fr.istic.prg1.list;

import java.util.ArrayList;

import fr.istic.prg1.list_util.Iterator;
import fr.istic.prg1.list_util.SuperT;

public class List<T extends SuperT> {
	// liste en double chainage par references

	private class Element {
		// element de List<Item> : (Item, Element, Element)
		public T value;
		public Element left;
		public Element right;

		public Element() {
			value = null;
			left = null;
			right = null;
		}
	} // class Element

	public class ListIterator implements Iterator<T> {
		private Element current;

		private ListIterator() {
			current = flag.right;
		}

		@Override
		public void goForward() {
			try {
				assert current.right != null : "Impossible d'avancer, il n'y a pas de voisin droit\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			} 
			current = current.right;
		}

		@Override
		public void goBackward() {
			try {
				assert current.left != null : "Impossible d'avancer, il n'y a pas de voisin gauche\\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			}
			current = current.left;
		}

		@Override
		public void restart() {
			current = flag.right;
		}

		@Override
	    public boolean isOnFlag() { 
			return current == flag;
		}

		@Override
		public void remove() {
			try {
				assert current != flag : "Impossible d'enlever le flag\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			}
			Element temp = current;
			Element leftNeighbor = current.left;
			Element rightNeighbor = current.right;
			leftNeighbor.right = rightNeighbor;
			rightNeighbor.left = leftNeighbor;
			current = current.right;
			temp.left = null;
			temp.right = null;
		}

		@Override		 
		public T getValue() {
			try {
				assert this.current.value != null : "L'élément courant n'a pas de valeur\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			}
			return current.value;
		}

		@Override
	    public T nextValue() {
			goForward();
			return getValue();
		}

		@Override
		public void addLeft(T v) {
			Element leftNeighbor = current.left;
			Element rightNeighbor = current;
			current = new Element();
			current.value = v;
			current.left = leftNeighbor;
			current.right = rightNeighbor;
			leftNeighbor.right = current;
			rightNeighbor.left = current;
		}

		@Override
		public void addRight(T v) {
			goForward();
			addLeft(v);
		}

		@Override
		public void setValue(T v) {
			current.value = v;
		}

		@Override
		public void selfDestroy() {
			current = null;
			itList.remove(this);
		}

		@Override
		public String toString() {
			return "parcours de liste : pas d'affichage possible\n";
		}

	} // class IterateurListe

	private Element flag;

	private ArrayList<ListIterator> itList = new ArrayList<>();
    
	public List() {
		flag = new Element();
		flag.left = flag;
		flag.right = flag;
	}

	public ListIterator iterator() {
		ListIterator it = new ListIterator();
		itList.add(it);
		return it;
	}

	public boolean isEmpty() {
		return flag == flag.right;
	}

	public void clear() {
		flag.left = flag;
		flag.right = flag;
		for (ListIterator it : itList) {
			it.current = flag;
		}
	}

	public void setFlag(T v) {
		flag.value = v;
	}

	public void addHead(T v) {
		ListIterator it = iterator();
		it.addLeft(v);
		it.selfDestroy();
	}

	public void addTail(T v) {
		ListIterator it = iterator();
		it.goBackward();
		it.addLeft(v);
		it.selfDestroy();
	}

	@SuppressWarnings("unchecked")
	public List<T> clone() {
		List<T> nouvListe = new List<>();
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			nouvListe.addTail((T) p.getValue().copyOf());
			// UNE COPIE EST NECESSAIRE !!!
			p.goForward();
		}
		p.selfDestroy();
		return nouvListe;
	}

	@Override
	public String toString() {
		String s = "contenu de la liste : \n";
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			s = s + p.getValue().toString() + " ";
			p.goForward();
		}
		p.selfDestroy();
		return s;
	}
}
