package engine.exception;

public class TrajetNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public TrajetNotFoundException() {
		super("Aucun trajet n'a �t� trouv� pour ce voyage");
	}
}
