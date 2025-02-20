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

		// Trim leading and trailing spaces
		move = move.trim();

        // Handle special moves
        if (move.equalsIgnoreCase("resign")) {
            littleBoy.message = currentPlayer == Player.white ? ReturnPlay.Message.RESIGN_WHITE_WINS : ReturnPlay.Message.RESIGN_BLACK_WINS;
            return littleBoy;
        }

        boolean drawRequested = move.endsWith("draw?");
        if (drawRequested) {
            move = move.substring(0, move.length() - 5).trim();
        }

        // Extract initial and next file and rank from the move string
        String[] parts = move.split(" ");
        if (parts.length < 2) {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }

        char initFileChar = parts[0].charAt(0);
        int initRank = Character.getNumericValue(parts[0].charAt(1));
        char nextFileChar = parts[1].charAt(0);
        int nextRank = Character.getNumericValue(parts[1].charAt(1));

        // Convert the file characters to the PieceFile enum
        PieceFile initFile = PieceFile.valueOf(String.valueOf(initFileChar));
        PieceFile nextFile = PieceFile.valueOf(String.valueOf(nextFileChar));

        //Check if a move is recursive
        if ((initFile == nextFile) && (initRank == nextRank))
        {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }

        // Handle pawn promotion
        PieceType promotionType = PieceType.WQ; // Default to queen
        if (parts.length == 3) {
            switch (parts[2].toUpperCase()) {
                case "N":
                    promotionType = currentPlayer == Player.white ? PieceType.WN : PieceType.BN;
                    break;
                case "R":
                    promotionType = currentPlayer == Player.white ? PieceType.WR : PieceType.BR;
                    break;
                case "B":
                    promotionType = currentPlayer == Player.white ? PieceType.WB : PieceType.BB;
                    break;
                case "Q":
                    promotionType = currentPlayer == Player.white ? PieceType.WQ : PieceType.BQ;
                    break;
            }
        }

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

        // Gets the player turn as a char
        char turn = currentPlayer == Player.white ? 'w' : 'b';

        // Check if the piece is a pawn and if it can move
        if (pieces.get(index).pieceType == PieceType.WP || pieces.get(index).pieceType == PieceType.BP) {
            System.out.println("check");
            Pawn pawn = new Pawn(pieces.get(index).pieceType, pieces.get(index).pieceFile, pieces.get(index).pieceRank);
            check = pawn.canMove(initFile, initRank, nextFile, nextRank, turn);
        }

        if (check) {
            pieces.get(index).pieceFile = nextFile;
            pieces.get(index).pieceRank = nextRank;
            littleBoy.piecesOnBoard = pieces;
            // Switch the current player
            currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white;
            if (drawRequested) {
                littleBoy.message = ReturnPlay.Message.DRAW;
            }
        } else {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
        }

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