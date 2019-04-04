package lightron.royaletea.fr.lightron;

import android.graphics.Canvas;

interface Animation {
    void startAnimation(Canvas canvas);
    void stopAnimation();
    int getTime();
    int getTimeAnimation();

}
