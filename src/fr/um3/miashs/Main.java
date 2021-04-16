package fr.um3.miashs;

import java.util.*;

public class Main {
	//ancien main maintenant inutile

	public static void main(String[] args) {
		// permet de lire le fichier
		Plateau game = new Plateau();
		game.remplissageGrille("src\\fr\\um3\\miashs\\resources\\carte1.txt");
	
		System.out.println(game);

		Engine gamemaster = new Engine(game);
		gamemaster.addPotion();
		gamemaster.addVictoire();
		//Joueur player1 = new Joueur();
		//Joueur player2 = new Joueur();
		Scanner sc1 = new Scanner(System.in);

		/// tant que saisi des joueurs

		System.out.println("entrez votre nom");
		String nom = sc1.nextLine();

		System.out.println("bienvenue " + nom + " vous avez actuellement 5 hp" + " .");

		//gamemaster.addPlayer(player1);

		
		System.out.println(game);
		///System.out.println(game.toString(player1.getHistorique()));

		
		int i =0 ;
		
		/*
		
		// tant que le joueur n'a pas gagné ca continue de tourner
		while (player1.estDehors() != true ) {
			
			if(i==4) {
				i=0;
			}
			
			System.out.println("veuillez entrer 4 directions, entrez z pour monter, q pour aller a gauche, s pour descendre et d pour aller a droite ");
			
			while(i<4) {
			String direction = sc1.nextLine();
			System.out.println("direction num " + (i+1));
			
			
			if (direction.compareTo("z") == 0) {
				gamemaster.move(player1, Mouvement.haut);
				i+=1;
				//System.out.println(game.toString(player1.getHistorique()));
				// System.out.println(game);

			} else if (direction.compareTo("q") == 0) {
				gamemaster.move(player1, Mouvement.gauche);
				// System.out.println(game);
				i+=1;
				//System.out.println(game.toString(player1.getHistorique()));

			} else if (direction.compareTo("s") == 0) {
				gamemaster.move(player1, Mouvement.bas);
				i+=1;
				// System.out.println(game);

				//System.out.println(game.toString(player1.getHistorique()));
			} else if (direction.compareTo("d") == 0) {
				i+=1;
				gamemaster.move(player1, Mouvement.droite);
				// System.out.println(game);
				//System.out.println(game.toString(player1.getHistorique()));
			} else {
				System.out.println("entrez une vraie direction");
			}
			
		}
		System.out.println(game.toString(player1.getHistorique()));	
		}

	}
*/
}

}
			 
			
			
		

	
