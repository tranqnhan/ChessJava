package com.mygdx.chessjava;

public class PiecePosition {
   int x;
   int y;
   
   public PiecePosition(int x, int y) {
       this.x = x;
       this.y = y;
   }
   
   @Override
   public boolean equals(Object o) {
       if (o instanceof PiecePosition) {
           PiecePosition comparePiece = (PiecePosition) o;
           return comparePiece.x == this.x && comparePiece.y == this.y;
       }
       return false;
   }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }
}
