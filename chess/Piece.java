package chess;

public class Piece extends ReturnPiece {
    public Piece(PieceType pieceType, PieceFile pieceFile, int pieceRank)
    {
        this.pieceType = pieceType;
        this.pieceFile = pieceFile; 
        this.pieceRank = pieceRank;
    }



    public boolean canMove (PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)
    {
        //turn will either be 'b' or 'w'
        //Check based on piece type movement
        return false;
    }
    
    public int getPieceRank()
    {
        return pieceRank;
    }

    public PieceFile getPieceFile()
    {
        return pieceFile;
    }

    public PieceType getPieceType()
    {
        return pieceType;
    }

    public boolean isWithinBoard(PieceFile pieceFile, int pieceRank)
    {
        return pieceFile.ordinal() >= 0 && pieceFile.ordinal() < 8 && pieceRank >= 1 && pieceRank <= 8;
    }
}