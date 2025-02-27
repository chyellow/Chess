package chess;

import java.util.ArrayList;

public class Piece extends ReturnPiece {
    public Piece(PieceType pieceType, PieceFile pieceFile, int pieceRank)
    {
        this.pieceType = pieceType;
        this.pieceFile = pieceFile; 
        this.pieceRank = pieceRank;
    }
    
    static ArrayList<ReturnPiece> pieces = Chess.getPieces();

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

    public PieceType getPieceTypeAtPosition(PieceFile pieceFile, int pieceRank) {
        for (ReturnPiece piece : pieces) {
            if (piece.pieceFile == pieceFile && piece.pieceRank == pieceRank) {
                return piece.pieceType;
            }
        }
        return null; // No piece found at the given position
    }

    public Pawn getPawn(PieceFile pieceFile, int pieceRank) {
        for (ReturnPiece piece : pieces) {
            if (piece.pieceFile == pieceFile && piece.pieceRank == pieceRank && (piece.pieceType == PieceType.WP || piece.pieceType == PieceType.BP)) {
                return (Pawn) piece;
            }
        }
        return null; // No pawn found at the given position
    }

    public King getWhiteKing(PieceFile pieceFile, int pieceRank) {
        for (ReturnPiece piece : pieces) {
            if (piece.pieceFile == pieceFile && piece.pieceRank == pieceRank && (piece.pieceType == PieceType.WK)) {
                return (King) piece;
            }
        }
        return null; // No king found at the given position
    }

    public King getBlackKing(PieceFile pieceFile, int pieceRank) {
        for (ReturnPiece piece : pieces) {
            if (piece.pieceFile == pieceFile && piece.pieceRank == pieceRank && (piece.pieceType == PieceType.BK)) {
                return (King) piece;
            }
        }
        return null; // No king found at the given position
    }

    public Rook getWhiteRook(PieceFile pieceFile, int pieceRank) {
        for (ReturnPiece piece : pieces) {
            if (piece.pieceFile == pieceFile && piece.pieceRank == pieceRank && (piece.pieceType == PieceType.WR)) {
                return (Rook) piece;
            }
        }
        return null; // No rook found at the given position
    }

    public Rook getBlackRook(PieceFile pieceFile, int pieceRank) {
        for (ReturnPiece piece : pieces) {
            if (piece.pieceFile == pieceFile && piece.pieceRank == pieceRank && (piece.pieceType == PieceType.BR)) {
                return (Rook) piece;
            }
        }
        return null; // No rook found at the given position
    }

    public static PieceFile next(PieceFile file) {
        return PieceFile.values()[(file.ordinal() + 1) % PieceFile.values().length];
    }

    public static PieceFile prev(PieceFile file) {
        return PieceFile.values()[(file.ordinal() - 1 + PieceFile.values().length) % PieceFile.values().length];
    }
}