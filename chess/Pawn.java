package chess;

public class Pawn extends Piece {

    boolean hasMoved = false;
    boolean movedTwo = false;
    boolean enPassant = false;
    int numTurns = 0;
    int movedTwoTurn = -1;
    public Pawn(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
        this.hasMoved = false;
        this.movedTwo = false;
        this.enPassant = false;
    }



    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)   //turn will either be 'b' or 'w'
    {
        if(isWithinBoard(nextFile, nextRank)){
            //Moving 2 on first turn
            if(!hasMoved && isSquareEmpty(nextFile, nextRank) && isSquareEmpty(nextFile, nextRank - 1) && (currFile == nextFile) && (currRank + 2 == nextRank) && (turn == 'w'))
            {
                this.hasMoved = true;
                this.movedTwo = true;
                this.movedTwoTurn = numTurns;
                return true;
            }
            if(!hasMoved && isSquareEmpty(nextFile, nextRank) && isSquareEmpty(nextFile, nextRank + 1) && (currFile == nextFile) && (currRank - 2 == nextRank) && (turn == 'b'))
            {
                this.hasMoved = true;
                this.movedTwo = true;
                this.movedTwoTurn = numTurns;
                return true;
            }

            //Moving one foward
            if(isSquareEmpty(nextFile, nextRank) && (currFile == nextFile) && (currRank + 1 == nextRank) && (turn == 'w')) 
            {
                this.hasMoved = true;
                return true;
            }
            if(isSquareEmpty(nextFile, nextRank) && (currFile == nextFile) && (currRank - 1 == nextRank) && (turn == 'b')) 
            {
                this.hasMoved = true;
                return true;
            }

            //Taking diagonally
            if (!isSquareEmpty(nextFile, nextRank) && ((currFile.ordinal() + 1 == nextFile.ordinal()) || (currFile.ordinal() - 1 == nextFile.ordinal())) && (currRank + 1 == nextRank) && (turn == 'w')){
                this.hasMoved = true;
                return true;
            }

            if (!isSquareEmpty(nextFile, nextRank) && ((currFile.ordinal() + 1 == nextFile.ordinal()) || (currFile.ordinal() - 1 == nextFile.ordinal())) && (currRank - 1 == nextRank) && (turn == 'b')){
                this.hasMoved = true;
                return true;
            }

            // En passant for White
            if (turn == 'w' && currRank == 5 && nextRank == 6 && Math.abs(currFile.ordinal() - nextFile.ordinal()) == 1) {
                PieceFile adjacentFile = nextFile; // NextFile is where the capture happens, so it's correct
                Pawn adjacentPawn = getPawn(adjacentFile, currRank); // Look at the current rank where an enemy pawn should be
                if (adjacentPawn != null && adjacentPawn.movedTwo && adjacentPawn.getPieceColorAtPosition(adjacentFile, currRank) == 'b' && isSquareEmpty(nextFile, nextRank) && (numTurns - adjacentPawn.movedTwoTurn <= 1)) {
                    this.hasMoved = true;
                    this.enPassant = true;
                    return true;
                }
            }

            // En passant for Black
            if (turn == 'b' && currRank == 4 && nextRank == 3 && Math.abs(currFile.ordinal() - nextFile.ordinal()) == 1) {
                PieceFile adjacentFile = nextFile; // Same logic for Black
                Pawn adjacentPawn = getPawn(adjacentFile, currRank);
                if (adjacentPawn != null && adjacentPawn.movedTwo && adjacentPawn.getPieceColorAtPosition(adjacentFile, currRank) == 'w' && isSquareEmpty(nextFile, nextRank) && (numTurns - adjacentPawn.movedTwoTurn <= 1)) {
                    this.hasMoved = true;
                    this.enPassant = true;
                    return true;
                }
            }

        }
        
        return false;
    }

}