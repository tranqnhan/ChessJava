package com.mygdx.javachess;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(TextureRegion texture, PieceColor color) {
        super(texture, color);
    }

    @Override
    public List<PiecePosition> getMoves(Piece[][] board) {
        List<PiecePosition> moves = new ArrayList<>();
        
        //Generate all possible moves.
        //Max number of moves = 8
        for (int i = 0; i < 8; i++) {
            int x = this.position.x + (int) Math.round(2.4 * Math.sin(0.75 * i - 1.05));
            int y = this.position.y + (int) Math.round(2.4 * Math.sin(0.75 * i + 0.5));
            
            if (x > -1 && x < board[0].length && y > -1 && y < board[0].length) {
                if (board[y][x] == null || board[y][x].color.value != this.color.value) {
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
