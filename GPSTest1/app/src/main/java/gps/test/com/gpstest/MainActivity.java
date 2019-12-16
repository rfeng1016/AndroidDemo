package gps.test.com.gpstest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView textview_show;
    LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_show = (TextView) findViewById(R.id.TextView_Show);

        //获取系统LocationManager服务
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //从GPS获取最近的定位信息
        Location location = lm.getLastKnownLocation(lm.GPS_PROVIDER);

        //设置每2秒获取一次GPS的定位信息
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //当GPS定位信息发生改变时，更新位置
                updateShow(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                //当GPS LocationProvider可用时，更新位置
                updateShow(lm.getLastKnownLocation(provider));
            }

            @Override
            public void onProviderDisabled(String provider) {
                updateShow(null);
            }
        });



    }

    //定义一个更新显示的方法
    private void updateShow(Location location) {
        if(location != null){
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("当前的位置信息：\\n");
            strBuilder.append("精度：" + location.getLongitude() + "\n");
            strBuilder.append("纬度：" + location.getLatitude() + "\n");
            strBuilder.append("高度：" + location.getAltitude() + "\n");
            strBuilder.append("速度：" + location.getSpeed() + "\n");
            strBuilder.append("方向：" + location.getBearing() + "\n");
            strBuilder.append("定位精度：" + location.getAccuracy() + "\n");
            textview_show.setText(strBuilder.toString());
        }else
            textview_show.setText("");
    }





}












































































