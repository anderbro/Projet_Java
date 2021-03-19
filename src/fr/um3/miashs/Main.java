package fr.um3.miashs;

import java.util.*;

public class Main {






		public static void main(String[] args) {
			
			Joueur1 player1 = new Joueur1();
			Scanner sc1 =  new Scanner(System.in);
			 
			
			 System.out.println("entrez votre nom");
			 String nom = sc1.nextLine();
		
			 System.out.println("bienvenue " + nom + " .");
			 
			 
			 
			Plateau game = new Plateau ()  ;
			game.remplissageGrille("C:\\Users\\Gorak\\git\\groupe2\\src\\fr\\um3\\miashs\\resources\\carte1.txt");
			System.out.println(game);
			
			FogOfWar map = new FogOfWar(8, 5);
			map.afficher();
		}

	}
