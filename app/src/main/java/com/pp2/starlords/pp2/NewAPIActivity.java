package com.pp2.starlords.pp2;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import 	android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class NewAPIActivity extends Activity implements
        ConnectionCallbacks,
        OnConnectionFailedListener,
        LocationListener {

    private LocationClient mLocationClient;

    // Update frequency in milliseconds
    private static final long UPDATE_INTERVAL = 5000;
    // A fast frequency ceiling in milliseconds
    private static final long FASTEST_INTERVAL = 1000;
    // Define an object that holds accuracy and frequency parameters
    LocationRequest mLocationRequest;

    private GoogleMap googleMap;

    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileLogger.initFile(getApplicationContext(), "readingsNewAPI.log");
        setContentView(R.layout.activity_new_api);
        mLocationClient = new LocationClient(this, this, this);
        createLocationRequest();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new NewAPIViewFragment())
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

    private void createLocationRequest() {
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); //Allow this to change
        //Set the update interval
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
    }



    /*
     * Called when the Activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }

    /*
    * Called when the Activity is no longer visible.
    */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        Toast.makeText(this, "Connected Loc Client", Toast.LENGTH_SHORT).show();
        mLocationClient.requestLocationUpdates(mLocationRequest, this);
    }

    @Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected Loc Client",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Display the connection status
        Toast.makeText(this, "Failed conn to Loc Cli",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        // Report to the UI that the location was updated
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());

        LatLng currentP = new LatLng(location.getLatitude(), location.getLongitude());

        if (currentMarker == null) // to only animate on first location update
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentP, 20.0f));


        currentMarker = googleMap.addMarker(new MarkerOptions().
                position(currentP).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_red)));

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();



        FileLogger.write(
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude()), "readingsNewAPI.log", getApplicationContext());

        FileLogger.parseFile("readingsNewAPI.log", getApplicationContext());
    }

    public void useLowBattery(View view) {
        mLocationClient.disconnect();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        Toast.makeText(this, "Low Battery enabled",
                Toast.LENGTH_SHORT).show();
        mLocationClient.connect();

    }

    public void useMediumBattery(View view) {
        mLocationClient.disconnect();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        Toast.makeText(this, "Medium Battery enabled",
                Toast.LENGTH_SHORT).show();
        mLocationClient.connect();
    }

    public void useHighAccuracy(View view) {
        mLocationClient.disconnect();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        Toast.makeText(this, "High Accuracy enabled",
                Toast.LENGTH_SHORT).show();
        mLocationClient.connect();
    }

    public static class NewAPIViewFragment extends Fragment {

        public NewAPIViewFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new_api, container, false);
            return rootView;
        }
    }
}
