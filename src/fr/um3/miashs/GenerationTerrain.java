package fr.um3.miashs;

public class GenerationTerrain {
	
	// Définir le nombre de case
	// Mettre les petits élements en premier
	// Mettre les elements du plus petit au plus grand en terme de nombre: potion: 1, piege:1, joueur: n, mur: n, ....
	// être sûr que le Joueur peut accéder à la victoire
	private char [][] plateau;
	private int horizontale;
	private int verticale;
	
	
	
	public int getVerticale() {
		return verticale;
	}
	public void setVerticale(int verticale) {
		this.verticale = verticale;
	}
	
	
	
	
	
	public int getHorizontale() {
		return horizontale;
	}
	public void setHorizontale(int horizontale) {
		this.horizontale = horizontale;
	}
	
	
	public void Element(Tuile Tui, GenerationTerrain G, int x, int y) {
		Tui.setType(TypeTuile.Mur);
		
		
		G.setHorizontale(y);
		G.setVerticale(x);
		int nb = 0;
		while (nb < (G.getHorizontale() * G.getVerticale())/2){
			x = (int)(Math.random() * ((G.getHorizontale() - 0) + 1));
			y = (int)(Math.random() * ((G.getVerticale() - 0) + 1));
			plateau[x][y] = '#';
			nb =+ 1;			
		}	
	}

}
