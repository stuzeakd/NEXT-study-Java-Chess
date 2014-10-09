package ChessGame;

import java.util.*;

abstract public class Piece {
	protected ArrayList<Coord> _canAttackTo;
	protected ArrayList<Coord> _canMoveTo;
	protected Coord _pos;
	protected EPlayerColor _color;
	protected EnumSet<EDirection> _attackDirs;

	public Piece(char file, char rank, EPlayerColor color)
	{
		_pos = new Coord(file, rank);
		_canMoveTo = new ArrayList<Coord>();
		_canAttackTo = new ArrayList<Coord>();
		_color = color;
		//test code
		_canMoveTo.add(new Coord('e', '5'));
	}
	abstract public EPieceType 	GetType();
	abstract public void		Update(ChessBoard board);
	abstract public void 		UpdateCanAttackTo(ChessBoard testBoard); 
	public Coord 			GetCoord() { return _pos; }
	public void 			SetCoord(Coord movePos) { _pos = movePos; }
	public ArrayList<Coord> CanAttackTo() { return _canAttackTo; }
	public ArrayList<Coord> CanMoveTo() { return _canMoveTo; }
	public EPlayerColor GetColor() { return _color; }
	public boolean equals(Object objPiece){ 
		if(!(objPiece instanceof Piece)) return false;
		Piece targetPiece = (Piece)objPiece;
		return GetType() == targetPiece.GetType(); 
	}
}
