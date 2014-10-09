package ChessGame;

public class Knight extends Piece {

	public Knight(char file, char rank, EPlayerColor color) {
		super(file, rank, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EPieceType GetType() { return EPieceType.KNIGHT; }

	@Override
	public void Update(ChessBoard board) {
		// TODO Auto-generated method stub
		if(board == null){
			// TODO Exception
			return;
		}
		_canMoveTo.clear();
		_canAttackTo.clear();

		
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		if(board.GetSquare(_pos).GetPiece() != GetType() 
				|| board.GetSquare(_pos).GetColor() != _color) return;

		
		char stdFile = _pos.GetCharFile();
		char stdRank = _pos.GetCharRank();

		char targetFile = (char)(stdFile + 2);
		char targetRank = (char)(stdRank + 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		targetRank = (char)(stdRank - 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		
		targetFile = (char)(stdFile + 1);
		targetRank = (char)(stdRank + 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		targetRank = (char)(stdRank - 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		
		targetFile = (char)(stdFile - 1);
		targetRank = (char)(stdRank + 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		targetRank = (char)(stdRank - 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		
		targetFile = (char)(stdFile - 2);
		targetRank = (char)(stdRank + 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);				
			}
		}
		
		targetRank = (char)(stdRank - 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != board.GetSquare(coord).GetColor()){
				_canMoveTo.add(coord);
				_canAttackTo.add(coord);
			}
		}
 		
	}

	@Override
	public void UpdateCanAttackTo(ChessBoard testBoard) {
		// TODO Auto-generated method stub
		if(testBoard == null){
			// TODO Exception
			return;
		}
		_canMoveTo.clear();
		
		// 보드에서 _pos 위치에 있는 Piece가 자신과 다를경우  
		if(testBoard.GetSquare(_pos).GetPiece() != GetType() 
				|| testBoard.GetSquare(_pos).GetColor() != _color) return;

		
		char stdFile = _pos.GetCharFile();
		char stdRank = _pos.GetCharRank();

		char targetFile = (char)(stdFile + 2);
		char targetRank = (char)(stdRank + 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		targetRank = (char)(stdRank - 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		
		targetFile = (char)(stdFile + 1);
		targetRank = (char)(stdRank + 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		targetRank = (char)(stdRank - 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		
		targetFile = (char)(stdFile - 1);
		targetRank = (char)(stdRank + 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		targetRank = (char)(stdRank - 2);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		
		targetFile = (char)(stdFile - 2);
		targetRank = (char)(stdRank + 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
		
		targetRank = (char)(stdRank - 1);
		if(Utility.IsInRangeOfFile(targetFile) && Utility.IsInRangeOfRank(targetRank)){
			Coord coord = new Coord(targetFile, targetRank);
			if(_color != testBoard.GetSquare(coord).GetColor())
				_canAttackTo.add(coord);
		}
 		
	}
}
