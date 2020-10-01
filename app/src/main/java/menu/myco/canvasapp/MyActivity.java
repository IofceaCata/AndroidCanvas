package menu.myco.canvasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {

    ArrayList<Circle> circleArrayList = new ArrayList<>();
    Circle _circle = new Circle(10,10,10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        final ZoomLinearLayout zoomLinearLayout = (ZoomLinearLayout) findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zoomLinearLayout.init(MyActivity.this);
                return false;
            }
        });



        CustomView view1 = new CustomView(this);
        //view1.setCircle(_circle);


        view1.requestLayout();
        view1.setBackgroundColor(Color.GREEN);

        zoomLinearLayout.addView(view1);
    }

    public void get_json(){
        String json;
        try {

            InputStream is = getAssets().open("coords.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read();
            is.close();
            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            System.out.println("Circle x : "+_circle.getX());
            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("type").equals("circle"))
                {
                   //Circle circle = new Circle(obj.getInt("x"),obj.getInt("y"),obj.getInt("radius"));
                    Circle circle = new Circle(10,10,10);
                    System.out.println("Circle x : "+circle.getX());
                    circleArrayList.add(circle);
                    _circle.setX(circle.getX());
                    _circle.setY(circle.getY());
                    _circle.setRadius(circle.getRadius());
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}