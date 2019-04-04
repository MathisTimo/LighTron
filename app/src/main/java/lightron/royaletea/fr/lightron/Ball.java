package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Ball {

    private float x;
    private float y;
    private float speed;
    private int size;
    private int directionX = 1;
    private int directionY = 1;

    private int nbRebond = 0;

    private int shadowNb = 0;

    private Animation destroyBallAnim;

    private boolean playAnimation = false;

    private Drawable myBall;

    private Shadow shadow;

    private Drawable image1;
    private Drawable image2;
    private Drawable image3;


    public Ball(float _x, float _y, float _speed, int _size, Context context){
        x = _x;
        y = _y;
        speed = _speed;
        size = _size;
        myBall = context.getDrawable(R.drawable.balle);

        destroyBallAnim = new DestroyBall(this,context);

        shadow = new Shadow(size,context);
        image1 = context.getDrawable(R.drawable.shadow);
        image1.setAlpha(80);

        image2 = context.getDrawable(R.drawable.shadow);
        image2.setAlpha(70);

        image3 = context.getDrawable(R.drawable.shadow);
        image3.setAlpha(60);

    }

    public void draw(Canvas canvas){
        shadow.draw(this,canvas);

        myBall.setBounds((int)x, (int)y, (int)x+size, (int)y+size);

        myBall.draw(canvas);
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

    public int getSize() { return size; }

    public float getSpeed() { return speed; }

    public float getX() { return x; }

    public float getY() { return y; }

    public int getDirectionX() { return directionX; }

    public int getDirectionY() { return directionY; }

    public int getNbRebond() { return nbRebond; }

    public boolean isPlayAnimation() {
        return playAnimation;
    }

    public Animation getDestroyBallAnim() {
        return destroyBallAnim;
    }

    public void setDirectionX(int directionX) { this.directionX = directionX; }

    public void setDirectionY(int directionY) { this.directionY = directionY; }

    public void setSize(int size) { this.size = size; }

    public void setSpeed(float speed) { this.speed = speed; }

    public void setX(float x) { this.x = x; }

    public void setY(float y) { this.y = y;}

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
    }
}
