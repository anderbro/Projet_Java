package Brouillon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlateauClear {

	ArrayList<Tuile> grille = new ArrayList<Tuile>() ;
	private int largeur,hauteur;
	
	// rempli la grille � partie d'un fichier

	public void remplissageGrille(String cheminCarte) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(cheminCarte));
		} catch (FileNotFoundException e) {
			System.out.println("erreur " + cheminCarte + " introuvable !");
			return ;
		}

		try {
			String line = reader.readLine();
			if (line!= null) {
				largeur = line.length();
			}
			int numeroLigne = 1;
			while (line != null) {

				for (int i = 0; i < line.length(); i++) {
					char car = line.charAt(i);
					Tuile tuile = new Tuile (car,i+1,numeroLigne);
					grille.add(tuile);
				}
				line = reader.readLine();
				numeroLigne++;
				
			}	
			hauteur = numeroLigne-1 ;
		} catch (IOException e) {
			System.out.println("Erreur de fichier");
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toString() {
		String result = "";
		for (int i = 1; i <= hauteur; i++) {
			final int index = i;
			result += grille.stream().filter(tuile -> tuile.getY() == index)
					.map(tuile -> Character.toString(tuile.toChar())).collect(Collectors.joining())+"\n";
		}
		return result;
	}

}
	
	
	

