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

    public static boolean isKingInCheck(char turn, PieceFile nextFile, int nextRank) {
        // Get the current player's king
        ReturnPiece king = getKing(turn);
        if (king == null) {
            return false; // No king found (should not happen in a valid game)
        }

        PieceFile kingFile = king.pieceFile;
        int kingRank = king.pieceRank;
        char oppositeTurn = (turn == 'w') ? 'b' : 'w';

        // Iterate through all pieces to see if any can attack the king
        for (ReturnPiece piece : pieces) {
            if (Character.toLowerCase(piece.pieceType.toString().charAt(0)) == oppositeTurn) {
                boolean check = false;
                if (piece.pieceType == PieceType.WP || piece.pieceType == PieceType.BP) {
                    Pawn pawn = (Pawn) piece;
                    check = pawn.canMove(pawn.getPieceFile(), pawn.getPieceRank(), kingFile, kingRank, oppositeTurn);
                } else if (piece.pieceType == PieceType.WR || piece.pieceType == PieceType.BR) {
                    Rook rook = (Rook) piece;
                    check = rook.canMove(rook.getPieceFile(), rook.getPieceRank(), kingFile, kingRank, oppositeTurn);
                } else if (piece.pieceType == PieceType.WN || piece.pieceType == PieceType.BN) {
                    Knight knight = (Knight) piece;
                    check = knight.canMove(knight.getPieceFile(), knight.getPieceRank(), kingFile, kingRank, oppositeTurn);
                } else if (piece.pieceType == PieceType.WB || piece.pieceType == PieceType.BB) {
                    Bishop bishop = (Bishop) piece;
                    check = bishop.canMove(bishop.getPieceFile(), bishop.getPieceRank(), kingFile, kingRank, oppositeTurn);
                } else if (piece.pieceType == PieceType.WQ || piece.pieceType == PieceType.BQ) {
                    Queen queen = (Queen) piece;
                    check = queen.canMove(queen.getPieceFile(), queen.getPieceRank(), kingFile, kingRank, oppositeTurn);
                } else if (piece.pieceType == PieceType.WK || piece.pieceType == PieceType.BK) {
                    King kingPiece = (King) piece;
                    check = kingPiece.canMove(kingPiece.getPieceFile(), kingPiece.getPieceRank(), kingFile, kingRank, oppositeTurn);
                }
                if (check) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ReturnPiece getKing(char turn) {
        // Iterate through all pieces to find the current player's king
        for (ReturnPiece piece : pieces) {
            if ((turn == 'w' && piece.pieceType == PieceType.WK) ||
                (turn == 'b' && piece.pieceType == PieceType.BK)) {
                return piece; // Return the king
            }
        }
        return null; // No king found (should not happen in a valid game)
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