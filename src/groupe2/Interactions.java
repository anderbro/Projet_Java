package groupe2;

public class Interactions extends Joueur{

	
	public Interactions(int x, int y, int hp, String nom) {
		super(x, y, hp, nom);
	
	}



	public void RandomPosition (int a, int b) { //void � changer
		int x =(int)(Math.random() * a);
		int y =(int)(Math.random() * b);
		return;
	}
	
	
	
	public void Potion(int z, int v, int positionJ,Joueur.getX(), int y) { //void � changer
		if(positionJ == z & positionJ == v) {
			// joueur r�cup�re potion
			;
			RandomPosition(a,b);
		}
		
		
		
	}
}
