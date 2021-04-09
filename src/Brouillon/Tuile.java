package Brouillon;

public class Tuile {

	private int x = 0;
	private int y = 0;
	private TypeTuile type;

	// constructeur de la tuile
	public Tuile(char car, int x, int y) {
		this.x = x;
		this.y = y;

		switch (car) {
		case 'X':
			type = TypeTuile.Joueur;
			break;

		case 'P':
			type = TypeTuile.Potion;
			break;

		case '#':
			type = TypeTuile.Mur;
			break;

		case '~':
			type = TypeTuile.Piege;
			break;

		case 'V':
			type = TypeTuile.Sortie;
			break;

		case ' ':
		default:
			type = TypeTuile.Vide;
			break;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TypeTuile getType() {
		return type;
	}

	public void setType(TypeTuile type) {
		this.type = type;
	}

	public char toChar() {
		switch (this.type) {
		case Joueur:
			return 'X';

		case Potion:
			return 'P';

		case Mur:
			return '#';

		case Piege:
			return '~';

		case Sortie:
			return 'V';

		case Vide:
		default:
			return ' ';

		}

	}

}

