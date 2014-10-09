package ChessException;

public class OutOfBoardException extends Exception {
	
	public OutOfBoardException(String msg, int file, int rank) {
		super(msg + ' ' + file + ' ' +  rank);
		// TODO Auto-generated constructor stub
	}

	public OutOfBoardException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public OutOfBoardException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public OutOfBoardException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
