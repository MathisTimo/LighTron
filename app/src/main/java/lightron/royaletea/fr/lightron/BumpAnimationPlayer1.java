package lightron.royaletea.fr.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class BumpAnimationPlayer1 implements Animation {

    private Particle left;
    private Particle center;
    private Particle right;
    private Particle left2;
    private Particle center2;
    private Particle right2;

    private Particle[] particles;

    private int initialSize = 50;
    private int removedSize = 3;
    private int time = 0;
    private int timeAnimation = 20;


    private boolean isRunning = false;
    private PlayerBar myPlayer;

    public BumpAnimationPlayer1(PlayerBar player,Context context){
        Drawable image = context.getDrawable(R.drawable.particle);
        myPlayer = player;

        left = new Particle((int)player.getLeft()+ (int)player.getWidth()/2-200,(int)player.getBottom()+20,initialSize,image);
        center = new Particle((int)player.getLeft()+(int)player.getWidth()/2-100,(int)player.getBottom()+40,initialSize,image);
        right = new Particle((int)player.getLeft()+(int)player.getWidth()/2+50,(int)player.getBottom()+30,initialSize,image);
        left2 = new Particle((int)player.getLeft()+ (int)player.getWidth()/2-150,(int)player.getBottom()+20,initialSize,image);
        center2 = new Particle((int)player.getLeft()+(int)player.getWidth()/2,(int)player.getBottom()+40,initialSize,image);
        right2 = new Particle((int)player.getLeft()+(int)player.getWidth()/2+100,(int)player.getBottom()+30,initialSize,image);

        particles = new Particle[]{left,left2,center,center2,right,right2};
    }

    @Override
    public void startAnimation(Canvas canvas) {
        if(isRunning){
            changePositionY();
            changePositionX();
            changeSizeParticle();
            drawAllparticle(canvas);
            time++;
        }else{
            setPosX();
            isRunning = true;
        }
    }

    @Override
    public void stopAnimation() {
        resetPos();
        resetSize();
        isRunning = false;
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

    private void setPosX(){
        left.setX((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2-200);
        left2.setX((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2-150);

        center.setX((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2-100);
        center2.setX((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2);

        right.setX((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2+50);
        right2.setX((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2+100);
    }

    private void resetPos(){

        left.reset((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2-200,(int)myPlayer.getBottom()+20);
        left2.reset((int)myPlayer.getLeft()+ (int)myPlayer.getWidth()/2-150,(int)myPlayer.getBottom()+20);

        center.reset((int)myPlayer.getLeft()+(int)myPlayer.getWidth()/2-100,(int)myPlayer.getBottom()+40);
        center2.reset((int)myPlayer.getLeft()+(int)myPlayer.getWidth()/2,(int)myPlayer.getBottom()+40);

        right.reset((int)myPlayer.getLeft()+(int)myPlayer.getWidth()/2+50,(int)myPlayer.getBottom()+30);
        right2.reset((int)myPlayer.getLeft()+(int)myPlayer.getWidth()/2+100,(int)myPlayer.getBottom()+30);
    }

    private void resetSize(){
        for(Particle particle : particles){
            particle.changeSize(initialSize);
        }
    }

    private void changeSizeParticle(){
        for(Particle particle : particles){
            particle.changeSize(particle.getWidth() - removedSize);
        }
    }

    private void drawAllparticle(Canvas canvas){
        for(Particle particle : particles){
            particle.draw(canvas);
        }
    }

    private void changePositionX(){
        left.setX(left.getX()-10);
        left2.setX(left2.getX()-5);

        center.setX(center.getX() - 2);
        center2.setX(center2.getX() + 2);

        right.setX(right.getX()+5);
        right2.setX(right2.getX() + 10);
    }

    private void changePositionY(){
        int speed = 10;
        int lowSpeed = 8;
        left.setY(left.getY() +lowSpeed);
        left2.setY(left.getY() +lowSpeed);

        center.setY(center.getY()+ speed);
        center2.setY(center.getY()+speed);

        right.setY(right.getY()+ lowSpeed);
        right2.setY(right.getY() +lowSpeed);
    }
}
