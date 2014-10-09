package ChessGame;

import java.util.*;

public class ChessManager {
	private final int INT_KING_INDEX = 0; 
	private ChessNotation _notation;
	private ArrayList<Piece> _attPieces;
	private ArrayList<Piece> _defPieces;
	private ArrayList<Piece> _whitePieces;
	private ArrayList<Piece> _blackPieces;
	private ChessBoard _board;
	
	//기본적인 판 배치.
	private void BasicPiecesPlacement(){
		for(char file = 'a'; file < (char)('a' + ChessBoard.INT_BOARDFILE); ++file )
		{
			_whitePieces.add(new Pawn(file, '2', EPlayerColor.WHITE));
			_blackPieces.add(new Pawn(file, '7', EPlayerColor.BLACK));
		}
		_whitePieces.add(new Rook('a', '1', EPlayerColor.WHITE));
		_whitePieces.add(new Rook('h', '1', EPlayerColor.WHITE));
		_whitePieces.add(new Knight('b', '1', EPlayerColor.WHITE));
		_whitePieces.add(new Knight('g', '1', EPlayerColor.WHITE));
		_whitePieces.add(new Bishop('c', '1', EPlayerColor.WHITE));
		_whitePieces.add(new Bishop('f', '1', EPlayerColor.WHITE));
		_whitePieces.add(new King('e', '1', EPlayerColor.WHITE));
		_whitePieces.add(new Queen('d', '1', EPlayerColor.WHITE));
		
		_blackPieces.add(new Rook('a', '8', EPlayerColor.BLACK));
		_blackPieces.add(new Rook('h', '8', EPlayerColor.BLACK));
		_blackPieces.add(new Knight('b', '8', EPlayerColor.BLACK));
		_blackPieces.add(new Knight('g', '8', EPlayerColor.BLACK));
		_blackPieces.add(new Bishop('c', '8', EPlayerColor.BLACK));
		_blackPieces.add(new Bishop('f', '8', EPlayerColor.BLACK));
		_blackPieces.add(new King('e', '8', EPlayerColor.BLACK));
		_blackPieces.add(new Queen('d', '8', EPlayerColor.BLACK));
	}
	private void CheckmatePiecesPlacement(){
		_whitePieces.add(new King('f', '5', EPlayerColor.WHITE));
		_whitePieces.add(new Rook('g', '1', EPlayerColor.WHITE));
		_blackPieces.add(new King('h', '5', EPlayerColor.BLACK));
		
	}
	public void Init() {
		_board = new ChessBoard();
		_whitePieces = new ArrayList<Piece>(16);
		_blackPieces = new ArrayList<Piece>(16);
		_attPieces = _whitePieces;
		_defPieces = _blackPieces;
		//BasicPiecesPlacement();
		CheckmatePiecesPlacement();
		_board = MakeBoard();
		UpdatePiecesBy_(_board);
	}
	
	//현재 whitePieces 와 blackPieces에 해당하는 ChessBoard를 생성하여 리턴한다.
	private ChessBoard MakeBoard() {
		// TODO Auto-generated method stub
		Iterator<Piece> pieceIter;
		ChessBoard newBoard = new ChessBoard();
		pieceIter = _whitePieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			newBoard.SetPiece(targetPiece, EPlayerColor.WHITE);
		}
		pieceIter = _blackPieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			newBoard.SetPiece(targetPiece, EPlayerColor.BLACK);
		}
		return newBoard;
	}

	public void Run() {
		while(!IsEndOfGame())
		{
			Piece targetPiece;
			Coord piecePos;
			_board.Render();
			do{
				piecePos = GivePos();
				targetPiece = GetPieceOn_From_(piecePos, _attPieces);				
			}while(targetPiece == null || targetPiece.CanMoveTo().isEmpty());
			_board.Render(); 
			Iterator<Coord> pieceIter = targetPiece.CanMoveTo().iterator();
			while(pieceIter.hasNext()) System.out.print(pieceIter.next() + " ");

			Coord movedPos;
			do{
				movedPos = GivePos();
			}while(!targetPiece.CanMoveTo().contains(movedPos));
			// && !illegal Pos.
			// && Checked.
			//render.

			//check Castling.
			if(targetPiece instanceof King ) //&& move Illegal
			{
				//move rook.
				//boardUpdate(rookPos, movePos);
			}
			if(targetPiece instanceof Pawn ) //&& before move is pawn && En Passant line.
			{
				//pawn die.
			}
			targetPiece.SetCoord(movedPos);
			
			EPlayerColor targetSquareColor = _board.GetSquare(movedPos).GetColor();
			//Def Player's Piece is Dead.
			if(targetSquareColor != targetPiece.GetColor() && targetSquareColor != EPlayerColor.NONE)
			{
				Piece deadPiece = GetPieceOn_From_(movedPos, _defPieces);
				_defPieces.remove(deadPiece);
			}
			_board.UpdateBoard(piecePos, movedPos);
			UpdatePiecesBy_(_board);
			AttDefSwap();
		}
	}
	
	//The attacker 와 The defender를 교환한다. ( 턴을 바꾼다 )
	private void AttDefSwap() {
		// TODO Auto-generated method stub
		ArrayList<Piece> tmpArrList;
		tmpArrList = _attPieces;
		_attPieces = _defPieces;
		_defPieces = tmpArrList;
	}

	private Coord GivePos() {
		// TODO Auto-generated method stub
		//System.in.
		String input;
		Scanner scanIn = new Scanner(System.in);
		do{
			System.out.print("input : ");
			input = scanIn.nextLine();
			
		}while(input.length() != 2 || !Utility.IsInRangeOfFile(input.charAt(0)) || !Utility.IsInRangeOfRank(input.charAt(1)));
	    return new Coord(input.charAt(0), input.charAt(1));
	   
	}

	//board를 통해 Piece의 상태를 업데이트한다.
	private void UpdatePiecesBy_(ChessBoard board) {
		// TODO Auto-generated method stub
		Iterator<Piece> pieceIter = _whitePieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			targetPiece.Update(board);
		}
		pieceIter = _blackPieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			targetPiece.Update(board);
		}
	}
	
	//Pieces에서 Coord위치에있는 Piece를 추출한다.
	private Piece GetPieceOn_From_(Coord coord, ArrayList<Piece> pieces) 
	{
		if(pieces == null) return null; //TODO need null exception
		Iterator<Piece> Iter = pieces.iterator();
		Piece targetPiece = null;
		while(Iter.hasNext())
		{
			Piece IterPiece = Iter.next();  
			if(IterPiece.GetCoord().equals(coord))
			{
				targetPiece = IterPiece; 
				break;
			}
		}
		return targetPiece;
	}

	//아무것도 움직 일 수 있는 Piece가 없는지 체크한다.
	private boolean IsEndOfGame() {
		//the attacker가 가지고 있는 모든 Piece의 CanMoveTo를 체크한다.
		Iterator<Piece> pieceIter = _attPieces.iterator();
		ChessBoard testBoard = new ChessBoard();
		while(pieceIter.hasNext())
		{
			//Piece가 가진 모든 움직일 수 있는 Coord로 움직여 시뮬레이션 해본다.  
			Piece targetPiece = pieceIter.next();
			Coord originCoord = targetPiece.GetCoord();
			Iterator<Coord> coordIter = targetPiece.CanMoveTo().iterator();
			while(coordIter.hasNext())
			{
				Coord testCoord = coordIter.next();
				testBoard.Copy(_board);
				targetPiece.SetCoord(testCoord);

				testBoard.UpdateBoard(originCoord, testCoord);
				UpdatePiecesCanAttackToBy_(testBoard);
				//어떤 한 수라도 두었을 때 체크가 아니라면 false를 리턴.
				if(!IsKingInDanger()){
					targetPiece.SetCoord(originCoord);
					UpdatePiecesBy_(_board);	
					return false;  
				}
			}
			targetPiece.SetCoord(originCoord);
		}
		UpdatePiecesBy_(_board);
		return true;
	}

	private void UpdatePiecesCanAttackToBy_(ChessBoard testBoard) {
		// TODO Auto-generated method stub
		Iterator<Piece> pieceIter = _whitePieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			targetPiece.UpdateCanAttackTo(testBoard);
		}
		pieceIter = _blackPieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			targetPiece.UpdateCanAttackTo(testBoard);
		}
	}
	//The Defender의 모든 Piece의 CanAttackTo를 순회하여 킹이 체크상테인지 확인한다. 
	private boolean IsKingInDanger() {
		Coord KingPos = _attPieces.get(INT_KING_INDEX).GetCoord();
		//The defender의 모든 Piece 순회.
		Iterator<Piece> pieceIter = _defPieces.iterator();
		while(pieceIter.hasNext())
		{
			Piece targetPiece = pieceIter.next();
			//Piece가 공격할 수 있는 위치에 King이 있다면 return ture
			if(targetPiece.CanAttackTo().contains(KingPos)) return true;
		}
		return false;
	}
}
