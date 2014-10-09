package ChessGame;

public class Square {
	private EPlayerColor _color;
	private EPieceType _piece;
	public Square(){
		_color = EPlayerColor.NONE;
		_piece = EPieceType.NONE;
	}
	public void SetColor(EPlayerColor color){ _color = color; }
	public void SetPiece(EPieceType piece)	{ _piece = piece; }
	public EPlayerColor GetColor()	{ return _color; }
	public EPieceType 	GetPiece()	{ return _piece; }
	
}
