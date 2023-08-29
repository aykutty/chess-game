public class Rook extends Piece{
    public Rook(int color, Square location) {
        super(color, location);
    }
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        if (location.isAtSameRow(targetLocation) || location.isAtSameColumn(targetLocation)) {
            if (targetLocation.isEmpty() || targetLocation.getPiece().color != this.color) {
                if (isPathClear(to)) {
                    validMove = true;
                }
            }
        }
        return validMove;
    }
    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        if (canMove(to)) {
            targetLocation.setPiece(this);
        }
        location.clear();
        setLocation(targetLocation);
        location.getBoard().nextPlayer();
    }
    public boolean isPathClear(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);

        for (Square square : between) {
            if (!square.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public String toString(){
        return color == 0 ? "R" : "r";
    }

}
