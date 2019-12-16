package sensor.test.com.sensortest1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //准备显示信息的UI组建
        TextView TV_Sensor = (TextView) findViewById(R.id.TextView_Sensor);

        //从系统服务中获得传感器管理器
        SensorManager SM_Sensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //从传感器管理器中获得全部的传感器列表
        List<Sensor> allSensors = SM_Sensor.getSensorList(Sensor.TYPE_ALL);

        //显示有多少个传感器
        TV_Sensor.setText("该HUD有" + allSensors.size() + "个传感器，它们分别是：\n");

        //显示每个传感器的具体信息
        for (Sensor s : allSensors) {
            String tempString = "\n" + "设备名称：" + s.getName() + "\n" + "设备版本:" + s.getVersion() + "\n" + "供应商：" + s.getVendor() + "\n";
            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "加速度传感器（Accelerometer Sensor）" + tempString);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "陀螺仪传感器（Gyroscope Sensor）" + tempString);
                    break;
                case Sensor.TYPE_LIGHT:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "环境光线传感器（Light Sensor）" + tempString);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "磁场传感器（Magnetic field Sensor）" + tempString);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "方向传感器（Orientation Sensor）" + tempString);
                    break;
                case Sensor.TYPE_PRESSURE:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "压力传感器（Pressure Sensor）" + tempString);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "距离传感器（Proximity Sensor）" + tempString);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "温度传感器（Temperature Sensor）" + tempString);
                    break;
                default:
                    TV_Sensor.setText(TV_Sensor.getText().toString() + s.getType() + "未知传感器" + tempString);
                    break;
            }
    }}}


