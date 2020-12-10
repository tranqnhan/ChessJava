package com.mygdx.javachess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GuideDot {
    Texture texture;
    int x;
    int y;
    
    public GuideDot(int x, int y) {
        this.x = x;
        this.y = y;
        texture = new Texture("dot.png");
    }
    
    public void render(Batch batch) {
        batch.draw(texture, x + 32 - 8, y + 32 - 8);
    }
    
    public void dispose() {
        texture.dispose();
    }
}
