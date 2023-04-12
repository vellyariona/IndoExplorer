package com.example.kompas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CompassActivity";
    private Compass compass;
    private ImageView arrowView;
    private float currentAzimuth;


    TextView showPlace;
    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;
    LinearLayout mLayout;


//    private SensorManager mSensorManager;
//    private Sensor mSensorHumidity;
//    private Sensor mSensorTemperature;
//    private TextView mTextSensorHumidity;
//    private TextView mTextSensorTemperature;

//    buttonfragment
    ImageButton mButton;
    private View fragment;
    private boolean isFragmentDisplayed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arrowView = findViewById(R.id.main_image_dial);
        setupCompass();

        mButton = findViewById(R.id.showPlace);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });
    }
//    public void displayFragment() {
//        // Memulai transaksi
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        // mengganti isi container dengan fragment baru
//        ft.replace(R.id.your_placeholder, new MapsFragment());
//        // atau ft.add(R.id.your_placeholder, new Sensor());
//        // mulai melakukan hal di atas (jika belum di commit maka proses di atas belum dimulai)
//        ft.commit();
//        isFragmentDisplayed = true;
//        mButton.setText("Tutup Maps");
//    }

    public void displayFragment(){
        PlacesListFragment showMoreFragment = PlacesListFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.your_placeholder, showMoreFragment).addToBackStack(null).commit();
//        showPlace.setText("Sembunyikan");
        isFragmentDisplayed = true;
    }

    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        PlacesListFragment showMoreFragment = (PlacesListFragment) fragmentManager
                .findFragmentById(R.id.your_placeholder);
        if (showMoreFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(showMoreFragment).commit();
        }
//        showPlace.setText("Tampilkan");
        isFragmentDisplayed = false;
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    private void setupCompass() {
        compass = new Compass(this);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(final float azimuth) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adjustArrow(azimuth);
                    }
                });
            }
        };
    }

    private void adjustArrow(float azimuth) {
        Log.d(TAG, "will set rotation from " + currentAzimuth + " to "
                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowView.startAnimation(an);
    }


}