public class Queen extends Piece{
    public Queen(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int targetRow = targetLocation.getRow();
        int targetCol = targetLocation.getCol();
        int currentRow = location.getRow();
        int currentCol = location.getCol();
        int rowDistance = Math.abs(targetRow - currentRow);
        int colDistance = Math.abs(targetCol - currentCol);

        if (rowDistance == colDistance) {
            if (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.getColor()){
                if (isPathClear(to)) {
                    validMove = true;
                }
            }
        }else if((rowDistance == 0 || colDistance == 0)){
            if (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.getColor()){
                if (isPathClear(to)) {
                    validMove = true;
                }
            }
        }
        return validMove;
    }
    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        if (canMove(to)) {
            targetLocation.setPiece(this);
        }
        location.clear();
        setLocation(targetLocation);
        location.getBoard().nextPlayer();
    }
    public boolean isPathClear(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);

        for (Square square : between) {
            if (!square.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        if(getColor() == ChessBoard.WHITE){
            return "Q";
        }else
            return "q";
    }
}
