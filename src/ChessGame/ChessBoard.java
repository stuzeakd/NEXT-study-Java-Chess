package ChessGame;

import ChessException.OutOfBoardException;

public class ChessBoard {
	public static final int INT_BOARDRANK = 8;
	public static final int INT_BOARDFILE = 8;
	private Square[][] _squares;
	public ChessBoard(){
		_squares = new Square[INT_BOARDRANK][INT_BOARDFILE];
		for(int rank = 0; rank < INT_BOARDRANK; ++rank)
			for(int file = 0; file < INT_BOARDFILE; ++file)
				_squares[rank][file] = new Square();
	}
	public void SetPiece(Piece piece, EPlayerColor color)
	{
		Coord coord = piece.GetCoord();
		Square targetSquare = GetSquare(coord);
		targetSquare.SetColor(color);
		targetSquare.SetPiece(piece.GetType());
	}
	public Square GetSquare(int file, int rank)
	{
		try{
			if(file < 0 || file > 7) throw new OutOfBoardException("out", file, rank);
			if(rank < 0 || rank > 7) throw new OutOfBoardException("out", file, rank);  
		}catch(OutOfBoardException e){
			System.out.println(e.getMessage());
		}
		return _squares[INT_BOARDRANK - 1 - rank][file];
	}
	public Square GetSquare(Coord pos){
		return GetSquare(pos.GetIntFile(), pos.GetIntRank());
	}
	public void Render()
	{
		for(int rank = INT_BOARDRANK - 1 ; rank >= 0  ; --rank)
		{
			System.out.print(rank + 1);
			
			for(int file = 0; file < INT_BOARDFILE ; ++file)
			{
				Square targetSquare = GetSquare(file, rank);
				if(targetSquare.GetColor() != EPlayerColor.NONE){ 
					System.out.print(' ');		
					System.out.print(GenerateShape(targetSquare));
				}
				else{
					System.out.print(' ');
					System.out.print('\u25e5');
//					if(( rank + file) % 2 == 0 ){
//						System.out.print(' ');
//						System.out.print('\u20d3');
//					}
//					System.out.print(' ');
				}
			}
			System.out.print('\n');
		}
	}
	public char GenerateShape(Square square)
	{
		char shape = 0;
		switch(square.GetColor())
		{
		case WHITE:
			shape = '\u2654';
			break;
		case BLACK:
			shape = '\u265A';
			break;
		case NONE:
			
		}
		switch(square.GetPiece())
		{
		case KING:
			shape += 0;
			break;
		case QUEEN:
			shape += 1;
			break;
		case ROOK:
			shape += 2;
			break;
		case BISHOP:
			shape += 3;
			break;
		case KNIGHT:
			shape += 4;
			break;
		case PAWN:
			shape += 5;
			break;
		default:
		}
		return shape;
	}
	public void UpdateBoard(Coord piecePos, Coord movedPos) {
		// TODO Auto-generated method stub
		Square movedPieceSquare = GetSquare(movedPos); 
		Square beforePieceSquare = GetSquare(piecePos);
		movedPieceSquare.SetColor(beforePieceSquare.GetColor());
		movedPieceSquare.SetPiece(beforePieceSquare.GetPiece());
		beforePieceSquare.SetColor(EPlayerColor.NONE);
		beforePieceSquare.SetPiece(EPieceType.NONE);
	}
	public void Copy(ChessBoard _board) {
		if(_board == null) return ;
		for(int rank = 0; rank < INT_BOARDRANK; ++rank)
			for(int file = 0; file < INT_BOARDFILE; ++file){
				_squares[INT_BOARDRANK - 1 - rank][file].SetColor(_board.GetSquare(file, rank).GetColor());
				_squares[INT_BOARDRANK - 1 - rank][file].SetPiece(_board.GetSquare(file, rank).GetPiece());
			}
		
	}
}
