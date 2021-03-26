package fr.um3.miashs;

import java.util.*;

public class Main {


		public static void main(String[] args) {
			Plateau game = new Plateau ()  ;
			game.remplissageGrille("C:\\Users\\BATOU\\git\\groupe2\\src\\fr\\um3\\miashs\\resources\\carte1.txt");
			System.out.println(game);
			
			Engine gamemaster =  new Engine(game);
			Joueur player1 = new Joueur();
			Scanner sc1 =  new Scanner(System.in);
			
			///tant que saisi des joueurs 
			
			 System.out.println("entrez votre nom");
			 String nom = sc1.nextLine();
		
			 System.out.println("bienvenue " + nom + " .");
			 
			gamemaster.addPlayer(player1);
			System.out.println(game);
			
			gamemaster.move(player1,Mouvement.haut);
			System.out.println(game);
			gamemaster.move(player1,Mouvement.haut);
			System.out.println(game);
			gamemaster.move(player1,Mouvement.haut);
			System.out.println(game);
			gamemaster.move(player1,Mouvement.haut);

			System.out.println(game);
			
			FogOfWar map = new FogOfWar(8, 5);
			map.afficher();
		}

	}
