package gps.test.com.gpstes3;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    // 定义LocationManager对象
    private LocationManager locationManager;
    private EditText show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (EditText) findViewById(R.id.main_et_show);
        // 获取系统LocationManager服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 从GPS获取最近的定位信息
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // 将location里的位置信息显示在EditText中
        updateView(location);
        // 设置每2秒获取一次GPS的定位信息
    }
}
