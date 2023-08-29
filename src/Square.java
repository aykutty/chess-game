public class Square {
    private int row;
    private int col;
    private Piece piece;
    private ChessBoard board = new ChessBoard();

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void setBoard(ChessBoard chessBoard) {
        board = chessBoard;
    }
    public int getRowDistance(Square location) {
        return Math.abs(row - location.row);
    }

    public boolean isAtSameColumn(Square targetLocation) {
        return this.getCol() == targetLocation.getCol();
    }

    public boolean isAtLastRow(int color) {
        Square location = piece.location;
        int row = location.getRow();
        if (color == ChessBoard.WHITE && row == 7) {
            return true;
        } else if (color == ChessBoard.BLACK && row == 0) {
            return true;
        } else {
            return false;
        }
    }
    public void clear() {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public int getColDistance(Square targetLocation) {
        return col - targetLocation.col;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }
    public ChessBoard getBoard() {
        System.out.println(this.board);
        return this.board;
    }
    public boolean isNeighborColumn(Square targetLocation) {
        int colDifference = Math.abs(col - targetLocation.getCol());
        return colDifference == 1;
    }
    public void putNewQueen(int color) {
        Square location = piece.location;

        if (location.isAtLastRow(color)) {
            location.setPiece(new Queen(color, location));
        }
    }
    public boolean isAtSameRow(Square targetLocation) {
        return this.getRow() == targetLocation.getRow();
    }
}
