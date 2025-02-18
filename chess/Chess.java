package chess;

import java.util.ArrayList;
import chess.ReturnPiece.PieceType;
import chess.ReturnPiece.PieceFile;

public class Chess {

        enum Player { white, black }
		
		static Player currentPlayer = Player.white;
		static ArrayList<ReturnPiece> pieces = new ArrayList<ReturnPiece>();
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */

	public static ArrayList<ReturnPiece> getPieces() {
        return pieces;
    }

	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		//So we get a string and then um we have to uh make it so we knwo the rank ans ummm file of the whatever we get
		//we should get the first line or move and look through the array and see if a piece is on that spot and if it is 
		//I'm liking this idea so were gonna code it now. Heres the code for this idea:
		ReturnPlay littleBoy = new ReturnPlay();
		littleBoy.message = null;
		littleBoy.piecesOnBoard = pieces;

		//Determining the syntax of a move string to get the initial and next rank and file is a whole thing
		//char intitFile = move.charAt(0);
		//int intitRank = move.charAt(1);

		int initRank = 2;
		PieceFile initFile = PieceFile.e;

		int nextRank = 4;
		PieceFile nextFile = PieceFile.e;

		int index = -1;
		for(int i = 0; i < pieces.size(); i++){
			if(pieces.get(i).pieceFile == initFile && pieces.get(i).pieceRank == initRank){
				//We found the piece YAY
				index = i;
				break;
			}
		}

		if (index == -1){
			//WHERE MY PIECE AT???
		}

		boolean check = false;

		//Gets the player turn as a char
		char turn = 'w';
		if (currentPlayer == Player.black)
		{
			turn = 'b';
		}

		//HERE WE GO! NOWWWWW we can check if our piece can actually move to its next position
		//check = Piece.canMove(initFile, initRank, nextFile, nextRank, turn); //For all pieces

		if (pieces.get(index).pieceType == PieceType.WP || pieces.get(index).pieceType == PieceType.BP){
			System.out.println("STINKY");
		}

		//Need if statements about each 


		if (check){
				littleBoy.piecesOnBoard.get(index).pieceFile = nextFile;
				littleBoy.piecesOnBoard.get(index).pieceRank = nextRank;
		}
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		//if move is valid 
		currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white;
		return littleBoy;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		pieces.clear();
		currentPlayer = Player.white;
		//Create every piece
		//White pawns
        pieces.add(new Piece(PieceType.WP, PieceFile.a, 2));
        pieces.add(new Piece(PieceType.WP, PieceFile.b, 2));
		pieces.add(new Piece(PieceType.WP, PieceFile.c, 2));
		pieces.add(new Piece(PieceType.WP, PieceFile.d, 2));
		pieces.add(new Piece(PieceType.WP, PieceFile.e, 2));
		pieces.add(new Piece(PieceType.WP, PieceFile.f, 2));
		pieces.add(new Piece(PieceType.WP, PieceFile.g, 2));
		pieces.add(new Piece(PieceType.WP, PieceFile.h, 2));
		//Black pawns
		pieces.add(new Piece(PieceType.BP, PieceFile.a, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.b, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.c, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.d, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.e, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.f, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.g, 7));
		pieces.add(new Piece(PieceType.BP, PieceFile.h, 7));
		//White rooks
		pieces.add(new Piece(PieceType.WR, PieceFile.a, 1));
		pieces.add(new Piece(PieceType.WR, PieceFile.h, 1));
		//Black rooks
		pieces.add(new Piece(PieceType.BR, PieceFile.a, 8));
		pieces.add(new Piece(PieceType.BR, PieceFile.h, 8));
		//White knights
		pieces.add(new Piece(PieceType.WN, PieceFile.b, 1));
		pieces.add(new Piece(PieceType.WN, PieceFile.g, 1));
		//Black knights
		pieces.add(new Piece(PieceType.BN, PieceFile.b, 8));
		pieces.add(new Piece(PieceType.BN, PieceFile.g, 8));
		//White bishops
		pieces.add(new Piece(PieceType.WB, PieceFile.c, 1));
		pieces.add(new Piece(PieceType.WB, PieceFile.f, 1));
		//Black bishops
		pieces.add(new Piece(PieceType.BB, PieceFile.c, 8));
		pieces.add(new Piece(PieceType.BB, PieceFile.f, 8));
		//White queen
		pieces.add(new Piece(PieceType.WQ, PieceFile.d, 1));
		//Black queen
		pieces.add(new Piece(PieceType.BQ, PieceFile.d, 8));
		//White king
		pieces.add(new Piece(PieceType.WK, PieceFile.e, 1));
		//Black king
		pieces.add(new Piece(PieceType.BK, PieceFile.e, 8));
		PlayChess.printBoard(pieces);
		
	}
}