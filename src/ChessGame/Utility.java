package ChessGame;

public class Utility {
	static public boolean IsInRangeOfFile(char file)
	{
		int intFile;
		if(Character.isUpperCase(file)) intFile = file - 'A';
		else if(Character.isLowerCase(file)) intFile = file - 'a';
		else return false;
		
		if(intFile < 0 || intFile >= ChessBoard.INT_BOARDFILE) return false;
		else return true;
	}
	static public boolean IsInRangeOfRank(char rank)
	{
		int intRank;
		if(Character.isDigit(rank)) intRank = rank - '1';
		else return false;
		
		if(intRank < 0 || intRank >= ChessBoard.INT_BOARDRANK ) return false;
		else return true;
	}
	static public Coord NewCoordOneMoveTo_From_(EDirection dir, Coord stdCoord)
	{
		Coord newCoord = null;
		char newCoordFile = stdCoord.GetCharFile();
		char newCoordRank = stdCoord.GetCharRank();
		switch(dir)
		{
		case UP:
			newCoordRank += 1;
			break;
		case DOWN:
			newCoordRank -= 1;
			break;
		case LEFT:
			newCoordFile -= 1;
			break;
		case RIGHT:
			newCoordFile += 1;
			break;
		case UPLEFT:
			newCoordRank += 1;
			newCoordFile -= 1;
			break;
		case UPRIGHT:
			newCoordRank += 1;
			newCoordFile += 1;
			break;
		case DOWNLEFT:
			newCoordRank -= 1;
			newCoordFile -= 1;
			break;
		case DOWNRIGHT:
			newCoordRank -= 1;
			newCoordFile += 1;
			break;
		}
		if(IsInRangeOfFile(newCoordFile) && IsInRangeOfRank(newCoordRank)) 
			newCoord = new Coord(newCoordFile, newCoordRank);
		return newCoord;
	}
	

}
