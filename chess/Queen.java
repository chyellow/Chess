package chess;

public class Queen extends Piece {
    public Queen(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
    }

    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn) {
        if (!isWithinBoard(nextFile, nextRank)) {
            return false;
        }

        int fileDiff = currFile.ordinal() - nextFile.ordinal();
        int rankDiff = currRank - nextRank;

        boolean isStraightMove = (currFile == nextFile) || (currRank == nextRank);
        boolean isDiagonalMove = Math.abs(fileDiff) == Math.abs(rankDiff);

        if (!(isStraightMove || isDiagonalMove)) {
            return false;
        }

        PieceFile file = currFile;
        int rank = currRank;

        int fileStep = Integer.signum(-fileDiff); // Direction for horizontal movement
        int rankStep = Integer.signum(-rankDiff); // Direction for vertical movement

        file = (fileStep != 0) ? PieceFile.values()[file.ordinal() + fileStep] : file;
        rank += rankStep;

        while (file != nextFile || rank != nextRank) {
            if (!isSquareEmpty(file, rank)) {
                return false;
            }

            if (fileStep != 0) {
                file = PieceFile.values()[file.ordinal() + fileStep];
            }
            if (rankStep != 0) {
                rank += rankStep;
            }
        }

        if (!isSquareEmpty(nextFile, nextRank)) {
            if ((turn == 'w' && getPieceColorAtPosition(nextFile, nextRank) == 'w') ||
                (turn == 'b' && getPieceColorAtPosition(nextFile, nextRank) == 'b')) {
                return false;
            }
        }

        return true;
    }
}
