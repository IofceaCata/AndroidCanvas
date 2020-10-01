package menu.myco.canvasapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class CustomView extends View {

    private ArrayList<Circle> circleArrayList = new ArrayList<Circle>();
    //private Circle _circle = new Circle(getRandom(),getRandom(),10f);
    private float mCircleX,mCircleY,mCircleRadius=10f;

    public CustomView(Context context) {
        super(context);
        for (int i=0;i<=200;i++){
            Circle circle = new Circle(getRandom(),getRandom(),10f);
            circleArrayList.add(circle);

        }
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {




        super.onDraw(canvas);
        Rect rect = new Rect();
        rect.left = 100;
        rect.top =100;
        rect.right = rect.left + 100;
        rect.bottom = rect.top + 100;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
    ////////////////////////////////////////
        Rect rect2 = new Rect();
        rect2.left = 300;
        rect2.top =300;
        rect2.right = rect2.left + 100;
        rect2.bottom = rect2.top + 100;

        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);
    /////////////////////////////////////////

        float cx,cy;
        float radius = 100f;

        cx = getWidth() - radius - 50f;
        cy = 300 + (300/2);
        mCircleX = getWidth()/2;
        mCircleY = getHeight()/2;

        for(int i=0;i<=200;i++){

            canvas.drawCircle(circleArrayList.get(i).getX(),circleArrayList.get(i).getY(),circleArrayList.get(i).getRadius(),paint);
        }

        canvas.drawCircle(mCircleX,mCircleY,mCircleRadius,paint);
        //canvas.drawCircle(_circle.getX(),_circle.getY(),_circle.getRadius(),paint);

        canvas.drawRect(rect,paint);
        canvas.drawRect(rect2,paint2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Boolean value =  super.onTouchEvent(event);


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                float x = event.getX();
                float y = event.getY();

                for(int i=0;i<=200;i++) {

                    //double dx = Math.pow(x - mCircleX,2);
                    //double dy = Math.pow(y - mCircleY,2);
                    double dx = Math.pow(x - circleArrayList.get(i).getX(),2);
                    double dy = Math.pow(y - circleArrayList.get(i).getY(),2);


                    if (dx + dy < Math.pow(circleArrayList.get(i).getRadius(), 2)) {
                        System.out.println("circle touched");
                    }
                }

            }
        }

        return value;
    }


    /*
    public void setCircle(Circle circle) {
        _circle.equals(circle);
    }*/

    public int getRandom(){
        Random rand = new Random();
        return rand.nextInt(1400);
    }
}
