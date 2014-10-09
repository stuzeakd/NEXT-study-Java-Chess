package ChessGame;

import java.util.EnumSet;
import java.util.Iterator;

public class King extends Piece {

	public King(char file, char rank, EPlayerColor color) {
		// TODO Auto-generated constructor stub
		super(file, rank, color);
		_attackDirs = EnumSet.of(EDirection.UP, EDirection.DOWN, EDirection.LEFT, EDirection.RIGHT
				,EDirection.UPLEFT, EDirection.UPRIGHT, EDirection.DOWNLEFT, EDirection.DOWNRIGHT);
	}
	public EPieceType GetType(){ return EPieceType.KING; }
	
	@Override
	public void Update(ChessBoard board) {
		// TODO Auto-generated method stub
		if(board == null) return;
		_canMoveTo.clear();
		_canAttackTo.clear();
		if(board.GetSquare(_pos).GetPiece() != GetType() 
				|| board.GetSquare(_pos).GetColor() != _color) return;
		
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		Iterator<EDirection> dirIter = _attackDirs.iterator();
		while(dirIter.hasNext())
		{
			EDirection dir = dirIter.next();
			Coord checkCoord = Utility.NewCoordOneMoveTo_From_(dir, _pos);
			if(checkCoord != null && board.GetSquare(checkCoord).GetColor() != _color){
				_canMoveTo.add(checkCoord);
				_canAttackTo.add(checkCoord);
			}
				
		}
	}
	@Override
	public void UpdateCanAttackTo(ChessBoard testBoard) {
		// TODO Auto-generated method stub
		if(testBoard == null) return;
		_canAttackTo.clear();
		if(testBoard.GetSquare(_pos).GetPiece() != GetType() 
				|| testBoard.GetSquare(_pos).GetColor() != _color) return;
		
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		Iterator<EDirection> dirIter = _attackDirs.iterator();
		while(dirIter.hasNext())
		{
			EDirection dir = dirIter.next();
			Coord checkCoord = Utility.NewCoordOneMoveTo_From_(dir, _pos);
			if(checkCoord != null && testBoard.GetSquare(checkCoord).GetColor() != _color)
				_canAttackTo.add(checkCoord);
		}
	}
}