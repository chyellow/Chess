package chess;

public class Pawn extends ReturnPiece {

    public Pawn(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        this.pieceType = pieceType;
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
    }
    

    public static boolean canMove(PieceFile initFile, int initRank, PieceFile nextFile, int nextRank){
        boolean jackubbe = false;

        //Check if we're moving back or foward
        //Check if were in rank 2 or 7 BUT based on color!!!

        return jackubbe;
    }   

}
