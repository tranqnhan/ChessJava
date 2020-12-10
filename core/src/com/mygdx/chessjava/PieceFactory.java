package com.mygdx.chessjava;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PieceFactory {
    static final Texture CHESS_PIECES_TEXTURE = new Texture("chess_pieces.png");
    static TextureRegion[][] chessPiecesReg = TextureRegion.split(CHESS_PIECES_TEXTURE, CHESS_PIECES_TEXTURE.getWidth() / 6, CHESS_PIECES_TEXTURE.getHeight() / 2);;
    
    public static Pawn makePawnPiece(PieceColor color) {
        return new Pawn(chessPiecesReg[color.value][5], color);
    }
    
    public static Knight makeKnightPiece(PieceColor color) {
        return new Knight(chessPiecesReg[color.value][3], color);
    }
    
    public static Queen makeQueenPiece(PieceColor color) {
        return new Queen(chessPiecesReg[color.value][1], color);
    }
    
    public static Rook makeRookPiece(PieceColor color) {
        return new Rook(chessPiecesReg[color.value][2], color);
    }
   
    public static Bishop makeBishopPiece(PieceColor color) {
        return new Bishop(chessPiecesReg[color.value][4], color);
    }
    
    public static King makeKingPiece(PieceColor color) {
        return new King(chessPiecesReg[color.value][0], color);
    }
    
    public static void disposeTexture() {
        CHESS_PIECES_TEXTURE.dispose();
    }
}
