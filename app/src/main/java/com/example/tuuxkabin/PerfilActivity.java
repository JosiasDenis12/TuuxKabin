package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);  // Mejor cambiar el nombre a activity_profile.xml


        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        // Establecer el ítem "Perfil" como seleccionado por defecto
        bottomNavigation.setSelectedItemId(R.id.nav_perfil);
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
                return true; // Cambiado a true
            }
            return false;
        });
    }
}