package groupe2;

public class Joueur   {
	
	private int x;
	private int y;
	private int hp;
	private String nom;
	boolean isDead() { return this.hp <= 0; }
	
	public Joueur (int x, int y, int hp , String nom)  {
		setX(x);
		setY(y);
		setHp(hp);
		setNom(nom);
		
	}
	
	
	public int getX() {
		
		return x ;
	}
	
	public void setX(int x) {
		this.x= x;
	}
	
	public int getY() {
		return y ;
	}
	
	public void setY(int y) {
		this.y= y;
	}
	
	public int getHp() {
		return hp ;
	}
	
	public void setHp(int hp) {
		this.hp= hp;
	}
	
	
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		if (nom != null && nom.length() > 1)
			this.nom = nom;
		else
			System.err.println("[setNom] error : " + nom);
	}
	
	
	public String toString () {
		return getX()+" "+getY()+" "+getHp()+" "+getNom()+" "   ;
	}
	
	
}
