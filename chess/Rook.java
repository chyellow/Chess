package chess;

public class Rook extends Piece {
    boolean hasMoved = false;
    public Rook(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
        this.hasMoved = false;
    }
    
    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)   //turn will either be 'b' or 'w'
    {
        if(isWithinBoard(nextFile, nextRank)){
            //Check if nextPosition is on the vertical or horizontal of currPosition
            boolean isAdjacent = false;
            int rankDifference = currRank - nextRank;
            int fileDifference = currFile.ordinal() - nextFile.ordinal();
            if ((currRank == nextRank) || (currFile == nextFile))
            {
                isAdjacent = true;
            }

            //Check for pieces in the way
            if (isAdjacent)
            {
                PieceFile file = currFile;
                int rank = currRank;
                switch (Integer.signum(rankDifference) * 2 + Integer.signum(fileDifference)) {
                    case 1: //Left
                        file = Piece.prev(file);
                        while (file != nextFile)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            file = Piece.prev(file);
                        }
                        break;
                    case -1: //Right
                        file = Piece.next(file);
                        while (file != nextFile)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            file = Piece.next(file);
                        }
                        break;
                    case 2: //Down
                        rank--;
                        while (rank != nextRank)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            rank--;
                        }
                        break;
                    case -2: //Up
                        rank++;
                        while (rank != nextRank)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            rank++;
                        }
                        break;
                }
            
                //Check for teamkill
                if(!isSquareEmpty(nextFile, nextRank))
                {
                    if((turn == 'w') && (getPieceColorAtPosition(nextFile, nextRank) == 'b'))
                    {
                        this.hasMoved = true;
                        return true;
                    }

                    if((turn == 'b') && (getPieceColorAtPosition(nextFile, nextRank) == 'w'))
                    {
                        this.hasMoved = true;
                        return true;
                    }
                }

                //Check for empty space
                if(isSquareEmpty(nextFile, nextRank))
                {
                    this.hasMoved = true;
                    return true;
                }
            }
        }
        
        return false;
    }
}