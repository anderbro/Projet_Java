package fr.um3.miashs;




public class Joueur extends Plateau {
	
	private int x;
	private int y;
	private int hp=5;
	private String nom;
	private boolean outside =  false;
	private Mouvement mouvement;
	public boolean isDead() { return this.hp <= 0; }
	public boolean estDehors() {return this.outside;}
	
	public Joueur () {
	
		
	}
	
	
	public Joueur (int x, int y, int hp , String nom) {
		super ();
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
	
	
	
	public void potion() {
		if(hp<5) {
			this.hp = hp+1;
			
		}
	}
	

	public void setOutside() {
		this.outside= true;
		
	}
	
	public void piege() {
		
		if (hp>0) {
			this.hp= hp-1;
		}
	}
	
	public void conflit() {
		if (hp>0) {
			this.hp-=1;
		}
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
	
	
	/*public String toString () {
		return getX()+" "+getY()+" "+getHp()+" "+getNom()+" "   ;
	}*/
	
	
}
