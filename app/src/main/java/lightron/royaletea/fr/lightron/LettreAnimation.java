package lightron.royaletea.fr.lightron;

import android.graphics.Canvas;

public class LettreAnimation implements Animation {

    private int time = 0;
    private int timeAnimation = 60;

    private int initialSize;
    private int sizeRemove = 1;

    private Lettre myLettre;

    public LettreAnimation(Lettre lettre) {
        initialSize = lettre.getSize();
        myLettre = lettre;
    }

    @Override
    public void startAnimation(Canvas canvas) {
        if(myLettre.getSize() >0){
            myLettre.setSize(myLettre.getSize() - sizeRemove);
            myLettre.draw(canvas);
            sizeRemove+= 3;
        }
        time++;
    }

    @Override
    public void stopAnimation() {
        myLettre.setSize(initialSize);
        sizeRemove = 1;
        time = 0;
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
