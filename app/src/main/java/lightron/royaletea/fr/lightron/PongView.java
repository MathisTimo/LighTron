package lightron.royaletea.fr.lightron;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Timer;


public class PongView extends View {

    private  Ball ball;
    public float width;
    public float height;

    public Vibrator vibe;

    private PlayerBar player1;
    private PlayerBar player2;
    private boolean sendSMS = false;

    private Lettre lettre1;
    private Lettre lettre2;
    private Lettre lettre3;
    private Lettre lettreGO;

    private Drawable backGround;

    @SuppressLint("ClickableViewAccessibility")
    public PongView(Context context) {
        super(context);
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        ball = new Ball(50,height/2,20,100,context);
        player1 = new PlayerBar(width/2,width/2,40,150,context,1);
        player2 = new PlayerBar(width/2,width/2,height-150,height-40,context,2);
        backGround =context.getDrawable(R.drawable.my_gradient_drawable);
        backGround.setBounds((int)0, (int)0, (int)width, (int)(height*1.1));
        vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        lettre1 = new Lettre(context.getDrawable(R.drawable.lettre1),(int)width/2,(int)height/2,500);
        lettre2 = new Lettre(context.getDrawable(R.drawable.lettre2),(int)width/2,(int)height/2,500);
        lettre3 = new Lettre(context.getDrawable(R.drawable.lettre3),(int)width/2,(int)height/2,500);
        lettreGO = new Lettre(context.getDrawable(R.drawable.go),(int)width/2,(int)height/2,800);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            switch(event.getActionMasked()){
                case MotionEvent.ACTION_MOVE:
                    if(event.getPointerCount() > 1){
                        if(event.getY(1) <= height/2){
                            player1.setLeft(event.getX(1) - player1.getWidth() / 2);
                            player1.setRight(player1.getLeft() + player1.getWidth());

                            player2.setLeft(event.getX()-player2.getWidth()/2);
                            player2.setRight(player2.getLeft()+player2.getWidth());

                        }else{
                            player2.setLeft(event.getX(1)-player2.getWidth()/2);
                            player2.setRight(player2.getLeft()+player2.getWidth());

                            player1.setLeft(event.getX() - player1.getWidth() / 2);
                            player1.setRight(player1.getLeft() + player1.getWidth());
                        }

                    }else{
                        if(event.getY() <= height/2) {
                            player1.setLeft(event.getX() - player1.getWidth() / 2);
                            player1.setRight(player1.getLeft() + player1.getWidth());
                        }else{
                            player2.setLeft(event.getX()-player2.getWidth()/2);
                            player2.setRight(player2.getLeft()+player2.getWidth());
                        }
                    }
                invalidate();
                break;
            }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        backGround.draw(canvas);
        ball.draw(canvas);
        player1.draw(canvas);
        player2.draw(canvas);
        drawAnimations(canvas);

        lettre3.getAnimation().startAnimation(canvas);
        if(lettre3.getAnimation().getTime() >= lettre3.getAnimation().getTimeAnimation()){
            lettre2.getAnimation().startAnimation(canvas);
        }
        if(lettre2.getAnimation().getTime() >= lettre2.getAnimation().getTimeAnimation()){
            lettre1.getAnimation().startAnimation(canvas);
        }
        if(lettre1.getAnimation().getTime() >= lettre1.getAnimation().getTimeAnimation()){
            lettreGO.getAnimation().startAnimation(canvas);
        }


        updateCoords();
    }


    private void updateCoords(){
        ballBump();
        invalidate();
    }

    private void ballBump(){

        bumpOnPlayer();

        if(ball.getX() >= width-ball.getSize()){
            ball.setDirectionX(-1);
            ball.addRebond();
        }
        if(ball.getX() <= ball.getSize() && ball.getX() < width-ball.getSize()){
            ball.setDirectionX(1);
            ball.addRebond();
        }
        if(ball.getY() >= height-ball.getSize()){
            resetBall();
            player2.setLife(player2.getLife()-1);
            ball.setPlayAnimation(true);
            ball.addRebond();
        }
        if(ball.getY() <= ball.getSize() && ball.getY() < height-ball.getSize()){
            resetBall();
            player1.setLife(player1.getLife()-1);
            ball.setPlayAnimation(true);
            ball.addRebond();
        }
        ball.mooveY();
        ball.mooveX();
        endGame();

    }

    private void bumpOnPlayer(){
        if(ball.getY() <= player1.getBottom() && ball.getX() - ball.getSize() >= player1.getLeft() && ball.getX() <= player1.getRight()){
            ball.setDirectionY(1);
            player1.setPlayAnimation(true);
            vibration();
            player1.getBumpSound().start();
            ball.addRebond();
        }

        if(ball.getY() + ball.getSize()  >= player2.getTop() && ball.getX() - ball.getSize()  >= player2.getLeft() && ball.getX() <= player2.getRight()){
            ball.setDirectionY(-1);
            player2.setPlayAnimation(true);
            vibration();
            player2.getBumpSound().start();
            ball.addRebond();
        }
    }

    private void resetBall(){
        ball.setSpeed(0);
        ball.setX(width/2);
        ball.setY(height/2);
        if (ball.getDirectionY() == 1){
            ball.setDirectionY(-1);
        }else{
            ball.setDirectionY(1);
        }
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        ball.setSpeed(20);
                    }
                },
                2000
        );
    }


    private void endGame() {
        if (gameIsFinished()) {
            if (!sendSMS){
                ball.setX((width - ball.getSize()) /2);
                ball.setY((height/2)-ball.getSize());
                ball.setSpeed(0);
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("PLAYER 2 WIN");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "PLAY AGAIN !",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                player1.setLife(3);
                                player2.setLife(3);
                                ball.setX(((width-ball.getSize())/2));
                                ball.setY((height/2)-ball.getSize());
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                               ball.setSpeed(20);
                                            }
                                        },
                                        5000
                                );

                            }
                        });
                alertDialog.show();
            }
            if (player1.getLife() < 1) {
                if(!sendSMS) {
                    sendSMS = true;
                    //   sendSMS("0783290095", "Le joueur 2 a gagné");
                }
            }
            if (player2.getLife() < 1) {

                if(!sendSMS) {
                    sendSMS = true;
                    //  sendSMS("0783290095", "Le joueur 1 a gagné");
                }
            }
        }
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null, message, null, null);

    }

    private boolean gameIsFinished(){

        return player1.getLife() < 1 || player2.getLife() < 1;
    }



    private void vibration(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibe.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibe.vibrate(50);
        }
    }

    private void drawAnimations(Canvas canvas){
        if(player1.isPlayAnimation()){
            if(player1.getAnimation().getTime() < player1.getAnimation().getTimeAnimation()){
                player1.bumpAnimation(canvas);
            }else{
                player1.setPlayAnimation(false);
                player1.getAnimation().stopAnimation();
            }
        }
        if(player2.isPlayAnimation()){
            if(player2.getAnimation().getTime() < player2.getAnimation().getTimeAnimation()){
                player2.bumpAnimation(canvas);
            }else{
                player2.setPlayAnimation(false);
                player2.getAnimation().stopAnimation();
            }
        }
        if(ball.isPlayAnimation()){
            if(ball.getDestroyBallAnim().getTime() < ball.getDestroyBallAnim().getTimeAnimation()){
                ball.DestroyBallAnimation(canvas);
            }else{
                ball.setPlayAnimation(false);
                ball.getDestroyBallAnim().stopAnimation();
            }
        }
    }

}
