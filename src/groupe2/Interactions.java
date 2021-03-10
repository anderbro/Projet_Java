package groupe2;

public class Interactions extends Plateau{

	public Interactions(int a, int b) {
		super(a, b);
		
	}
	
	public void RandomPosition (int a, int b) { //void à changer
		int x =(int)(Math.random() * a);
		int y =(int)(Math.random() * b);
		return;
	}
	
	public void Potion(int x, int y, int positionJ,int a, int b) {
		if(positionJ == x & positionJ == y) {
			// joueur récupère potion
			RandomPosition(a,b)
		}
		
		
		
	}
}
