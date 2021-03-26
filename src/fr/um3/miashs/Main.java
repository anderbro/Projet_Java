package fr.um3.miashs;

import java.util.*;

public class Main {


		public static void main(String[] args) {
			Plateau game = new Plateau ()  ;
			game.remplissageGrille("C:\\Users\\BATOU\\git\\groupe2\\src\\fr\\um3\\miashs\\resources\\carte1.txt");
			System.out.println(game);
			
			Engine gamemaster =  new Engine(game);
			Joueur player1 = new Joueur();
			Joueur player2 = new Joueur();
			Scanner sc1 =  new Scanner(System.in);
			
			
			
			///tant que saisi des joueurs 
			

			
			
			
			System.out.println("entrez votre nom");
			String nom = sc1.nextLine();

			System.out.println("bienvenue " + nom + " vous avez actuellement 5 hp"+" .");

			gamemaster.addPlayer(player1);
			gamemaster.addPlayer(player2);
			gamemaster.addPotion();
			gamemaster.addVictoire();
			System.out.println(game);
			System.out.println(game.toString(player1.getHistorique()));
			
			
			
			
			while (player1.estDehors() != true) {
				
			
				System.out.println(
						"entrez haut pour monter, gauche pour aller a gauche, bas pour descendre et droite pour aller a droite ");
				
				String direction = sc1.nextLine();
				
				
				if (direction.compareTo("haut") == 0) {
					gamemaster.move(player1, Mouvement.haut);
					
					System.out.println(game.toString(player1.getHistorique()));
					//System.out.println(game);
					
				}
				else if (direction.compareTo("gauche") == 0) {
					gamemaster.move(player1, Mouvement.gauche);
					//System.out.println(game);
					System.out.println(game.toString(player1.getHistorique()));
				}
				else if (direction.compareTo("bas") == 0) {
					gamemaster.move(player1, Mouvement.bas);
					//System.out.println(game);
					System.out.println(game.toString(player1.getHistorique()));
				}
				else if (direction.compareTo("droite") == 0)
					gamemaster.move(player1, Mouvement.droite);
					//System.out.println(game);
					System.out.println(game.toString(player1.getHistorique()));
				}
				
				
		}}	
		

			 

		
			 
			
			
		

	
