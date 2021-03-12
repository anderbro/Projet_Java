package fr.um3.miashs;

import java.util.*;

public class Main {






		public static void main(String[] args) {
			
			Joueur player1 = new Joueur();
			Scanner sc1 =  new Scanner(System.in);
			 
			
			 System.out.println("entrez votre nom");
			 String nom = sc1.nextLine();
		
			 System.out.println("bienvenue " + nom + " .");
			 
			 
			 
			Plateau game = new Plateau ()  ;
			game.remplissageGrille("C:\\Users\\BATOU\\eclipse-workspace\\Projet_dungeon_quest\\src\\fr\\um3\\miashs\\resources\\carte1.txt");
			System.out.println(game);
		}

	}
