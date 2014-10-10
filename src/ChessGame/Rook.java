package ChessGame;

import java.util.EnumSet;
import java.util.Iterator;

public class Rook extends Piece {
	private boolean _notYetMoved;
	public Rook(char file, char rank, EPlayerColor color) {
		super(file, rank, color);
		_attackDirs = EnumSet.of(EDirection.UP, EDirection.DOWN, EDirection.LEFT, EDirection.RIGHT);
		_notYetMoved = true;
	}
	@Override
	public EPieceType GetType() { return EPieceType.ROOK; }
	public boolean IsNotYetMoved(){ return _notYetMoved; }

	@Override
	public void Update(ChessBoard board) {
		// TODO Auto-generated method stub
		if(board == null) return;
		_canMoveTo.clear();
		_canAttackTo.clear();
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		if(board.GetSquare(_pos).GetPiece() != GetType() 
				|| board.GetSquare(_pos).GetColor() != _color) return;

		
		Iterator<EDirection> dirIter = _attackDirs.iterator();
		while(dirIter.hasNext())
		{
			EDirection dir = dirIter.next();
			Coord checkCoord = Utility.NewCoordOneMoveTo_From_(dir, _pos);
			while(checkCoord != null )
			{
				EPlayerColor color = board.GetSquare(checkCoord).GetColor();
				if(color != _color){ //Empty or Enemy.
					_canMoveTo.add(checkCoord);
					_canAttackTo.add(checkCoord);
					if(color != EPlayerColor.NONE) //Enemy.
						break;
				}
				else break; //Alliance.
				checkCoord = Utility.NewCoordOneMoveTo_From_(dir, checkCoord);
			}

		}
	}
	@Override
	public void UpdateCanAttackTo(ChessBoard testBoard) {
		// TODO Auto-generated method stub
		if(testBoard == null) return;
		_canAttackTo.clear();
		
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		if(testBoard.GetSquare(_pos).GetPiece() != GetType() 
				|| testBoard.GetSquare(_pos).GetColor() != _color) return;

		
		Iterator<EDirection> dirIter = _attackDirs.iterator();
		while(dirIter.hasNext())
		{
			EDirection dir = dirIter.next();
			Coord checkCoord = Utility.NewCoordOneMoveTo_From_(dir, _pos);
			while(checkCoord != null )
			{
				EPlayerColor color = testBoard.GetSquare(checkCoord).GetColor();
				if(color != _color){ //Empty or Enemy.
					_canAttackTo.add(checkCoord);
					if(color != EPlayerColor.NONE) //Enemy.
						break;
				}
				else break; //Alliance.
				checkCoord = Utility.NewCoordOneMoveTo_From_(dir, checkCoord);
			}

		}
	}
	public void firstMove() {
		// TODO Auto-generated method stub
		if(_notYetMoved) _notYetMoved = false;
	}

}
