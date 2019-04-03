package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Ball {

    private float x;
    private float y;
    private float speed;
    private float size;
    private int directionX = 1;
    private int directionY = 1;

    private int nbRebond = 0;

    private Animation destroyBallAnim;

    private Drawable color1;
    private Drawable color2;
    private Drawable color3;
    private Drawable color4;

    public Ball(float _x, float _y, float _speed, float _size, Context context){
        x = _x;
        y = _y;
        speed = _speed;
        size = _size;
        color1 = context.getDrawable(R.drawable.balle);
        color2 = context.getDrawable(R.drawable.balle2);
        color3 = context.getDrawable(R.drawable.balle3);
        color4 = context.getDrawable(R.drawable.balle4);

        destroyBallAnim = new DestroyBall(this,context);

    }

    public void draw(Canvas canvas){
        if (nbRebond <10){
            color1.setBounds((int)x, (int)y, (int)x+100, (int)y+100);
            color1.draw(canvas);
        }
        if(nbRebond >=10 && nbRebond <20){
            color2.setBounds((int)x, (int)y, (int)x+100, (int)y+100);
            color2.draw(canvas);
        }
        if(nbRebond >=20 && nbRebond < 30){
            color3.setBounds((int)x, (int)y, (int)x+100, (int)y+100);
            color3.draw(canvas);
        }
        if(nbRebond >=30){
            color4.setBounds((int)x, (int)y, (int)x+100, (int)y+100);
            color4.draw(canvas);
        }

    }

    public void DestroyBallAnimation(Canvas canvas){
        destroyBallAnim.startAnimation(canvas);
    }

    public void addRebond(){
        nbRebond += 1;
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

    public int getNbRebond() { return nbRebond; }

    public void setDirectionX(int directionX) { this.directionX = directionX; }

    public void setDirectionY(int directionY) { this.directionY = directionY; }

    public void setSize(float size) { this.size = size; }

    public void setSpeed(float speed) { this.speed = speed; }

    public void setX(float x) { this.x = x; }

    public void setY(float y) { this.y = y;}
}
