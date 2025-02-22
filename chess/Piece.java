package chess;

import java.util.ArrayList;

public class Piece extends ReturnPiece {
    public Piece(PieceType pieceType, PieceFile pieceFile, int pieceRank)
    {
        this.pieceType = pieceType;
        this.pieceFile = pieceFile; 
        this.pieceRank = pieceRank;
    }
    
    ArrayList<ReturnPiece> pieces = Chess.getPieces();

    public int getPieceRank()
    {
        return pieceRank;
    }

    public PieceFile getPieceFile()
    {
        return pieceFile;
    }

    public PieceType getPieceType()
    {
        return pieceType;
    }

    public boolean isWithinBoard(PieceFile pieceFile, int pieceRank)
    {
        return pieceFile.ordinal() >= 0 && pieceFile.ordinal() < 8 && pieceRank >= 1 && pieceRank <= 8;
    }

    public boolean canMove (PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)
    {
        //turn will either be 'b' or 'w'
        //Check based on piece type movement
        return false;
    }

    public boolean isSquareEmpty(PieceFile pieceFile, int pieceRank)
    {
        for(int i = 0; i < pieces.size(); i++){
			if(pieces.get(i).pieceFile == pieceFile && pieces.get(i).pieceRank == pieceRank){
				return false;
			}
		}
        
        return true;
    }
//        char turn = currentPlayer == Player.white ? 'w' : 'b';
//			String pieceStr = "" + rp.pieceType;
//Character.toLowerCase(pieceStr.charAt(0))
    public char getPieceColorAtPosition(PieceFile pieceFile, int pieceRank)
    {
        char color = 'l';
        if (!isSquareEmpty(pieceFile, pieceRank))
        {
            int i;
            for(i = 0; i < pieces.size(); i++){
                if(pieces.get(i).pieceFile == pieceFile && pieces.get(i).pieceRank == pieceRank){
                    break;
                }
            }

            String pieceStr = "" + pieces.get(i).pieceType;
            color = Character.toLowerCase(pieceStr.charAt(0));
        }
        
        return color;
    }    

    public static PieceFile next(PieceFile file) {
        return PieceFile.values()[(file.ordinal() + 1) % PieceFile.values().length];
    }

    public static PieceFile prev(PieceFile file) {
        return PieceFile.values()[(file.ordinal() - 1 + PieceFile.values().length) % PieceFile.values().length];
    }
}