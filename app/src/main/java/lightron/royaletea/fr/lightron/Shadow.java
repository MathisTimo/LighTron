package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class Shadow {

    private Drawable image1;
    private Drawable image2;
    private Drawable image3;
    private Drawable image4;
    private Drawable image5;
    private Drawable image6;

    private Drawable[] images;

    public Shadow(int _size,Context context) {
        image1 = context.getDrawable(R.drawable.shadow);

        image2 = context.getDrawable(R.drawable.shadow);

        image3 = context.getDrawable(R.drawable.shadow);

        image4 = context.getDrawable(R.drawable.shadow);

        image5 = context.getDrawable(R.drawable.shadow);

        image6 = context.getDrawable(R.drawable.shadow);
        images = new Drawable[]{image1, image2, image3, image4, image5, image6};

    }

    public void draw(Ball ball,Canvas canvas){
        changeCoord(ball);
        for(Drawable image : images){
            image.draw(canvas);
            image.setAlpha(10);
        }
    }

    private void changeCoord(Ball ball){
        float lastX = ball.getX() - ball.getSpeed()*ball.getDirectionX();
        float lastY = ball.getY() - ball.getSpeed()*ball.getDirectionY();
        image1.setBounds((int)lastX, (int)lastY, (int)lastX+ball.getSize(), (int)lastY+ball.getSize());
        lastX = lastX - ball.getSpeed()*ball.getDirectionX();
        lastY = lastY - ball.getSpeed()*ball.getDirectionY();
        image2.setBounds((int)lastX, (int)lastY, (int)lastX+ball.getSize(), (int)lastY+ball.getSize());
        lastX = lastX - ball.getSpeed()*ball.getDirectionX();
        lastY = lastY - ball.getSpeed()*ball.getDirectionY();
        image3.setBounds((int)lastX, (int)lastY, (int)lastX+ball.getSize(), (int)lastY+ball.getSize());
        lastX = lastX - ball.getSpeed()*ball.getDirectionX();
        lastY = lastY - ball.getSpeed()*ball.getDirectionY();
        image4.setBounds((int)lastX, (int)lastY, (int)lastX+ball.getSize(), (int)lastY+ball.getSize());
        lastX = lastX - ball.getSpeed()*ball.getDirectionX();
        lastY = lastY - ball.getSpeed()*ball.getDirectionY();
        image5.setBounds((int)lastX, (int)lastY, (int)lastX+ball.getSize(), (int)lastY+ball.getSize());
        lastX = lastX - ball.getSpeed()*ball.getDirectionX();
        lastY = lastY - ball.getSpeed()*ball.getDirectionY();
        image6.setBounds((int)lastX, (int)lastY, (int)lastX+ball.getSize(), (int)lastY+ball.getSize());
    }
}
