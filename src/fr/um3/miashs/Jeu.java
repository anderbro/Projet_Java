package fr.um3.miashs;

public class Jeu implements Runnable {

	private Engine engine;

	public Jeu(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void run() {
		try {
			// regarde si la partie est finie puis si elle contient un joueur, et la lance enfin si ces conditions sont valides 
			while (!this.engine.partieFinie()) {
				if (this.engine.hasPlayers())
					this.engine.tourPlateau();
				else {
					synchronized (this.engine.running) {
						//permet de marcher ca tourne pas en rond lol + synchronized pour un accés exclusif sur le lock 
						//pour synchro deux thread accedant aux memes données 
						this.engine.running.wait();
					}
				}
			}
			System.out.println("partie finie !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
