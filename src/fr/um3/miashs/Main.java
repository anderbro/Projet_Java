package fr.um3.miashs;

import java.util.*;

public class Main {


		public static void main(String[] args) {
			//permet de lire le fichier
			Plateau game = new Plateau ()  ;
			game.remplissageGrille("src\\fr\\um3\\miashs\\resources\\carte1.txt");
			//get.Class().getRessource(carte1.txt);
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
			
			gamemaster.addPotion();
			gamemaster.addVictoire();
			System.out.println(game);
			System.out.println(game.toString(player1.getHistorique()));
			
			
			
			
		
			//tant que le joueur n'a pas gagner ca continue de tourner	
			while (player1.estDehors() != true) {
				
			
				System.out.println(
						"entrez z pour monter, q pour aller a gauche, s pour descendre et d pour aller a droite ");
				
				String direction = sc1.nextLine();
				
				
				if (direction.compareTo("z") == 0) {
					gamemaster.move(player1, Mouvement.haut);
					
					System.out.println(game.toString(player1.getHistorique()));
					//System.out.println(game);
					
				}
				else if (direction.compareTo("q") == 0) {
					gamemaster.move(player1, Mouvement.gauche);
					//System.out.println(game);
					System.out.println(game.toString(player1.getHistorique()));
					
				}
				else if (direction.compareTo("s") == 0) {
					gamemaster.move(player1, Mouvement.bas);
					//System.out.println(game);
					
					System.out.println(game.toString(player1.getHistorique()));
				}
				else if (direction.compareTo("d") == 0) {
					
					gamemaster.move(player1, Mouvement.droite);
					//System.out.println(game);
					System.out.println(game.toString(player1.getHistorique()));
				}
				else {
					System.out.println("entrez une vraie direction");
				}
			
			}
			
			
				
			
				
			}
				
			
		}	
	
		

			 

		
			 
			
			
		

	
