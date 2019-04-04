package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class LaunchScreen extends View {


    public float width;
    public float height;

    private Drawable image;
    private  Drawable background;
    private  Drawable play;


    public LaunchScreen(Context context) {
        super(context);
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        this.image = context.getDrawable(R.drawable.logo1);
        this.background = context.getDrawable(R.drawable.bg);
        this.play = context.getDrawable(R.drawable.play);
        background.setBounds(0,0, (int)width, (int)(height*1.1));

        image.setBounds(((int)width/2)-400,0,((int)width/2)+400,400);
        play.setBounds(((int)width/2)-400,((int)height/2)-200,((int)width/2)+400,((int)height/2)+200);





    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        background.draw(canvas);
        image.draw(canvas);
        play.draw(canvas);


        invalidate();
    }

}
