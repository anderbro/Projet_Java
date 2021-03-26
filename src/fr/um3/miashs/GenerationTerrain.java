package fr.um3.miashs;

public class GenerationTerrain {
	
	// Définir le nombre de case
	// Mettre les petits élements en premier
	// Mettre les elements du plus petit au plus grand en terme de nombre: potion: 1, piege:1, joueur: n, mur: n, ....
	// être sûr que le Joueur peut accéder à la victoire
	
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
		Tui.setType(TypeTuile.Potion);
		
		
		G.setHorizontale(y);
		G.setVerticale(x);
		
		x = (int)(Math.random() * ((G.getHorizontale() - 0) + 1));
		y = (int)(Math.random() * ((G.getVerticale() - 0) + 1));	
		Tui.setX(x);
		Tui.setY(y);
		
		//Trouver moyen de l'afficher
	}

}
