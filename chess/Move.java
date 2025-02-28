package chess;

public class Move extends ReturnPiece {
    public PieceFile fromFile;
    public int fromRank;
    public PieceFile toFile;
    public int toRank;

    public Move(PieceFile fromFile, int fromRank, PieceFile toFile, int toRank) {
        this.fromFile = fromFile;
        this.fromRank = fromRank;
        this.toFile = toFile;
        this.toRank = toRank;
    }
}
