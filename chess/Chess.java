package chess;

import java.util.ArrayList;
import chess.ReturnPiece.PieceType;
import chess.ReturnPiece.PieceFile;

public class Chess {

        enum Player { white, black }
    
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		ArrayList<ReturnPiece> pieces = new ArrayList<ReturnPiece>();
		//Create every piece
		//White pawns
		pieces.add(new Pawn(PieceType.WP, PieceFile.a, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.b, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.c, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.d, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.e, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.f, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.g, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.h, 2));
        //Black pawns
        pieces.add(new Pawn(PieceType.BP, PieceFile.a, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.b, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.c, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.d, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.e, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.f, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.g, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.h, 7));
		//White Bishops
		pieces.add(new Bishop(PieceType.WB, PieceFile.c, 1));
		pieces.add(new Bishop(PieceType.WB, PieceFile.f, 1));
		//Black Bishops
		pieces.add(new Bishop(PieceType.BB, PieceFile.c, 8));
		pieces.add(new Bishop(PieceType.BB, PieceFile.f, 8));
		//White Knights
		pieces.add(new Knight(PieceType.WN, PieceFile.b, 1));
		pieces.add(new Knight(PieceType.WN, PieceFile.g, 1));
		//Black Knights
		pieces.add(new Knight(PieceType.BN, PieceFile.b, 8));
		pieces.add(new Knight(PieceType.BN, PieceFile.g, 8));
		//White Rooks
		pieces.add(new Rook(PieceType.WR, PieceFile.a, 1));
		pieces.add(new Rook(PieceType.WR, PieceFile.h, 1));
		//Black Rooks
		pieces.add(new Rook(PieceType.BR, PieceFile.a, 8));
		pieces.add(new Rook(PieceType.BR, PieceFile.h, 8));
		//White Queens
		pieces.add(new Queen(PieceType.WQ, PieceFile.d, 1));
		//Black Queens
		pieces.add(new Queen(PieceType.BQ, PieceFile.d, 8));
		//White Kings
		pieces.add(new King(PieceType.WK, PieceFile.e, 1));
		//Black Kings
		pieces.add(new King(PieceType.BK, PieceFile.e, 8));
		PlayChess.printBoard(pieces);
	}
}