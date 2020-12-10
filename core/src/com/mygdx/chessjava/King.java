package com.mygdx.chessjava;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(TextureRegion texture, PieceColor color) {
        super(texture, color);
    }
    
    @Override
    public List<PiecePosition> getMoves(Piece[][] board) {
        List<PiecePosition> moves = new ArrayList<>();
        
        for (int d = 0; d < 8; d++) {
            int x = (int)(Math.round(1.5*Math.sin(.8*d+.5))) + this.position.x;
            int y = (int)(Math.round(1.5*Math.sin(.8*d-.7))) + this.position.y;
            if (x < board[0].length && x > -1 && y < board.length && y > -1) {
                if (!(board[y][x] != null && 
                    board[y][x].color.value == this.color.value)) {
                    moves.add(new PiecePosition(x, y));
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
