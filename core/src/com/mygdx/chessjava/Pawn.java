package com.mygdx.javachess;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    
    boolean twoStepRule;
    
    public Pawn(TextureRegion texture, PieceColor color) {
        super(texture, color);
        twoStepRule = true;
    }
    
    @Override
    public List<PiecePosition> getMoves(Piece[][] board) {
        List<PiecePosition> moves = new ArrayList<>();   
        
        //Moving Rules
        int lastRank = (board.length - 1) * color.value;
        int direction = 2 * color.value - 1;
            
        if (position.y != lastRank) {
            int numAllowSteps = (twoStepRule) ? 2 : 1;
            for (int s = 1; s < numAllowSteps + 1; s++) {
                if (position.y + (s * direction) == (lastRank + direction) || 
                    board[position.y + (s * direction)][position.x] != null ) {
                    break;
                }
                moves.add(new PiecePosition(position.x, position.y + (s * direction)));
            }
        }        
        
        //Capture Rules
        int forwardY = position.y + direction;
        
        int leftX = position.x - 1;
        if (leftX >= 0 && leftX < board[0].length && 
            board[forwardY][leftX] != null && 
            board[forwardY][leftX].color.value != this.color.value ) {
            moves.add(new PiecePosition(leftX, forwardY));
        }
        
        int rightX = position.x + 1;
        if (rightX >= 0 && rightX < board[0].length && 
            board[forwardY][rightX] != null && 
            board[forwardY][rightX].color.value != this.color.value) {
            moves.add(new PiecePosition(rightX, forwardY));
        }
       
        return moves;
    }
    
    @Override
    public boolean movePieceOperation(Piece[][] board, int toBoardX, int toBoardY) {
        List<PiecePosition> allowedMoves = this.getMoves(board);   
        PiecePosition newPosition = new PiecePosition(toBoardX, toBoardY);
        
        if (allowedMoves.contains(newPosition)) {
            if (twoStepRule) {
                twoStepRule = false;
            }
            this.position = newPosition;
            return true;
        }
        return false;
    }
}
