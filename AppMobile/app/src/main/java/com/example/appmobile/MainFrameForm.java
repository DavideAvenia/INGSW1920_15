package com.example.appmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appmobile.controller.ControllerLogin;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainFrameForm extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static boolean isLogged; //mantiene l'info sullo stato di login dell'utente
    private Menu menu;
    private LocationManager locationManager = null;


    //Controllers
    private ControllerLogin controllerLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        isLogged = false;
        controllerLogin = ControllerLogin.getControllerLogin();
    }

    private final LocationListener LOCATION_LISTENER = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            LatLng currenLocation = new LatLng(location.getLatitude(), location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Controlla se il gps è abilitato
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            enableGpsMessage();
        }

        LatLng defaultLocation = new LatLng(40.863,14.2767);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation,10.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.login:
                if(isLogged == false){
                     controllerLogin.mostraLoginForm(this); //apre finestra di login
                }else{
                    //signout
                    Toast.makeText(this,"Logout effettuato",Toast.LENGTH_LONG).show();
                    setIsLogged(false);
                }return true;
            case R.id.cerca:
                //implementare la ricerca
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableGpsMessage(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Il gps non è abilitato, vuoi abilitarlo?").setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void setIsLogged(boolean value){
        isLogged = value;
    }
}
