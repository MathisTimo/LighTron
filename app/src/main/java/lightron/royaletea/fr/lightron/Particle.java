package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Particle {

    private Drawable myParticle;

    private int x = 0;
    private int y = 0;
    private int width;
    private int height;

    public Particle(int _x,int _y,int size,Drawable image) {
        x = _x;
        y = _y;
        width = size;
        height = size;
        myParticle = image;
    }

    public void draw(Canvas canvas){
        myParticle.setBounds(x, y, x+width, y+height);
        myParticle.draw(canvas);
    }

    public void reset(int _x, int _y){
        x = _x;
        y = _y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeSize(int size){
        width= size;
        height = size;
    }

    public int getWidth() {
        return width;
    }
}
