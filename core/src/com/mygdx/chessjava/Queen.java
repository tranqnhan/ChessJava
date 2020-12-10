package com.mygdx.javachess;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(TextureRegion texture, PieceColor color) {
        super(texture, color);
    }

    @Override
    public List<PiecePosition> getMoves(Piece[][] board) {
        List<PiecePosition> moves = new ArrayList<>();
        
        for (int d = 0; d < 8; d++) {
            int oX = (int)(Math.round(1.5*Math.sin(.8*d+.5)));
            int oY = (int)(Math.round(1.5*Math.sin(.8*d-.7)));
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
