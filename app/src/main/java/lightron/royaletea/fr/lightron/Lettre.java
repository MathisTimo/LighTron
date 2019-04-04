package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Lettre {

    private Drawable image;

    private int x;
    private int y;
    private int size;

    private Animation animation;

    public Lettre(Drawable drawable, int _x, int _y, int _size) {
        image = drawable;
        x = _x;
        y = _y;
        size = _size;

        animation = new LettreAnimation(this);
    }

    public void draw(Canvas canvas){
        image.setBounds(x-size/2,y-size/2,x+size/2,y+size/2);
        image.draw(canvas);
    }

    public Animation getAnimation() {
        return animation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
