package ChessGame;

import ChessException.*;

public class Coord {
	private int _file;
	private int _rank;
	public Coord(char file, char rank)
	{
		try{

			if(Character.isUpperCase(file)) _file = file - 'A';
			else if(Character.isLowerCase(file)) _file = file - 'a';
			else throw new WrongInputException("file wrong", file, rank);
			if(Character.isDigit(rank)) _rank = rank - '1';
			else throw new WrongInputException("rank wrong", file, rank);
			
			if(_file < 0 || _file > 7) throw new OutOfBoardException("file out", _file, _rank);
			if(_rank < 0 || _rank > 7) throw new OutOfBoardException("rank out", _file, _rank);  

		}catch(WrongInputException e){
			System.out.println(e.getMessage());
		}catch(OutOfBoardException e){
			System.out.println(e.getMessage());
		}
	}
	public int GetIntFile(){ return _file; }
	public int GetIntRank(){ return _rank; }
	public char GetCharFile(){ return (char)(_file + 'a'); }
	public char GetCharRank(){ return (char)(_rank + '1'); }
	public boolean equals(Object obj) {
		if(!(obj instanceof Coord)) return false;
		Coord coord = (Coord)obj;
		return (_file == coord.GetIntFile() && _rank == coord.GetIntRank());
	}
	public String toString()
	{
		String result = "";
		result += (char)(_file + 'a');
		result += (char)(_rank + '1');
		return result; 
	}
}
