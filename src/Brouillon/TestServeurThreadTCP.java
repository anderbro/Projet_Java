package Brouillon;

import java.net.*;
import java.io.*;

public class TestServeurThreadTCP extends Thread {
//créer une instance de la classe ServerSocket en précisant le port en paramètre
  final static int port = 2411;
  private Socket socket;

  public static void main(String[] args) {
    try {
      ServerSocket socketServeur = new ServerSocket(port);
      System.out.println("Lancement du serveur");
	  //5sec avant timeout du serveur s'il y a personne dessus
      //socketServeur.setSoTimeout(5000);
      //définir une boucle sans fin
      while (true) {
    	//méthode accept() qui renvoie une socket lors d'une nouvelle connexion
        Socket socketClient = socketServeur.accept();
        //obtenir un flux en entrée et en sortie à partir de la socket
        TestServeurThreadTCP t = new TestServeurThreadTCP(socketClient);
        t.start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public TestServeurThreadTCP(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    traitements();
  }

  //en gros ça c'est le test pour savoir si l'information est bien envoyée au client  mais y'a que la ligne 48 qui fonctionne
  public void traitements() {
    try {
      String message = "Joueur";

      System.out.println("Connexion avec le client : " + socket.getInetAddress());

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintStream out = new PrintStream(socket.getOutputStream());
      message = in.readLine();
      out.println("Bonjour " + message);

      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}