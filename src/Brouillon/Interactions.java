package Brouillon;

public class Interactions extends Joueur{

	
	public Interactions(int x, int y, int hp, String nom) {
		super(x, y, hp, nom);
	
	}



	public void RandomPosition (int a, int b) { //void à changer
		int z =(int)(Math.random() * a);
		int v =(int)(Math.random() * b);
		}
	
	
	
	public void Potion(int z, int v, int positionJ, Joueur j1) { //void à changer
		if(positionJ == z & positionJ == v) {
			if(j1.getHp() < j1.getHpMax()) {
				j1.setHp(j1.getHp() + 1);
			}
			RandomPosition(z,v);
		}
		
		
		
	}
}
