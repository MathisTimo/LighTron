package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

public class PlayerBar {

    private float left;
    private float right;
    private float top;
    private float bottom;
    private Paint color = new Paint();
    private float width = 400;
    private float height = 100;

    private Drawable image1;
    private Drawable image2;
    private Drawable image3;

    private int life;

    public PlayerBar(float _left, float _right, float _top, float _bottom, Context context){
        left = _left - (width/2);
        right = _right + (width/2);
        top = _top;
        bottom = _bottom;
        life = 3;
        image1 = context.getDrawable(R.drawable.barre);
        image2 = context.getDrawable(R.drawable.barrebreak1);
        image3 = context.getDrawable(R.drawable.barrebreak2);
    }

    public void draw(Canvas canvas){
        if (life == 3){
            image1.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image1.draw(canvas);
        }
        if(life == 2){
            image2.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image2.draw(canvas);
        }
        if(life <= 1){
            image3.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image3.draw(canvas);
        }
    }

    public float getBottom() { return bottom; }

    public float getLeft() { return left; }

    public float getRight() { return right; }

    public float getHeight() { return height; }

    public float getWidth() { return width; }

    public float getTop() { return top; }

    public int getLife() { return life; }

    public void setLife(int life) { this.life = life; }

    public void setBottom(float bottom) { this.bottom = bottom; }

    public void setLeft(float left) { this.left = left; }

    public void setRight(float right) { this.right = right; }

    public void setTop(float top) { this.top = top; }

    public void setColor(Paint color) { this.color = color; }



}
