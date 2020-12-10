package com.mygdx.javachess;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(TextureRegion texture, PieceColor color) {
        super(texture, color);
    }
    
    //Return true if theres no piece on the square, return false otherwise.
    public boolean searchSquare(int x, int y, Piece[][] board, List<PiecePosition> moves) {
        if (board[y][x] != null && 
            board[y][x].color.value == this.color.value) {
            return false;
        } else {
            moves.add(new PiecePosition(x, y));
            return !(board[y][x] != null && 
                board[y][x].color.value != this.color.value);
        }
    }
    
    @Override
    public List<PiecePosition> getMoves(Piece[][] board) {
        List<PiecePosition> moves = new ArrayList<>();
        
        for (int d = 0; d < 4; d++) {
            int oX = (int)(Math.abs(d-2)-1);
            int oY = (int)(-Math.abs(d-1)+1);
            
            int startX = oX + this.position.x;
            int startY = oY + this.position.y;
            
            if (oX == 0) {
                for (int y = startY; y < board.length && y > -1; y += oY) {
                    if (!this.searchSquare(startX, y, board, moves)) {
                        break;
                    }
                }
            } else {
                for (int x = startX; x < board[0].length && x > -1; x += oX) {
                    if (!this.searchSquare(x, startY, board, moves)) {
                        break;
                    }
                }
            }
        }
        
        return moves;
    }

    @Override
    public boolean movePieceOperation(Piece[][] board, int toBoardX, int toBoardY) {
        PiecePosition newPosition = new PiecePosition(toBoardX, toBoardY);
        if (getMoves(board).contains(newPosition)) {
            this.position = newPosition;
            return true;
        }
        return false;
    }
    
}
