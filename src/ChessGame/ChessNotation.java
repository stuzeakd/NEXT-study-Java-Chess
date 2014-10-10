package ChessGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class ChessNotation {
	//수 데이터.
	//Prototype인 관계로  pieceType은 사용하지 않는다.
	private class MoveData{
		private Coord _beforeCoord;
		private Coord _movedCoord;
		private EPieceType _pieceType;
		private String _significant;
		public MoveData(Coord beforeCoord, Coord movedCoord, EPieceType pieceType)
		{
			_beforeCoord 	= beforeCoord;
			_movedCoord 	= movedCoord;
			_pieceType 		= pieceType;
			_significant 	= "";
		}
//		
//		public MoveData(Coord beforeCoord, Coord movedCoord, EPieceType pieceType, String significant)
//		{
//			this(beforeCoord, movedCoord, pieceType);			
//			_significant	= significant;
//		}
		public Coord GetBeforeCoord(){
			return _beforeCoord;
		}
		public Coord GetMovedCoord(){
			return _movedCoord;
		}
		public String toString(){
			return _beforeCoord.toString() + " " + _movedCoord.toString();
		}
	}
	ArrayList<MoveData> _datas;
	boolean firstGetMove = false;
	int dataIndex = 0;
	public ChessNotation(){
		_datas = new ArrayList<MoveData>();
	}
	public void AddMove(Coord beforeCoord, Coord movedCoord, EPieceType pieceType)
	{
		if(beforeCoord == null || movedCoord == null) return;
		_datas.add(new MoveData(beforeCoord, movedCoord, pieceType));
	}
	//임시방편
	public Coord GetMove(){
		firstGetMove = !firstGetMove;
		if(_datas.get(dataIndex) == null) return null;
		if(firstGetMove){
			return _datas.get(dataIndex).GetBeforeCoord();
		}
		else return _datas.get(dataIndex++).GetMovedCoord();
	}
	public boolean HasNext(){ 
		if(dataIndex > _datas.size() -1 ) return false;
		else return true;
	}
	
	public void ParsingNotation(String fileName) throws IOException{
		FileReader input = new FileReader(fileName);
		BufferedReader buffIn = new BufferedReader(input);
		String s;
		while((s=buffIn.readLine())!= null){
			_datas.add(new MoveData(new Coord(s.charAt(0), s.charAt(1)), new Coord(s.charAt(3), s.charAt(4)), EPieceType.NONE)); 
		}
	}
	public void SaveNotation() throws FileNotFoundException
	{
		PrintWriter notationText = new PrintWriter("notation.txt"); 
		Iterator<MoveData> moveDataIterator = _datas.iterator();
		while(moveDataIterator.hasNext())
		{
			MoveData targetMoveData = moveDataIterator.next();
			notationText.println(targetMoveData);
		}
		notationText.close();
	}
}
