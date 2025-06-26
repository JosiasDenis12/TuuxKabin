package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View;


public class ReservatranspActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Transporte> transporteList;
    private TransporteAdapter adapter;

    private TextView tvServiciosDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_transporte);

        EditText etSearchLocation = findViewById(R.id.etSearchLocation);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewTransportes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvServiciosDisponibles = findViewById(R.id.tvServiciosDisponibles);

        // Inicializar lista de transporte
        transporteList = new ArrayList<>();
        transporteList.add(new Transporte(
                "Radio Taxi Amarillos", 5.0f, 10,
                "Servicio las 24 horas", "+529981234567", R.drawable.taxi_amarillo,
                35, 50, 150, 300, 800, 1000
        ));
        transporteList.add(new Transporte(
                "Radio Taxi Gavilanes", 4.0f, 6,
                "Servicio las 24 horas", "+529982345678", R.drawable.taxi_gavilanes,
                40, 60, 200, 350, 900, 1100
        ));
        transporteList.add(new Transporte(
                "Radio Taxi A.A.O", 3.5f, 4,
                "Servicio las 24 horas", "+529983456789", R.drawable.taxi_duvalin,
                30, 45, 180, 320, 850, 1050
        ));

        // Configurar adaptador
        adapter = new TransporteAdapter(transporteList);
        recyclerView.setAdapter(adapter);

        // Buscar taxis
        etSearchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTransportes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Navegación inferior
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_inicio) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.nav_servicios) {
                // Ir a ServiciosActivity
                Intent intent = new Intent(this, LoginPageActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_explorar) {
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra("MODO", "TODOS"); // Indicamos que es modo "todos"
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_perfil) {
                Intent intent = new Intent(ReservatranspActivity.this, PerfilActivity.class);
                startActivity(intent);
                return true; // Cambiado a true
            }
            return false;
        });
    }

    private void filterTransportes(String texto) {
        texto = texto.toLowerCase();
        List<Transporte> listaFiltrada = new ArrayList<>();

        for (Transporte transporte : transporteList) {
            if (transporte.getNombre().toLowerCase().contains(texto)) {
                listaFiltrada.add(transporte);
            }
        }

        adapter.setFilteredList(listaFiltrada);

        // Control de encabezado
        if (texto.isEmpty()) {
            tvServiciosDisponibles.setText("Servicios Disponibles");
            tvServiciosDisponibles.setVisibility(View.VISIBLE);
        } else if (!listaFiltrada.isEmpty()) {
            tvServiciosDisponibles.setText("Servicios encontrados");
            tvServiciosDisponibles.setVisibility(View.VISIBLE);
        } else {
            tvServiciosDisponibles.setText("No se encontró");
            tvServiciosDisponibles.setVisibility(View.VISIBLE);
        }
    }


    private void abrirDetalleTaxi(Transporte transporte) {
        try {
            Intent intent = new Intent(this, DetalleTaxiActivity.class);
            intent.putExtra("transporte", transporte);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir detalles", Toast.LENGTH_SHORT).show();
        }
    }
}