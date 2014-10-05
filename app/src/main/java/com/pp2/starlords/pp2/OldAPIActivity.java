package com.pp2.starlords.pp2;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.location.Location;


public class OldAPIActivity extends Activity implements LocationListener, View.OnClickListener {

    private TextView GPS_latituteField;
    private TextView GPS_longitudeField;
    private TextView Network_latituteField;
    private TextView Network_longitudeField;
    private LocationManager locationManager;
    private double GPS_latitude = 0;
    private double GPS_longitude = 0;
    private double network_latitude = 0;
    private double network_longitude = 0;
    private LocationListener GPSListener;
    private LocationListener NetworkListener;
    private Button btnGPS;
    private Button btnNetwork;
    private static int TIME_INTERVAL = 5000;
    private static int SPACE_INTERVAL = 1;
    private FileLogger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_api);

        logger = new FileLogger("readingsOldAPI.log", getApplicationContext());

        GPS_latituteField = (TextView) findViewById(R.id.txt_GPS_latitude);
        GPS_longitudeField = (TextView) findViewById(R.id.txt_GPS_longitude);
        Network_latituteField = (TextView) findViewById(R.id.txt_Network_latitude);
        Network_longitudeField = (TextView) findViewById(R.id.txt_Network_longitude);
        btnGPS = (Button) this.findViewById(R.id.btn_GPS);
        btnNetwork = (Button) this.findViewById(R.id.btn_network);
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            GPS_latituteField.setText("Location not available");
            GPS_longitudeField.setText("Location not available");
        }
        btnGPS.setOnClickListener(this);
        btnNetwork.setOnClickListener(this);

    }

    private void redrawLocations(){
        GPS_latituteField.setText(String.valueOf(GPS_latitude));
        GPS_longitudeField.setText(String.valueOf(GPS_longitude));
        Network_latituteField.setText(String.valueOf(network_latitude));
        Network_longitudeField.setText(String.valueOf(network_longitude));
    }

    public void onClick(View v) {
        if (v!= null && v.getId() == R.id.btn_GPS) {
            // GPS button pressed
            if (GPSListener == null) {
                // must turn on GPS tracking
                GPSListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        OldAPIActivity.this.GPS_latitude = location.getLatitude();
                        OldAPIActivity.this.GPS_longitude = location.getLongitude();
                        OldAPIActivity.this.redrawLocations();
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };
                this.locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,TIME_INTERVAL, SPACE_INTERVAL, GPSListener);
                btnGPS.setText(R.string.btn_GPS_on);
            } else {
                // must turn GPS location off
                this.locationManager.removeUpdates(GPSListener);
                this.GPSListener = null;
                btnGPS.setText(R.string.btn_GPS_off);
            }
        } else if (v.getId() == R.id.btn_network) {
            // Network button pressed
            if (NetworkListener == null) {
                // must turn on Network tracking
                NetworkListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        OldAPIActivity.this.network_latitude = location.getLatitude();
                        OldAPIActivity.this.network_longitude = location.getLongitude();
                        OldAPIActivity.this.redrawLocations();
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };
                this.locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER,TIME_INTERVAL, SPACE_INTERVAL, NetworkListener);
                btnNetwork.setText(R.string.btn_Network_on);
            } else {
                // must turn Network location off
                this.locationManager.removeUpdates(NetworkListener);
                this.NetworkListener = null;
                btnNetwork.setText(R.string.btn_Network_off);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        network_latitude = (int) (location.getLatitude());
        network_longitude = (int) (location.getLongitude());
        GPS_latituteField.setText(String.valueOf(network_latitude));
        GPS_longitudeField.setText(String.valueOf(network_longitude));

        logger.write(    
                network_latitude + "," +
                network_longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
