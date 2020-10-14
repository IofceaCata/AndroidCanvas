package menu.myco.canvasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {


    ArrayList<Circle> circleArrayList = new ArrayList<>();
    ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();

    //Circle _circle = new Circle(100,10,100f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        get_json();

        final ZoomLinearLayout zoomLinearLayout = (ZoomLinearLayout) findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zoomLinearLayout.init(MyActivity.this);
                return false;
            }
        });



        CustomView view1 = new CustomView(this,circleArrayList , rectangleArrayList);



        view1.requestLayout();
        view1.setBackgroundColor(Color.GREEN);

        zoomLinearLayout.addView(view1);
    }

    public void get_json(){

        try {
            JSONObject obj1 = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = obj1.getJSONArray("coords");

            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("type").equals("circle"))
                {
                    Circle circle = new Circle(obj.getInt("x"),obj.getInt("y"),obj.getInt("radius"));
                    circleArrayList.add(circle);
                }

                if(obj.getString("type").equals("rectangle"))
                {
                    Rectangle rectangle = new Rectangle(obj.getInt("left"),obj.getInt("top"),obj.getInt("right"),obj.getInt("bottom"));
                    rectangleArrayList.add(rectangle);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("coords.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}