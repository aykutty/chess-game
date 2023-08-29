public class Bishop extends Piece{
    public Bishop(int color, Square location) {
        super(color, location);
    }
    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = this.location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRow() - this.location.getRow());
        int colDistance = Math.abs(targetLocation.getCol() - this.location.getCol());
        if (rowDistance == colDistance) {
            if (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.getColor()) {
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
        Square[] between = location.getBoard().getSquaresBetween(this.location,targetLocation);
        for (Square square : between){
            if(!square.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return color == 0 ? "B" : "b";
    }
}
