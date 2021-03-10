package groupe2;

public class Interactions extends Joueur{

	
	public Interactions(int x, int y, int hp, String nom) {
		super(x, y, hp, nom);
	
	}



	public void RandomPosition (int a, int b) { //void à changer
		int x =(int)(Math.random() * a);
		int y =(int)(Math.random() * b);
		return;
	}
	
	
	
	public void Potion(int z, int v, int positionJ,Joueur.getX(), int y) { //void à changer
		if(positionJ == z & positionJ == v) {
			// joueur récupère potion
			;
			RandomPosition(a,b);
		}
		
		
		
	}
}
