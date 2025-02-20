package chess;

public class Pawn extends Piece {

    private boolean hasMoved = false;

    public Pawn(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
        this.hasMoved = false;
    }

    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)   //turn will either be 'b' or 'w'
    {
        if(isWithinBoard(nextFile, nextRank)){
            //Moving 2 on first turn
            if(!hasMoved && isSquareEmpty(nextFile, nextRank) && isSquareEmpty(nextFile, nextRank - 1) && (currFile == nextFile) && (currRank + 2 == nextRank) && (turn == 'w'))
            {
                return true;
            }
            if(!hasMoved && isSquareEmpty(nextFile, nextRank) && isSquareEmpty(nextFile, nextRank + 1) && (currFile == nextFile) && (currRank - 2 == nextRank) && (turn == 'b'))
            {
                return true;
            }

            //Moving one foward
            if(isSquareEmpty(nextFile, nextRank) && (currFile == nextFile) && (currRank + 1 == nextRank) && (turn == 'w')) 
            {
                return true;
            }
            if(isSquareEmpty(nextFile, nextRank) && (currFile == nextFile) && (currRank - 1 == nextRank) && (turn == 'b')) 
            {
                return true;
            }

            //Taking diagonally
            if (!isSquareEmpty(nextFile, nextRank) && ((currFile.ordinal() + 1 == nextFile.ordinal()) || (currFile.ordinal() - 1 == nextFile.ordinal())) && (currRank + 1 == nextRank) && (turn == 'w')){
                return true;
            }

            if (!isSquareEmpty(nextFile, nextRank) && ((currFile.ordinal() + 1 == nextFile.ordinal()) || (currFile.ordinal() - 1 == nextFile.ordinal())) && (currRank - 1 == nextRank) && (turn == 'b')){
                return true;
            }
        }
      
        return false;
    }
}