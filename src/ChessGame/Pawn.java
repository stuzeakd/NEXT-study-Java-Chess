package ChessGame;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

public class Pawn extends Piece {
	private EDirection _dir;
	private boolean _notYetMoved;
	public Pawn(char file, char rank, EPlayerColor color) {
		super(file, rank, color);
		_canAttackTo = new ArrayList<Coord>();
		
		if(color == EPlayerColor.BLACK){
			_dir = EDirection.DOWN;
			_attackDirs = EnumSet.of(EDirection.DOWNLEFT, EDirection.DOWNRIGHT);
		}
		else if(color == EPlayerColor.WHITE){
			_dir = EDirection.UP;
			_attackDirs = EnumSet.of(EDirection.UPLEFT, EDirection.UPRIGHT);
		}
		// else : color none exception;
		_notYetMoved = true;
	}
	public boolean IsNotYetMoved(){ return _notYetMoved; }
	public EPieceType GetType(){ return EPieceType.PAWN; }

	@Override
	public void Update(ChessBoard board) {
		// TODO Auto-generated method stub
		if(board == null) return;
		_canMoveTo.clear();
		_canAttackTo.clear();
		
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		if(board.GetSquare(_pos).GetPiece() != GetType() 
				|| board.GetSquare(_pos).GetColor() != _color) return;

		Coord checkCoord = Utility.NewCoordOneMoveTo_From_(_dir, _pos);
		if(checkCoord != null && board.GetSquare(checkCoord).GetColor() == EPlayerColor.NONE)
			_canMoveTo.add(checkCoord);
		if(_notYetMoved){
			checkCoord = Utility.NewCoordOneMoveTo_From_(_dir, checkCoord);
			if(board.GetSquare(checkCoord).GetColor() == EPlayerColor.NONE) _canMoveTo.add(checkCoord);
		}
		
		Iterator<EDirection> dirIter = _attackDirs.iterator();
		while(dirIter.hasNext())
		{
			EDirection dir = dirIter.next();
			checkCoord = Utility.NewCoordOneMoveTo_From_(dir, _pos);
			if(checkCoord != null)
			{
				_canAttackTo.add(checkCoord);
				EPlayerColor checkColor = board.GetSquare(checkCoord).GetColor(); 
				if(checkColor != EPlayerColor.NONE && checkColor != _color) 
					_canMoveTo.add(checkCoord);
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

		
		Coord checkCoord;
		
		Iterator<EDirection> dirIter = _attackDirs.iterator();
		while(dirIter.hasNext())
		{
			EDirection dir = dirIter.next();
			checkCoord = Utility.NewCoordOneMoveTo_From_(dir, _pos);
			if(checkCoord != null)
			{
				_canAttackTo.add(checkCoord);
			}
		}

	}
	public void firstMove() {
		// TODO Auto-generated method stub
		_notYetMoved = false;
	}
}
