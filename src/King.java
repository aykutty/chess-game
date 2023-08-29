public class King extends Piece{
    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);
        if(rowDistance <= 1 && colDistance <= 1){
            validMove = targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.getColor();
        }
        return validMove;
    }
    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        if(canMove(to)){
            targetLocation.setPiece(this);
        }
        location.clear();
        setLocation(targetLocation);
        location.getBoard().nextPlayer();
    }
    @Override
    public String toString() {
        return color == 0 ? "K" : "k";
    }

}
