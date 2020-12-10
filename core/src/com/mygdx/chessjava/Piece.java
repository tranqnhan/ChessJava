package com.mygdx.chessjava;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.List;

public abstract class Piece {
    private final TextureRegion texture;
    public final PieceColor color;
    public PiecePosition position;
    
    public Piece(TextureRegion texture, PieceColor color) {
        this.texture = texture;
        this.color = color;
    }
    
    public void setPosition(int boardX, int boardY) {
        position = new PiecePosition(boardX, boardY);
    }
    
    //Get all possible moves, return it in a list of (x, y) coordinates
    public abstract List<PiecePosition> getMoves(Piece[][] board);
    
    //Check if a move is legal, if it is return true
    public abstract boolean movePieceOperation(Piece[][] board, int toBoardX, int toBoardY);
    
    public PieceColor getColor() {
        return this.color;
    }
    
    public void render(Batch batch) {
        if (position != null) {
            batch.draw(texture, position.x * 64, position.y * 64);
        }
    }
}
