package chess;

public class King extends Piece {
    boolean hasMoved = false;
    public King(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
        this.hasMoved = false;
    }
    
    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn) {
        //Castling
        if ((turn == 'w') && (currFile == PieceFile.e) && ((nextFile == PieceFile.g) || (nextFile == PieceFile.c)) && (currRank == 1) && (nextRank == 1))
        {
            King king = getWhiteKing(currFile, currRank);
            if ((king != null) && !Chess.isKingInCheck(turn, currFile, currRank) && (king.hasMoved == false))
            {
                if (nextFile == PieceFile.g) //Bottom right case
                {
                    Rook rook = getWhiteRook(PieceFile.h, 1);
                    if ((rook != null) && (rook.hasMoved == false) && isSquareEmpty(PieceFile.f, 1) && isSquareEmpty(PieceFile.g, 1))
                    {
                        if (!Chess.isKingInCheck(turn, PieceFile.f, 1) && !Chess.isKingInCheck(turn, PieceFile.g, 1))
                        {
                            //Move rook
                            this.hasMoved = true;
                            return true;
                        }
                    }
                }

                else if (nextFile == PieceFile.c) //Bottom left case
                {
                    Rook rook = getWhiteRook(PieceFile.a, 1);
                    if ((rook != null) && (rook.hasMoved == false) && isSquareEmpty(PieceFile.b, 1) && isSquareEmpty(PieceFile.c, 1) && isSquareEmpty(PieceFile.d, 1))
                    {
                        if (!Chess.isKingInCheck(turn, PieceFile.b, 1) && !Chess.isKingInCheck(turn, PieceFile.c, 1) && !Chess.isKingInCheck(turn, PieceFile.d, 1))
                        {
                            //move Rook
                            this.hasMoved = true;
                            return true;
                        }
                    }
                }
            }
        }
        else if ((turn == 'b') && (currFile == PieceFile.e) && ((nextFile == PieceFile.g) || (nextFile == PieceFile.c)) && (currRank == 8) && (nextRank == 8))
        {
            King king = getBlackKing(currFile, currRank);
            if ((king != null) && !Chess.isKingInCheck(turn, currFile, currRank) && (king.hasMoved == false))
            {
                if (nextFile == PieceFile.g) //Top right case
                {
                    Rook rook = getBlackRook(PieceFile.h, 8);
                    if ((rook != null) && (rook.hasMoved == false) && isSquareEmpty(PieceFile.f, 8) && isSquareEmpty(PieceFile.g, 8))
                    {
                        if (!Chess.isKingInCheck(turn, PieceFile.f, 8) && !Chess.isKingInCheck(turn, PieceFile.g, 8))
                        {
                            //move Rook
                            this.hasMoved = true;
                            return true;
                        }
                    }
                }

                else if (nextFile == PieceFile.c) //Top left case
                {
                    Rook rook = getBlackRook(PieceFile.a, 8);
                    if ((rook != null) && (rook.hasMoved == false) && isSquareEmpty(PieceFile.b, 8) && isSquareEmpty(PieceFile.c, 8) && isSquareEmpty(PieceFile.d, 8))
                    {
                        if (!Chess.isKingInCheck(turn, PieceFile.b, 8) && !Chess.isKingInCheck(turn, PieceFile.c, 8) && !Chess.isKingInCheck(turn, PieceFile.d, 8))
                        {
                            rook.hasMoved = true;
                            rook.pieceFile = PieceFile.d;
                            this.hasMoved = true;
                            return true;
                        }
                    }
                }
            }
        }

        // Ensure move is within board bounds
        if (!isWithinBoard(nextFile, nextRank)) {
            return false;
        }

        // Ensure move is only one square in any direction
        if (Math.abs(currFile.ordinal() - nextFile.ordinal()) > 1 || Math.abs(currRank - nextRank) > 1) {
            return false;
        }

        // Check if the target square has a piece
        if (!isSquareEmpty(nextFile, nextRank)) {
            // Prevent moving onto a friendly piece
            if ((turn == 'w' && getPieceColorAtPosition(nextFile, nextRank) == 'w') ||
                (turn == 'b' && getPieceColorAtPosition(nextFile, nextRank) == 'b')) {
                return false;
            }
        }

        this.hasMoved = true;
        return true; // Move is valid
    }
}
