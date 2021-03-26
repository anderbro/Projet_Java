package fr.um3.miashs;

public class Coordonnees {
	protected int x = 0;
	protected int y = 0;
	
	public Coordonnees(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {  
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}