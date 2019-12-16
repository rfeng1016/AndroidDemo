package gps.test.com.gpstest2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.app.Activity;
import android.os.Bundle;
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
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!isGpsAble(lm)) {
            Toast.makeText(MainActivity.this, "请打开GPS~", Toast.LENGTH_SHORT).show();
            openGPS2();
        }


        //从GPS获取最近的定位信息

        if(getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION",getPackageName()) == PackageManager.PERMISSION_DENIED) {
            Location lc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updateShow(lc);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //当GPS定位信息发生改变时，更新定位
                    updateShow(location);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    //当GPS LocationProvider可用时，更新定位
                    updateShow(lm.getLastKnownLocation(provider));
                }

                @Override
                public void onProviderDisabled(String provider) {
                    updateShow(null);
                }
            });
        }


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

    //打开设置页面让用户自己设置
    private void openGPS2() {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivityForResult(intent,0);
    }

    private boolean isGpsAble(LocationManager lm) {
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ? true : false;
    }



}
