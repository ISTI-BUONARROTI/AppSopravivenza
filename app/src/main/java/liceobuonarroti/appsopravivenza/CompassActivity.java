package liceobuonarroti.appsopravivenza;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CompassActivity extends AppCompatActivity  implements SensorEventListener {

    private ImageView imgCompass;
    private SensorManager sensorManager;
    private TextView txtDegrees;
    private float currentDegree;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        imgCompass=(ImageView)findViewById(R.id.imgCompass);
        txtDegrees=(TextView)findViewById(R.id.txtDegrees);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }



    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float degree=Math.round(event.values[0]);
        txtDegrees.setText("Rotation: "+Float.toString(degree)+" degrees");
        RotateAnimation ra=new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(120);
        ra.setFillAfter(true);
        imgCompass.startAnimation(ra);
        currentDegree=-degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
