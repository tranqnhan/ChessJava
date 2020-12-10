package com.mygdx.javachess;

public enum PieceColor {
    Black(0),
    White(1);
    
    public final int value;
	 
    private PieceColor(int num) {
        this.value = num;
    }
    
}
