package fr.um3.miashs;

public class FogOfWar extends Joueur1 {
	
	
	private int horizontale;
	private int verticale;
	private char [][] plateau;
	
	//construction du plateau
	public FogOfWar(int a, int b) {
		horizontale = a;
		verticale = b;
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
		
		
		//Comparaison Terrain
	public void Comparaison(Joueur1 J1, Tuile Tui, Plateau P) {
		
		for (int i = 0; i <=3; i++) {  // i = nombre de déplacement du joueur (4)
			// J1 fait son déplacement avec fonction
			
			
			
			if (J1.getX() == Tui.getX() & J1.getY() == Tui.getY()) {
				// J1 interagit avec la tuile
				
				
				
				//Potion
				if (Tui.getType() ==  TypeTuile.Potion) {
					int pv = J1.getHp() + 1;
					J1.setHp(pv);
					System.out.println("Chouette une fiole bizarre trouvée par terre. Buvons là. Joueur + 1hp");
					// + déplacement
				}
				
				
				
				//Piège
				if (Tui.getType() == TypeTuile.Piege) {
					int pv = J1.getHp() - 1;
					J1.setHp(pv);
					System.out.println("Aie coup dur pour Jérôme. Joueur - 1hp");
					// + déplacement
				
				}
				
				
				
				//Sortie = Victoire
				if (Tui.getType() == TypeTuile.Sortie) {
					System.out.println("Vous avez gagné avant tout le monde. Point faible, trop fort.");
					// Fin stopper le jeu
				
				}
				
				if (Tui.getType() == TypeTuile.Mur) {
					System.out.println("Le mur est plus résistant que vous, ne passez pas en force.");
					//Garder la position du joueur;
				}else {
					System.out.println("Vous avancez d'un case");
					// + déplacement
				}
			}
		}

		
		
		
		
		
	}
		
	}
	

