package ChessException;

public class WrongInputException extends Exception {
	char _wrongFile;
	char _wrongRank;
	public WrongInputException(String message, char file, char rank) {
		// TODO Auto-generated constructor stub
		super(message);
		_wrongFile = file;
		_wrongRank = rank;
	}

	public WrongInputException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WrongInputException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WrongInputException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
