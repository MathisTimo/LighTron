package lightron.royaletea.fr.lightron;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

public class PongView extends View {


    Paint myPaint;
    int xb;
    int yb;
    int vbx;
    int vby;
    Drawable d;

    public PongView(Context context) {
        super(context);

        Drawable d = getResources().getDrawable(R.drawable.balle, null);
        myPaint = new Paint();
        xb=0;
        vbx = 3;
        yb=0;
        vby=3;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //canvas.drawCircle(xb, 300, (float) 50.0, myPaint);
        Drawable d = getResources().getDrawable(R.drawable.balle, null);
        d.setBounds(xb, yb, xb+500, yb+500);
        d.draw(canvas);
        moveBall();
    }


    private void moveBall(){

        xb += vbx;
        yb += vby;

        invalidate();
    }

}
