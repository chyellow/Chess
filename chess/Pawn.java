package chess;

public class Pawn extends Piece {
    public Pawn(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
    }

    public boolean canMove (PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)
    {
        //turn will either be 'b' or 'w'
        //Check based on piece type movement
        return false;
    }
}
