package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import java.util.ArrayList;

public class Chess {

    enum Player { white, black }
    
    static Player currentPlayer = Player.white;
    static ArrayList<ReturnPiece> pieces = new ArrayList<ReturnPiece>();

    public static ArrayList<ReturnPiece> getPieces() {
        return pieces;
    }

    public static int findPieceIndex(PieceFile file, int rank) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).pieceFile == file && pieces.get(i).pieceRank == rank) {
                return i;
            }
        }
        return -1;
    }
    public static boolean isKingInCheck(char turn, PieceFile kingFile, int kingRank) {
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
    public static ReturnPlay play(String move) { 
        ReturnPlay littleBoy = new ReturnPlay();
        littleBoy.message = null;
        littleBoy.piecesOnBoard = pieces;
    
        move = move.trim();
    
        if (move.equalsIgnoreCase("resign")) {
            littleBoy.message = currentPlayer == Player.white ? ReturnPlay.Message.RESIGN_BLACK_WINS : ReturnPlay.Message.RESIGN_WHITE_WINS;
            return littleBoy;
        }
    
        boolean drawRequested = move.endsWith("draw?");
        if (drawRequested) {
            move = move.substring(0, move.length() - 5).trim();
        }
    
        String[] parts = move.split(" ");
        if (parts.length < 2) {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }
    
        char initFileChar = parts[0].charAt(0);
        int initRank = Character.getNumericValue(parts[0].charAt(1));
        char nextFileChar = parts[1].charAt(0);
        int nextRank = Character.getNumericValue(parts[1].charAt(1));
    
        PieceFile initFile = PieceFile.valueOf(String.valueOf(initFileChar));
        PieceFile nextFile = PieceFile.valueOf(String.valueOf(nextFileChar));
    
        if ((initFile == nextFile) && (initRank == nextRank)) {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }
        
        // Handle pawn promotion
        PieceType promotionType = currentPlayer == Player.white ? PieceType.WQ : PieceType.BQ; // Default to queen
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

        int index = findPieceIndex(initFile, initRank);
        if (index == -1) {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }
    
        char turn = (currentPlayer == Player.white) ? 'w' : 'b';
    
        // Ensure the piece belongs to the current player
        PieceType pieceType = pieces.get(index).pieceType;
        if ((turn == 'w' && pieceType.toString().startsWith("B")) || 
            (turn == 'b' && pieceType.toString().startsWith("W"))) {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }
    
        boolean check = false;
        
        if (pieceType == PieceType.WP || pieceType == PieceType.BP) {
            Pawn pawn = (Pawn) pieces.get(index);
            check = pawn.canMove(initFile, initRank, nextFile, nextRank, turn);
        } else if (pieceType == PieceType.WB || pieceType == PieceType.BB) {
            Bishop bishop = (Bishop) pieces.get(index);
            check = bishop.canMove(initFile, initRank, nextFile, nextRank, turn);
        } else if (pieceType == PieceType.WR || pieceType == PieceType.BR) {
            Rook rook = (Rook) pieces.get(index);
            check = rook.canMove(initFile, initRank, nextFile, nextRank, turn);
        } else if (pieceType == PieceType.WK || pieceType == PieceType.BK) {
            King king = (King) pieces.get(index);
            check = king.canMove(initFile, initRank, nextFile, nextRank, turn);
        } else if (pieceType == PieceType.WN || pieceType == PieceType.BN) {
            Knight knight = (Knight) pieces.get(index);
            check = knight.canMove(initFile, initRank, nextFile, nextRank, turn);
        } else if (pieceType == PieceType.WQ || pieceType == PieceType.BQ) {
            Queen queen = (Queen) pieces.get(index);
            check = queen.canMove(initFile, initRank, nextFile, nextRank, turn);
        } 
    
        if (!check) {
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }
    
        int targetIndex = findPieceIndex(nextFile, nextRank);
    
        // Store original position in case move is reverted
        PieceFile originalFile = pieces.get(index).pieceFile;
        int originalRank = pieces.get(index).pieceRank;
    
        // Move piece
        pieces.get(index).pieceFile = nextFile;
        pieces.get(index).pieceRank = nextRank;
    
        // Ensure the move doesn't leave the player's own king in check
        ReturnPiece king = getKing(turn);
        // Get the current player's king
        if (king == null) {
            return littleBoy; // No king found (should not happen in a valid game)
        }
        PieceFile kingFile = king.pieceFile;
        int kingRank = king.pieceRank;

    
        ReturnPiece capturedPiece = null;
        if (targetIndex != -1) {
            ReturnPiece targetPiece = pieces.get(targetIndex);
            if ((currentPlayer == Player.white && targetPiece.pieceType.toString().startsWith("B")) ||
                (currentPlayer == Player.black && targetPiece.pieceType.toString().startsWith("W"))) {
                
                // Temporarily store the captured piece in case we need to revert the move
                capturedPiece = pieces.remove(targetIndex);
            } else {
                littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
                return littleBoy;
            }
        }
        
        // Check if the move leaves the king in check
        if (isKingInCheck(turn, kingFile, kingRank)) {
            // Undo the move
            pieces.get(index).pieceFile = originalFile;
            pieces.get(index).pieceRank = originalRank;
        
            // Restore the captured piece if there was one
            if (capturedPiece != null) {
                pieces.add(targetIndex, capturedPiece); // Reinsert the captured piece back to its original position
            }
        
            littleBoy.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return littleBoy;
        }

        // Handle en passant capture
        if (pieceType == PieceType.WP || pieceType == PieceType.BP) {
            Pawn pawn = (Pawn) pieces.get(index);
            if (pawn.movedTwo && Math.abs(initFile.ordinal() - nextFile.ordinal()) == 1 && Math.abs(initRank - nextRank) == 1) {
                int capturedPawnRank = (turn == 'w') ? nextRank - 1 : nextRank + 1;
                int capturedPawnIndex = findPieceIndex(nextFile, capturedPawnRank);
                if (capturedPawnIndex != -1 && pieces.get(capturedPawnIndex).pieceType == (turn == 'w' ? PieceType.BP : PieceType.WP)) {
                    pieces.remove(capturedPawnIndex);
                }
            }
        }

        // Handle pawn promotion
        if ((pieceType == PieceType.WP && nextRank == 8) || (pieceType == PieceType.BP && nextRank == 1)) {
            pieces.remove(index);
            if (promotionType == PieceType.WN || promotionType == PieceType.BN) {
                pieces.add(new Knight(promotionType, nextFile, nextRank));
            } else if (promotionType == PieceType.WR || promotionType == PieceType.BR) {
                pieces.add(new Rook(promotionType, nextFile, nextRank));
            } else if (promotionType == PieceType.WB || promotionType == PieceType.BB) {
                pieces.add(new Bishop(promotionType, nextFile, nextRank));
            } else {
                pieces.add(new Queen(promotionType, nextFile, nextRank));
            }
        }
        
        littleBoy.piecesOnBoard = pieces;
        
        // Check if opponent's king is now in check
        char opponentTurn = (currentPlayer == Player.white) ? 'b' : 'w';
        king = getKing(opponentTurn);
        // Get the current player's king
        if (king == null) {
            return littleBoy; // No king found (should not happen in a valid game)
        }
        kingFile = king.pieceFile;
        kingRank = king.pieceRank;
        if (isKingInCheck(opponentTurn, kingFile, kingRank)) {
            littleBoy.message = ReturnPlay.Message.CHECK;
        }
    
        // Change turn only after a successful move
        currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white;
    
        // Reset movedTwo flag for all pawns
        for (ReturnPiece piece : pieces) {
            if (piece.pieceType == PieceType.WP || piece.pieceType == PieceType.BP) {
                Pawn pawn = (Pawn) piece;
                pawn.numTurns++;
            }
        }

        if (drawRequested) {
            littleBoy.message = ReturnPlay.Message.DRAW;
        }
    
        return littleBoy;
    }
    

    public static void start() {
        pieces.clear();
        currentPlayer = Player.white;
        // Create every piece
        // White pawns
        pieces.add(new Pawn(PieceType.WP, PieceFile.a, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.b, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.c, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.d, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.e, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.f, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.g, 2));
        pieces.add(new Pawn(PieceType.WP, PieceFile.h, 2));
        // Black pawns
        pieces.add(new Pawn(PieceType.BP, PieceFile.a, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.b, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.c, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.d, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.e, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.f, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.g, 7));
        pieces.add(new Pawn(PieceType.BP, PieceFile.h, 7));
        // White rooks
        pieces.add(new Rook(PieceType.WR, PieceFile.a, 1));
        pieces.add(new Rook(PieceType.WR, PieceFile.h, 1));
        // Black rooks
        pieces.add(new Rook(PieceType.BR, PieceFile.a, 8));
        pieces.add(new Rook(PieceType.BR, PieceFile.h, 8));
        // White knights
        pieces.add(new Knight(PieceType.WN, PieceFile.b, 1));
        pieces.add(new Knight(PieceType.WN, PieceFile.g, 1));
        // Black knights
        pieces.add(new Knight(PieceType.BN, PieceFile.b, 8));
        pieces.add(new Knight(PieceType.BN, PieceFile.g, 8));
        // White bishops
        pieces.add(new Bishop(PieceType.WB, PieceFile.c, 1));
        pieces.add(new Bishop(PieceType.WB, PieceFile.f, 1));
        // Black bishops
        pieces.add(new Bishop(PieceType.BB, PieceFile.c, 8));
        pieces.add(new Bishop(PieceType.BB, PieceFile.f, 8));
        // White queen
        pieces.add(new Queen(PieceType.WQ, PieceFile.d, 1));
        // Black queen
        pieces.add(new Queen(PieceType.BQ, PieceFile.d, 8));
        // White king
        pieces.add(new King(PieceType.WK, PieceFile.e, 1));
        // Black king
        pieces.add (new King(PieceType.BK, PieceFile.e, 8));
    }
}