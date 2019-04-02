package lightron.royaletea.fr.lightron;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    private float x;
    private float y;
    private float speed;
    private float size;
    private int directionX = 1;
    private int directionY = 1;
    private Paint color = new Paint();

    public Ball(float _x, float _y, float _speed, float _size){
        x = _x;
        y = _y;
        speed = _speed;
        size = _size;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x,y,size,color);
    }

    public void mooveX(){
        x += speed*directionX;
    }

    public void mooveY(){
        y += speed*directionY;
    }

    public float getSize() { return size; }

    public float getSpeed() { return speed; }

    public float getX() { return x; }

    public float getY() { return y; }

    public int getDirectionX() { return directionX; }

    public int getDirectionY() { return directionY; }

    public Paint getColor() { return color; }

    public void setColor(Paint color) { this.color = color; }

    public void setDirectionX(int directionX) { this.directionX = directionX; }

    public void setDirectionY(int directionY) { this.directionY = directionY; }

    public void setSize(float size) { this.size = size; }

    public void setSpeed(float speed) { this.speed = speed; }

    public void setX(float x) { this.x = x; }

    public void setY(float y) { this.y = y;}
}
