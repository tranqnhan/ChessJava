package com.mygdx.chessjava;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Piece[][] board;
    private int width;
    private int height;
    private final Texture boardTex;
    private final BoardInputListener boardInput;
    private final List<GuideDot> guideDots;
    private Piece pieceSelected;
    
    public final int ORIGIN_X = 0;
    public final int ORIGIN_Y = 0;
    public final int WIDTH_AS_PIXELS = 512;
    public final int HEIGHT_AS_PIXELS = 512;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.board = new Piece[height][width];
        this.boardTex = new Texture("chess_board.png");
        this.guideDots = new ArrayList<>();
        this.boardInput = new BoardInputListener(this);
    }
    
    public void boardInteraction(int boardX, int boardY) {
        this.clearGuideDots();
        
        if (this.pieceSelected == null) {
            if (this.board[boardY][boardX] != null) {
                this.selectPiece(this.board[boardY][boardX]);
            }
        } else {
            if(this.movePiecePosition(this.pieceSelected, boardX, boardY) ||
               this.board[boardY][boardX] == null ||
               this.pieceSelected.position.equals(new PiecePosition(boardX, boardY))) {
               this.pieceSelected = null;
            } else if (this.board[boardY][boardX] != null) {
                this.selectPiece(this.board[boardY][boardX]);
            }
        }
    }
    
    private void selectPiece(Piece piece) {
        List<PiecePosition> moves = piece.getMoves(this.board);
        this.addGuideDots(moves);

        this.pieceSelected = piece;
    }
    
    private void clearGuideDots() {
        for (GuideDot guideDot : this.guideDots) {
            guideDot.dispose();
        }
        this.guideDots.clear();
    }
    
    private void addGuideDots(List<PiecePosition> moves) {
        for (PiecePosition position : moves) {
            this.guideDots.add(new GuideDot((position.x * 64) + ORIGIN_X, (position.y * 64) + ORIGIN_Y));
        }
    }
    
    //Return true if successful, false otherwise
    public boolean movePiecePosition(Piece piece, int boardX, int boardY) {
        PiecePosition currentPosition = piece.position;
        
        if (currentPosition == null) {
            piece.setPosition(boardX, boardY);
            this.board[boardY][boardX] = piece;
            return true;
        } else {
            if (piece.movePieceOperation(board, boardX, boardY)) {
                this.board[currentPosition.y][currentPosition.x] = null;
                this.board[piece.position.y][piece.position.x] = piece;
                printBoard();
                return true;
            }
            return false;
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    public void render(Batch batch) {
        batch.draw(this.boardTex, ORIGIN_X, ORIGIN_Y, WIDTH_AS_PIXELS, HEIGHT_AS_PIXELS);
        
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (this.board[row][col] != null) {
                   this.board[row][col].render(batch);
                }
            }
        }
        
        for (GuideDot guideDot : this.guideDots) {
            guideDot.render(batch);
        }
    }
    
    public void dispose() {
        this.boardTex.dispose();
        PieceFactory.disposeTexture();
    }
    
    public void printBoard() {
        System.out.println(" -------------------- Printing Board -------------------- ");
        for (Piece[] row : this.board) {
            System.out.print("{");
            for (Piece p : row) {
                if (p != null) {
                    if (p.color == PieceColor.White)
                        System.out.print("WP,");
                    else
                        System.out.print("BP,");
                } else {
                    System.out.print("NN,");
                }
            }
            System.out.print("} \n");
        }
        System.out.println(" -------------------- Operation End --------------------- ");
    }
    
}
