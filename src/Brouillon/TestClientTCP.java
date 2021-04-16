package Brouillon;

import java.net.*;
import java.io.*;

public class TestClientTCP {
  final static int port = 2411;

  public static void main(String[] args) {

    Socket socket;
    //DataInputStream userInput;
    //PrintStream theOutputStream;

    try {
      InetAddress serveur = InetAddress.getLocalHost();
      socket = new Socket(serveur, port);

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintStream out = new PrintStream(socket.getOutputStream());

      out.println();
      System.out.println(in.readLine());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}