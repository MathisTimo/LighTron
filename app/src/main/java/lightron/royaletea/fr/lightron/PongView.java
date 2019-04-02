package fr.royaletea.lightron;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class PongView extends View {

    private Ball ball;
    private Paint ballColor = new Paint();
    public float width;
    public float height;

    private PlayerBar player1;
    private PlayerBar player2;
    private Paint player1Color = new Paint();
    private Paint player2Color = new Paint();


    public PongView(Context context) {
        super(context);
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        ball = new Ball(50,height/2,20,50);
        player1 = new PlayerBar(width/2+100,width/2+100,40,150);
        player2 = new PlayerBar(width/2,width/2,height-150,height-40);
        addColor(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
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
        }
        if(ball.getX() <= ball.getSize() && ball.getX() < width-ball.getSize()){
            ball.setDirectionX(1);
        }
        if(ball.getY() >= height-ball.getSize()){
            ball.setDirectionY(-1);
        }
        if(ball.getY() <= ball.getSize() && ball.getY() < height-ball.getSize()){
            ball.setDirectionY(1);
        }
        ball.mooveY();
        ball.mooveX();
    }

    private void addColor(Context context){
        player1Color.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        ballColor.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        player2Color.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        player1.setColor(player1Color);
        ball.setColor(ballColor);
        player2.setColor(player2Color);
    }

    private void bumpOnPlayer(){
        if(ball.getY() - ball.getSize() <= player1.getBottom() && ball.getX() - ball.getSize() >= player1.getLeft() && ball.getX() - ball.getSize() <= player1.getRight()){
            ball.setDirectionY(1);
        }

        if(ball.getY() + ball.getSize()  >= player2.getTop() && ball.getX() - ball.getSize()  >= player2.getLeft() && ball.getX() - ball.getSize()  <= player2.getRight()){
            ball.setDirectionY(-1);
        }
    }
}
