package com.mygdx.chessjava;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(TextureRegion texture, PieceColor color) {
        super(texture, color);
    }

    @Override
    public List<PiecePosition> getMoves(Piece[][] board) {
        List<PiecePosition> moves = new ArrayList<>();
        
        for (int d = 0; d < 4; d++) {
            int oX = (int)(-2*Math.abs(d-1.5)+2);
            int oY = (int)(Math.floor(d/2)*2-1);
            for (int y = oY + this.position.y, x = oX + this.position.x; 
                    y < board.length && x < board[0].length && y > -1 && x > -1; 
                    y += oY, x += oX){
                if (board[y][x] != null && 
                    board[y][x].color.value == this.color.value) {
                    break;
                } else {
                    moves.add(new PiecePosition(x, y));
                }
                
                if (board[y][x] != null && 
                    board[y][x].color.value != this.color.value) {
                    break;
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
