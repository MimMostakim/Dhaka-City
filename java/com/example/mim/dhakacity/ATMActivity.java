package com.example.mim.dhakacity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mim.dhakacity.nearby.NearbyPlaceResponse;
import com.example.mim.dhakacity.nearby.Result;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ATMActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean isLocationEnabled = false;
    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        checkLocationPermission();
    }



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

        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));

        if (isLocationEnabled) {
            mMap.setMyLocationEnabled(true);
        }

        getNearbyPlace();
    }

    private void getNearbyPlace() {
        NearbyPlaceService placeService = RetrofitClient.getClient().create(NearbyPlaceService.class);
        String apikey = "AIzaSyDnKH3oLK7Ucv7XZZBYixTH-o66TihY_7Y";
        String endUrl = String.format("place/nearbysearch/json?location=23.754741, 90.376709&radius=1500&type=atm&key=%s",apikey);


       placeService.getNearbyPlaceResponse(endUrl)
               .enqueue(new Callback<NearbyPlaceResponse>() {
                   @Override
                   public void onResponse(Call<NearbyPlaceResponse> call, Response<NearbyPlaceResponse> response) {
                       if (response.isSuccessful()){
                           NearbyPlaceResponse nearbyPlaceResponse=
                                   response.body();
                          List<Result> resultList = nearbyPlaceResponse.getResults();

                          for (Result result: resultList){
                              double lat =result.getGeometry().getLocation().getLat();
                              double lng =result.getGeometry().getLocation().getLng();
                              String name=result.getName();

                              LatLng latLng = new LatLng(lat,lng);
                              mMap.addMarker(new MarkerOptions().position(latLng).title(name));
                          }
                       }
                   }

                   @Override
                   public void onFailure(Call<NearbyPlaceResponse> call, Throwable t) {

                       Log.e("failed", t.getLocalizedMessage());

                   }
               });


    }


    private  void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                    , 111);
        } else {
            isLocationEnabled = true;
        }
    }
}
