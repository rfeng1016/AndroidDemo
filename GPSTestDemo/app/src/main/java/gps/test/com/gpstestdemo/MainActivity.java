package gps.test.com.gpstestdemo;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button_one,button_two,button_three;
    TextView textview_result;
    //提供系统定位服务
    LocationManager lm;
    // 存放LocationProvider名称的集合
    List<String> pNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        bindViews();
    }

    private void bindViews() {
        textview_result = (TextView) findViewById(R.id.tv_result);
        button_one = (Button) findViewById(R.id.button1);
        button_two = (Button) findViewById(R.id.button2);
        button_three = (Button) findViewById(R.id.button3);

        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                pNames.clear();
                pNames = lm.getAllProviders();
                textview_result.setText(getProvider());
                break;
            case R.id.button2:
                pNames.clear();
                Criteria criteria = new Criteria();
                //免费
                criteria.setCostAllowed(false);
                //能够提供高度信息
                criteria.setAltitudeRequired(false);
                //能够提供方向信息
                criteria.setBearingRequired(false);
                pNames = lm.getProviders(criteria,true);
                textview_result.setText(getProvider());
                break;
            case R.id.button3:
                pNames.clear();
                //指定名称
                pNames.add(lm.getProvider(LocationManager.GPS_PROVIDER).getName());
                textview_result.setText(getProvider());
                break;
        }
    }

    //遍历数组返回字符串的方法
    private String getProvider(){
        StringBuilder sb = new StringBuilder();
        for (String s : pNames) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }
}















































