package groupe2;

public class Plateau {

	private int horizontale;
	private int verticale;
	private char [][] plateau;
	
	//construction du plateau
	public Plateau(int n, int p) {
		horizontale = n;
		verticale = p;
		plateau = new char [horizontale][verticale];
		
		for(int i = 0; i<horizontale; i++) {
			for(int j = 0; j<verticale; j++) {
				plateau[i][j] = '?';
			}
		}
	}
	public void afficher() {
		System.out.println();
		for(int i = 0; i<horizontale; i++) {
			for(int j = 0; j<verticale; j++) {
				plateau[i][j] = '?';
				System.out.print(plateau[i][j]);
			}
			System.out.println();
		}
	}
	

}