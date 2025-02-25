package chess;

public class King extends Piece {
    public King(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
    }
    
    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn) {
        // turn will either be 'b' or 'w'
        if (isWithinBoard(nextFile, nextRank)) {
            if (Math.abs(currFile.ordinal() - nextFile.ordinal()) <= 1 && Math.abs(currRank - nextRank) <= 1) {
                // Check for teamkill
                if (!isSquareEmpty(nextFile, nextRank)) {
                    if ((turn == 'w' && getPieceColorAtPosition(nextFile, nextRank) == 'b') ||
                        (turn == 'b' && getPieceColorAtPosition(nextFile, nextRank) == 'w')) {
                        return true;
                    }
                }
                // Check for empty space
                if (isSquareEmpty(nextFile, nextRank)) {
                    return true;
                }
            }
        }
        return false;
    }
}
