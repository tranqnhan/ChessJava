package com.mygdx.javachess;

public final class BoardInputListener implements MouseEventListener {
    private final Board board;
    
    public BoardInputListener(Board board) {
        this.board = board;
        this.mountListener();
    }
    
    /* When listener finished initializing, it'll add itself to the event handler.
       If adding self to event handler in the constructor, it can cause error when
       it has not finish initializing.
    */
    private void mountListener() {
        MouseEventHandler.addListener(this);
    }
    
    @Override
    public void activateEvent(int button, int x, int y) {
        if (x >= board.ORIGIN_X && x <= board.WIDTH_AS_PIXELS &&
            y >= board.ORIGIN_Y && y <= board.HEIGHT_AS_PIXELS) {
          
            int boardX = ((x - board.ORIGIN_X) * board.WIDTH) / board.WIDTH_AS_PIXELS;
            int boardY = ((y - board.ORIGIN_Y) * board.HEIGHT) / board.WIDTH_AS_PIXELS;
            
            board.boardInteraction(boardX, boardY);
        }
    }
}
