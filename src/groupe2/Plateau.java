package groupe2;

import java.util.* ; 

public class Plateau {

	private int horizontale;
	private int verticale;
	private char [][] plateau;
	
	//construction du plateau
	public Plateau(int a, int b) {
		horizontale = a;
		verticale = b;
		plateau = new char [horizontale][verticale];
		
		for(int i = 0; i<horizontale; i++) {
			for(int j = 0; j<verticale; j++) {
				plateau[i][j] = '?';
			}
		}
	}
	
	
	public char getCase(int l, int c) {
		return plateau[l][c];
		
	}
	
	
	
	public void placer (int l, int c, char x) {
		l=l-1;
		c=c-1;		
		
		if(l<0 || c<0 || l>horizontale || c> verticale) {
			System.out.println("erreur");
			return;
		}
		
		 if(plateau[l][c] == '?') {
			 plateau[l][c] = x;
		 }
		
		 else {
			 System.out.println("erreur deja pris");
		 }
		
	}
	
	
	 public void deplacerH () {
		 
		 char tmp;
		 for(int i = 0; i<horizontale; i++) {
				for(int j = 0; j<verticale; j++) {
					if(plateau[i][j] == 'J') {
						
						if(i-1 >= 0 && plateau[i-1][j]==' ') {
							tmp=plateau[i][j];
							plateau[i][j] = ' ';
							plateau[i-1][j]= tmp ;
						}
						
						
					}
				}
			}
		 
	 }
		/* if(plateau[][] = ' ' ) {
			 plateau[][]=t;
		 }
		 
		 */
		 
	 
	 public void deplacerG () {
		 
		 char tmp;
		 for(int i = 0; i<horizontale; i++) {
				for(int j = 0; j<verticale; j++) {
					if(plateau[i][j] == 'J'&& plateau[i][j-1]==' ') {
						if(j-1 >= 0) {
							tmp=plateau[i][j];
							plateau[i][j] = ' ';
							plateau[i][j-1]= tmp ;
						}
						
						
					}
				}
			}
		 
	 }
	
	
	 public void deplacerD () {
		 
		 char tmp;
		 for(int i = 0; i<horizontale; i++) {
				for(int j = 0; j<verticale; j++) {
				
					if(plateau[i][j] == 'J') {
						
						if(j+1 <= verticale && plateau[i][j+1]==' ') {
						
							tmp=plateau[i][j];
							plateau[i][j] = ' ';
							plateau[i][j+1]= tmp ;
							i=9;
							j=9;
							
						}
						
						
					}
				}
			}
		 
	 }
	
	 
	 
	 
 public void deplacerB () {
		 
		 char tmp;
		 for(int i = 0; i<horizontale; i++) {
				for(int j = 0; j<verticale; j++) {
					
					System.out.println("i1 = "+ i);
					if(plateau[i][j] == 'J') {
						System.out.println("i2 = " +i);
						
						if(i+1 <= horizontale) {
							System.out.println( "i3 = " +i);
							tmp=plateau[i][j];
							plateau[i][j] = ' ';
							plateau[i+1][j]= tmp ;
							i=9;
							//break;
						}
						
						
					}
				}
			}
		 
	 }
	 
	
	
	
	public void afficher() {
		System.out.println();
		for(int i = 0; i<horizontale; i++) {
			for(int j = 0; j<verticale; j++) {
				
				System.out.print(plateau[i][j]);
			}
			System.out.println();
		}
	}
	

}