package chess;

public class Knight extends Piece {
    public Knight(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
    }

    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn) {
        // Ensure move is within board bounds
        if (!isWithinBoard(nextFile, nextRank)) {
            return false;
        }

        int fileDiff = Math.abs(currFile.ordinal() - nextFile.ordinal());
        int rankDiff = Math.abs(currRank - nextRank);

        // Knights move in an "L" shape: (2,1) or (1,2)
        if (!((fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2))) {
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
