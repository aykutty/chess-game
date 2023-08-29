public class Knight extends Piece{
    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRow() - this.location.getRow());
        int colDistance = Math.abs(targetLocation.getCol() - this.location.getCol());
        if((rowDistance == 2 && colDistance == 1) || (rowDistance == 1 && colDistance == 2)){
            if(targetLocation.isEmpty() || targetLocation.getPiece().getColor() != this.getColor()){
                validMove = true;
            }else{
                validMove = false;
            }
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
        return color == 0 ? "N" : "n";
    }
}

