package menu.myco.canvasapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class CustomView extends View {

    ArrayList<Circle> circleArrayList = new ArrayList<>();
    ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();


    public CustomView(Context context, ArrayList<Circle> circleArrayList, ArrayList<Rectangle> rectangleArrayList) {
        super(context);
        this.circleArrayList = circleArrayList;
        this.rectangleArrayList = rectangleArrayList;
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {


        //Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        //Bitmap bitmap = Bitmap.createBitmap(2000, 2000, conf);


        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        for(int i =0;i<circleArrayList.size();i++){
            canvas.drawCircle(circleArrayList.get(i).getX(),circleArrayList.get(i).getY(),circleArrayList.get(i).getRadius(),paint);
        }

        for(int i =0;i<rectangleArrayList.size();i++){
            canvas.drawRect(rectangleArrayList.get(i).getLeft(),rectangleArrayList.get(i).getTop(),rectangleArrayList.get(i).getRight(),rectangleArrayList.get(i).getBottom(),paint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Boolean value =  super.onTouchEvent(event);


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                float x = event.getX();
                float y = event.getY();


                for(int i=0;i<rectangleArrayList.size();i++) {
                    if (   rectangleArrayList.get(i).getRight() >= x
                            && rectangleArrayList.get(i).getLeft() <= x
                            && rectangleArrayList.get(i).getBottom() >= y
                            && rectangleArrayList.get(i).getTop() <= y){
                        System.out.println("rectangle touched");
                    }


                }

                for(int i=0;i<circleArrayList.size();i++) {
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







}
