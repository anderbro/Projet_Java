package groupe2;

public class main {

	public static void main(String[] args) {
		// on peut modif le nombre de cases comme on veut, c'est les parametres a,b
		Plateau p1 = new Plateau(10,10);
		
		p1.placer(9, 8, 'J');
		
		p1.placer(4, 8, '~');
		p1.placer(4, 6, '~');
	
		
		
		p1.deplacerH();
		p1.deplacerG();
		p1.deplacerB();
		p1.deplacerD();
		
		
		
		
		
		
		
		p1.afficher();
		
	
	
	}

}

