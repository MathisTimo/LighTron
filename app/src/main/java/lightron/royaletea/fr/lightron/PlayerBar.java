package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

public class PlayerBar {

    private float left;
    private float right;
    private float top;
    private float bottom;
    private Paint color = new Paint();
    private float width = 400;
    private float height = 100;

    private MediaPlayer bumpSound;

    private Drawable image1;
    private Drawable image2;
    private Drawable image3;
    private Drawable image4;

    private Animation animation;

    private boolean playAnimation = false;

    private int life = 4;

    public PlayerBar(float _left, float _right, float _top, float _bottom, Context context, int _animation){

        left = _left - (width/2);
        right = _right + (width/2);
        top = _top;
        bottom = _bottom;
        if(_animation == 1){
            animation = new BumpAnimationPlayer1(this,context);

            image1 = context.getDrawable(R.drawable.barre);
            image2 = context.getDrawable(R.drawable.barrebreak1p1);
            image3 = context.getDrawable(R.drawable.barrebreak2p1);
            image4 = context.getDrawable(R.drawable.barrebreak3p1);
        }else{
            animation = new BumpAnimationPlayer2(this,context);

            image1 = context.getDrawable(R.drawable.barre);
            image2 = context.getDrawable(R.drawable.barrebreak1);
            image3 = context.getDrawable(R.drawable.barrebreak2);
            image4 = context.getDrawable(R.drawable.barrebreak3);
        }

        bumpSound = MediaPlayer.create(context,R.raw.pong);

    }

    public void draw(Canvas canvas){
        if (life == 4){
            image1.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image1.draw(canvas);
        }
        if(life == 3){
            image2.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image2.draw(canvas);
        }
        if(life == 2){
            image3.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image3.draw(canvas);
        }
        if(life <= 1){
            image4.setBounds((int)left, (int)top, (int)right, (int)bottom);
            image4.draw(canvas);
        }
    }

    public void bumpAnimation(Canvas canvas){
        animation.startAnimation(canvas);
    }

    public float getBottom() { return bottom; }

    public float getLeft() { return left; }

    public float getRight() { return right; }

    public float getHeight() { return height; }

    public float getWidth() { return width; }

    public float getTop() { return top; }

    public int getLife() { return life; }

    public boolean isPlayAnimation() { return playAnimation; }

    public Animation getAnimation() {
        return animation;
    }

    public MediaPlayer getBumpSound() {
        return bumpSound;
    }

    public void setLife(int life) { this.life = life; }

    public void setBottom(float bottom) { this.bottom = bottom; }

    public void setLeft(float left) { this.left = left; }

    public void setRight(float right) { this.right = right; }

    public void setTop(float top) { this.top = top; }

    public void setColor(Paint color) { this.color = color; }

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
    }
}
