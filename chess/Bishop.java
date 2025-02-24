package chess;

public class Bishop extends Piece {
    public Bishop(PieceType pieceType, PieceFile pieceFile, int pieceRank) {
        super(pieceType, pieceFile, pieceRank);
    }
    
    public boolean canMove(PieceFile currFile, int currRank, PieceFile nextFile, int nextRank, char turn)   //turn will either be 'b' or 'w'
    {
        if(isWithinBoard(nextFile, nextRank)){
            //Check if nextPosition is on the diagonal of currPosition
            boolean isDiagonal = false;
            int rankDifference = currRank - nextRank;
            int fileDifference = currFile.ordinal() - nextFile.ordinal();
            if (Math.abs(fileDifference) == Math.abs(rankDifference)) //This will work because of how diagonals work as a square
            {
                isDiagonal = true;
            }

            //Check for pieces in the way
            if (isDiagonal)
            {
                PieceFile file = currFile;
                int rank = currRank;
                switch ((rankDifference > 0 ? 2 : 0) + (fileDifference > 0 ? 1 : 0)) {
                    case 3: //Down left
                        file = Piece.prev(file);
                        rank--;
                        while (rank != nextRank)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            file = Piece.prev(file);
                            rank--;
                        }
                        break;
                    case 2: //Down right
                        file = Piece.next(file);
                        rank--;
                        while (rank != nextRank)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            file = Piece.next(file);
                            rank--;
                        }
                        break;
                    case 1: //Up left
                        file = Piece.prev(file);
                        rank++;
                        while (rank != nextRank)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            file = Piece.prev(file);
                            rank++;
                        }
                        break;
                    case 0: //Up right
                        file = Piece.next(file);
                        rank++;
                        while (rank != nextRank)
                        {
                            if (!isSquareEmpty(file, rank))
                            {
                                return false;
                            }

                            file = Piece.next(file);
                            rank++;
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
        
        System.out.println("FAIL");
        return false;
    }
}