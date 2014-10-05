package com.pp2.starlords.pp2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.location.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class OldAPIActivity extends Activity implements LocationListener {

    private LocationManager locationManager;
    private static final long TIME_INTERVAL = 5000;
    private static final float SPACE_INTERVAL = 0;
    private GoogleMap googleMap;

    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileLogger.initFile(getApplicationContext(), "readingsOldAPI.log");
        setContentView(R.layout.activity_old_api);
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new OldAPIViewFragment())
                    .commit();
        }
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseGps(View view) {
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,TIME_INTERVAL, SPACE_INTERVAL, this);
    }

    public void chooseWifi(View view) {
        this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,TIME_INTERVAL, SPACE_INTERVAL, this);
    }


    @Override
    public void onLocationChanged(Location location) {
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        LatLng currentP = new LatLng(location.getLatitude(), location.getLongitude());

        if (currentMarker == null) // to only animate on first location update
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentP, 20.0f));


        currentMarker = googleMap.addMarker(new MarkerOptions().
                position(currentP).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_red)));

        FileLogger.write(
                location.getLatitude() + "," +
                        location.getLongitude(), "readingsOldAPI.log", getApplicationContext());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(this, "New provider " + provider,
                Toast.LENGTH_SHORT).show();
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

    public static class OldAPIViewFragment extends Fragment {

        public OldAPIViewFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_old_api, container, false);
            return rootView;
        }
    }
}
