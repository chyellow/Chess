package chess;

public class King extends Piece {
    public King(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
    }
    
    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn) {
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

        return true; // Move is valid
    }
}
