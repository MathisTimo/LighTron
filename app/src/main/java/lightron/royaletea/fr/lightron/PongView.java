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
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Timer;

public class PongView extends View {

    private  Ball ball;
    private Paint ballColor = new Paint();
    public float width;
    public float height;
    private PlayerBar player1;
    private PlayerBar player2;
    private boolean sendSMS = false;

    private Drawable backGround;

    @SuppressLint("ClickableViewAccessibility")
    public PongView(Context context) {
        super(context);
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        ball = new Ball(50,height/2,20,50,context);
        player1 = new PlayerBar(width/2,width/2,40,150,context);
        player2 = new PlayerBar(width/2,width/2,height-150,height-40,context);
        backGround =context.getDrawable(R.drawable.my_gradient_drawable);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        event.getActionIndex();
        if (event.getY() < height/2){
            switch(event.getAction()){

                case MotionEvent.ACTION_POINTER_DOWN:
                    if (event.getActionIndex() == 0){
                        Log.d("mylog","JOUEUR 1");
                    player1.setLeft(event.getX());
                    player1.setRight(player1.getLeft()+player1.getWidth());
                    // y = event.getY();
                    }
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                   // x = event.getX();

                    player1.setLeft(event.getX());
                    player1.setRight(player1.getLeft()+player1.getWidth());
                   // y = event.getY();
                    invalidate();
                    break;
            }
        } else{
            switch(event.getAction()){
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (event.getActionIndex() == 1) {
                        Log.d("mylog","JOUEUR 1");
                        player2.setLeft(event.getX());
                        player2.setRight(player2.getLeft() + player2.getWidth());
                        // y = event.getY();
                    }
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    // x = event.getX();

                    player2.setLeft(event.getX());
                    player2.setRight(player2.getLeft()+player2.getWidth());
                    // y = event.getY();
                    invalidate();
                    break;
            }

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        backGround.setBounds((int)0, (int)0, (int)width, (int)(height*1.1));
        backGround.draw(canvas);
        ball.draw(canvas);
        player1.draw(canvas);
        player2.draw(canvas);
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
            ball.addRebond();
        }
        if(ball.getY() <= ball.getSize() && ball.getY() < height-ball.getSize()){
            resetBall();
            player1.setLife(player1.getLife()-1);
            ball.addRebond();
        }
        ball.mooveY();
        ball.mooveX();
        endGame();

    }

    private void moveBar(int x){
        player1.setLeft(player1.getLeft()+10);
        player1.setRight(player1.getRight()+10);
    }

    private void bumpOnPlayer(){
        if(ball.getY() - ball.getSize() <= player1.getBottom() && ball.getX() - ball.getSize() >= player1.getLeft() && ball.getX() - ball.getSize() <= player1.getRight()){
            ball.setDirectionY(1);
            ball.addRebond();
        }

        if(ball.getY() + ball.getSize()  >= player2.getTop() && ball.getX() - ball.getSize()  >= player2.getLeft() && ball.getX() - ball.getSize()  <= player2.getRight()){
            ball.setDirectionY(-1);
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



}
