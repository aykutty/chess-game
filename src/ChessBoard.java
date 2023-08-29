public class ChessBoard {
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public Square[][] board = new Square[8][8];
    private boolean isWhitePlaying;

    public ChessBoard(){
        initializeBoard();
        isWhitePlaying = true;
    }
    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append(" A B C D E F G H\n");
        boardString.append("-----------------\n");

        for (int row = 0; row < 8; row++) {
            boardString.append(row + 1).append("|");

            for (int col = 0; col < 8; col++) {
                Square square = board[row][col];
                board[row][col].setBoard(this);

                if (square.getPiece() != null) {
                    boardString.append(square.getPiece().toString());
                } else {
                    boardString.append(" ");
                }

                boardString.append("|");
            }

            boardString.append(row + 1).append("\n");
            boardString.append("-----------------\n");
        }

        boardString.append("  A B C D E F G H\n");
        return boardString.toString();
    }
    public void initializeBoard(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Square(row, col);
                board[row][col].setBoard(this);
            }
        }
        //Initialize white pieces
        board[0][0].setPiece(new Rook(0, board[0][0]));
        board[0][1].setPiece(new Knight(0, board[0][1]));
        board[0][2].setPiece(new Bishop(0, board[0][2]));
        board[0][3].setPiece(new Queen(0, board[0][3]));
        board[0][4].setPiece(new King(0, board[0][4]));
        board[0][5].setPiece(new Bishop(0, board[0][5]));
        board[0][6].setPiece(new Knight(0, board[0][6]));
        board[0][7].setPiece(new Rook(0, board[0][7]));

        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(new Pawn(0, board[1][col]));
        }
        // Initialize black pieces
        board[7][0].setPiece(new Rook(1, board[7][0]));
        board[7][1].setPiece(new Knight(1, board[7][1]));
        board[7][2].setPiece(new Bishop(1, board[7][2]));
        board[7][3].setPiece(new Queen(1, board[7][3]));
        board[7][4].setPiece(new King(1, board[7][4]));
        board[7][5].setPiece(new Bishop(1, board[7][5]));
        board[7][6].setPiece(new Knight(1, board[7][6]));
        board[7][7].setPiece(new Rook(1, board[7][7]));

        for (int col = 0; col < 8; col++) {
            board[6][col].setPiece(new Pawn(1, board[6][col]));
        }
    }
    public Square getSquareAt(String location) {
        int row = location.charAt(1) - '1';
        int col = location.charAt(0) - 'a';
        return board[row][col];
    }

    public Square[] getSquaresBetween(Square start, Square end) {
        int startRow = start.getRow();
        int startCol = start.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        int numRowsBetween = Math.abs(endRow - startRow) - 1;
        int numColsBetween = Math.abs(endCol - startCol) - 1;
        int numSquaresBetween = Math.abs(Math.min(numRowsBetween, numColsBetween));

        Square[] squaresBetween = new Square[numSquaresBetween];

        int rowDirection = (endRow > startRow) ? 1 : -1;
        int colDirection = (endCol > startCol) ? 1 : -1;

        for (int i = 0; i < numSquaresBetween; i++) {
            int row = startRow + (i + 1) * rowDirection;
            int col = startCol + (i + 1) * colDirection;
            squaresBetween[i] = getSquareAt(String.valueOf((char)('a' + col)) + (row + 1));
        }
        return squaresBetween;
    }
    public boolean isWhitePlaying() {
        return isWhitePlaying;
    }

    public boolean isGameEnded() {
        boolean whitePiecesExist = false;
        boolean blackPiecesExist = false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = board[row][col];
                if (square != null) {
                    Piece piece = square.getPiece();
                    if (piece != null) {
                        if (piece.getColor() == ChessBoard.WHITE) {
                            whitePiecesExist = true;
                        } else if (piece.getColor() == ChessBoard.BLACK) {
                            blackPiecesExist = true;
                        }
                    }
                }
            }
        }
        return !(whitePiecesExist && blackPiecesExist);
    }
    public void nextPlayer() {
        isWhitePlaying = !isWhitePlaying;
    }
    public Piece getPieceAt(String from) {
        return getSquareAt(from).getPiece();
    }
}
