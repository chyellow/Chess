package chess;

public class Rook extends Piece {
    public Rook(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
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
                        while (file != nextFile)
                        {
                            file = Piece.prev(file);

                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }
                        }
                        break;
                    case -1: //Right
                        while (file != nextFile)
                        {
                            file = Piece.next(file);

                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }
                        }
                        break;
                    case 2: //Down
                        while (rank != nextRank)
                        {
                            rank--;

                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }
                        }
                        break;
                    case -2: //Up
                        while (rank != nextRank)
                        {
                            rank++;

                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }
                        }
                        break;
                }
            
                //Check for teamkill
                if(!isSquareEmpty(nextFile, nextRank))
                {
                    if((turn == 'w') && (getPieceColorAtPosition(nextFile, nextRank) == 'b'))
                    {
                        return true;
                    }

                    if((turn == 'b') && (getPieceColorAtPosition(nextFile, nextRank) == 'w'))
                    {
                        return true;
                    }
                }

                //Check for empty space
                if(isSquareEmpty(nextFile, nextRank))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
}