package com.example.experiment_7;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mLight;
    TextView sensorData;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorData = findViewById(R.id.sensorData);
        //获取传感器服务的实例，并使用它来获取光度传感器的实例
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //运行时判断设备是否有光度传感器
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null) {
            Toast.makeText(SensorActivity.this, "此设备无光度传感器!", Toast.LENGTH_LONG);
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度发生变化时
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        //光线传感器返回单一的值
        float lux = event.values[0];
        sensorData.setText(lux + " lux");
    }

    @Override
    protected void onResume() {
        //注册传感器监听
        super.onResume();
        //SENSOR_DELAY_NORMAL为200000微秒
        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        //取消注册传感器监听(屏幕关闭时系统不会自动禁用传感器)
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
