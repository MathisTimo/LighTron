package lightron.royaletea.fr.lightron;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PlayerBar {

    private float left;
    private float right;
    private float top;
    private float bottom;
    private Paint color = new Paint();
    private float width = 400;
    private float height = 100;

    public PlayerBar(float _left,float _right,float _top, float _bottom){
        left = _left - (width/2);
        right = _right + (width/2);
        top = _top;
        bottom = _bottom;
    }

    public void draw(Canvas canvas){
        canvas.drawRect(left,top,right,bottom,color);

    }

    public float getBottom() { return bottom; }

    public float getLeft() { return left; }

    public float getRight() { return right; }

    public float getHeight() { return height; }

    public float getWidth() { return width; }

    public float getTop() { return top; }

    public void setBottom(float bottom) { this.bottom = bottom; }

    public void setLeft(float left) { this.left = left; }

    public void setRight(float right) { this.right = right; }

    public void setTop(float top) { this.top = top; }

    public void setColor(Paint color) { this.color = color; }



}
