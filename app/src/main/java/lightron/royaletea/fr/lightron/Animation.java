package lightron.royaletea.fr.lightron;

import android.graphics.Canvas;

interface Animation {
    public void startAnimation(Canvas canvas);
    public void stopAnimation();
    public int getTime();
    public int getTimeAnimation();

}
