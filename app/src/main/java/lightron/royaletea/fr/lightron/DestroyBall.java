package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class DestroyBall implements Animation {


    private int time = 0;
    private int timeAnimation = 20;
    private Particle top;
    private Particle top1;

    private Particle right;
    private Particle right1;

    private Particle bottom;
    private Particle bottom1;

    private Particle left;
    private Particle left1;

    private int initialSize = 100;

    private boolean isRunning = false;
    private Ball myBall;

    public DestroyBall(Ball ball, Context context) {
        Drawable image = context.getDrawable(R.drawable.particle);
        myBall = ball;

        top = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);
        top1 = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);

        right = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);
        right1 = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);

        bottom = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);
        bottom1 = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);

        left = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);
        left1 = new Particle((int)myBall.getX(),(int)myBall.getY(),initialSize,image);

    }

    @Override
    public void startAnimation(Canvas canvas) {
        if(isRunning){
            top.draw(canvas);
            top1.draw(canvas);
            right.draw(canvas);
            right1.draw(canvas);
            bottom.draw(canvas);
            bottom1.draw(canvas);
            left.draw(canvas);
            left1.draw(canvas);
        }else{

        }

        time++;
    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getTimeAnimation() {
        return timeAnimation;
    }
}
