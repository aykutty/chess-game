public class Pawn extends Piece{
    boolean initialLocation = true;
    public Pawn(int color,Square location) {
        super(color,location);
    }
    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        if (this.location.isAtSameColumn(targetLocation)) {
            if (getColor() == 0 && rowDistance < 0 && rowDistance >= -2){
                if (rowDistance == -2) {
                    if (initialLocation) {
                        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
                return validMove;
            } else if (getColor() == 1 && rowDistance > 0 && rowDistance <= 2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
            }
        }else if (this.location.isNeighborColumn(targetLocation)) {
            if (getColor() == 0 && rowDistance == -1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == 1;

            } else if (getColor() == 1 && rowDistance == 1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == 0;
            }
        }
        return validMove;
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        if(canMove(to)){
            if (targetLocation.isAtLastRow(color)) {
                targetLocation.putNewQueen(color);
            } else {
                targetLocation.setPiece(this);
            }
            location.clear();
            setLocation(targetLocation);
            location.getBoard().nextPlayer();
        }
    }

    @Override
    public String toString() {
        return color == 0 ? "P" : "p";
    }
}