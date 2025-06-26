package com.example.tuuxkabin;

import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.tuuxkabin.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Obtenemos el modo (INDIVIDUAL o TODOS)
        String modo = getIntent().getStringExtra("MODO");

        if (modo != null && modo.equals("TODOS")) {
            // ===== MODO TODOS LOS CENOTES =====
            Map<String, LatLng> cenotes = new HashMap<>();
            cenotes.put("Cenote Zaci", new LatLng(20.691737, -88.197337));
            cenotes.put("Cenote Suytun", new LatLng(20.699012, -88.124563));
            cenotes.put("Cenote Chukum", new LatLng(20.720602, -88.075922));
            cenotes.put("Cenote X'ux Ha", new LatLng(20.687429, -88.057625));
            cenotes.put("Cenote Saamal", new LatLng(20.676493, -88.242606));
            cenotes.put("Cenote Xlakaj", new LatLng(20.628525, -88.195848));
            cenotes.put("Cenote Xkekén", new LatLng(20.660850, -88.242632));
            cenotes.put("Cenote Samula", new LatLng(20.662794, -88.244820));
            cenotes.put("Hacienda Cenote Oxmán", new LatLng(20.667713, -88.230134));
            cenotes.put("Cenote Noolha by Chichikan", new LatLng(20.708881, -88.206323));

            LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
            for (Map.Entry<String, LatLng> entry : cenotes.entrySet()) {
                mMap.addMarker(new MarkerOptions()
                        .position(entry.getValue())
                        .title(entry.getKey()));
                boundsBuilder.include(entry.getValue());
            }

            // Ajustar la cámara para mostrar todos
            try {
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 100));
            } catch (Exception e) {
                e.printStackTrace();
                // En caso de error, centrar en Valladolid
                LatLng valladolid = new LatLng(20.689381, -88.201401);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(valladolid, 12));
            }
        } else {
            // ===== MODO INDIVIDUAL =====
            String nombreCenote = getIntent().getStringExtra("NOMBRE_CENOTE");
            LatLng ubicacion = obtenerUbicacionCenote(nombreCenote);

            if (ubicacion != null) {
                mMap.addMarker(new MarkerOptions()
                        .position(ubicacion)
                        .title(nombreCenote));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
            } else {
                // Si no se encuentra el cenote, centrar en Valladolid
                LatLng valladolid = new LatLng(20.689381, -88.201401);
                mMap.addMarker(new MarkerOptions()
                        .position(valladolid)
                        .title("Valladolid"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(valladolid, 12));
            }
        }
    }

    private LatLng obtenerUbicacionCenote(String nombreCenote) {
        switch (nombreCenote) {
            case "Cenote Zaci":
                return new LatLng(20.691737, -88.197337);
            case "Cenote Suytun":
                return new LatLng(20.699012, -88.124563);
            case "Cenote Chukum":
                return new LatLng(20.720602, -88.075922);
            case "Cenote X'ux Ha":
                return new LatLng(20.687429, -88.057625);
            case "Cenote Saamal":
                return new LatLng(20.676493, -88.242606);
            case "Cenote Xlakaj":
                return new LatLng(20.628525, -88.195848);
            case "Cenote Xkekén":
                return new LatLng(20.660850, -88.242632);
            case "Cenote Samula":
                return new LatLng(20.662794, -88.244820);
            case "Hacienda Cenote Oxmán":
                return new LatLng(20.667713, -88.230134);
            case "Cenote Noolha by Chichikan":
                return new LatLng(20.708881, -88.206323);
            default:
                return null;
        }
    }
}