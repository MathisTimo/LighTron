package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class DestroyBall implements Animation {


    private int time = 0;
    private int timeAnimation = 20;

    private boolean isRunning = false;
    private Ball myBall;

    public DestroyBall(Ball ball, Context context) {
        Drawable image;
        myBall = ball;

    }

    @Override
    public void startAnimation(Canvas canvas) {

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
