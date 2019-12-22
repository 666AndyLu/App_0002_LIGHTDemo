package com.example.andylu.app_0002_lightdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button mLightButton = null;
    boolean flashing = false;
    final private int LED_NOTIFICATION_ID = 123;

    private Handler mLightHandler = new Handler();
    private LightRunable mLightRunable = new LightRunable();
    class LightRunable implements Runnable{
        @Override
        public void run() {
            if(flashing){
                FlashingLight();
            }
            else{
                ClearLED();
            }
        }
    }
    private void FlashingLight()
    {
        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        Notification notif = new Notification();
        notif.flags = Notification.FLAG_SHOW_LIGHTS;
        notif.ledARGB = 0xFF0000ff;
        notif.ledOnMS = 100;
        notif.ledOffMS = 100;
        nm.notify(LED_NOTIFICATION_ID, notif);
    }
    private void ClearLED()
    {
        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        nm.cancel(LED_NOTIFICATION_ID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLightButton = (Button)findViewById(R.id.button);
        mLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashing = !flashing;
                if(flashing){
                    mLightButton.setText("Stop Flashing the Light");
                }
                else{
                    mLightButton.setText("Flashing Light 20s");
                }
                mLightHandler.postDelayed(mLightRunable,20000);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
