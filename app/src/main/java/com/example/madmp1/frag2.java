package com.example.madmp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class frag2 extends Fragment {
    private MapView mapView;
    private GoogleMap googleMap;
    TextView emaildial,phonedial;
    public frag2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);
        emaildial = view.findViewById(R.id.emaildial);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this::onMapReady);
        phonedial = view.findViewById(R.id.phonedial);

        emaildial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + Uri.encode("BlitzStore@gmail.com")));
                intent.putExtra(Intent.EXTRA_EMAIL,"BlitzStore@gmail.com");
                startActivity(intent);
            }
        });
        phonedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialphone = new Intent(Intent.ACTION_DIAL);
                dialphone.setData(Uri.parse("tel:"+"9820203538"));
                startActivity(dialphone);
            }
        });

        return view;
    }
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Add a marker to a specific location
        LatLng location = new LatLng(45.775552103937144, 4.8252123171337695);

        MarkerOptions markerOptions = new MarkerOptions().position(location).title("Blitz Store");
        Marker marker = googleMap.addMarker(markerOptions);
        marker.setTag("Blitz Store");
        marker.showInfoWindow();
//        googleMap.addMarker(new MarkerOptions().position(location).title("Blitz Store").snippet("Blitz Store"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
