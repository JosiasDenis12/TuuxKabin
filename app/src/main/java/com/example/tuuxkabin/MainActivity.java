package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
    // Declaración de variables para las tarjetas de cenotes

    private TextView tvCenotesPopulares, tvRecomendados;
    private CardView cardCenoteZaci, cardCenoteSuytun, cardCenoteXkeken, cardCenoteSamula;
    private EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        inicializarComponentes();

        // Configurar listeners para las tarjetas de cenotes
        configurarListenersTarjetas();

        configurarBarraBusqueda();

        // Set the background image for the header
        View headerContainer = findViewById(R.id.headerContainer);
        headerContainer.setBackgroundResource(R.drawable.principal);

        /*  Configuración del botón existente
        View pressButton = findViewById(R.id.pressButton);
        if (pressButton != null) {
            pressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.animate()
                            .scaleX(0.95f)
                            .scaleY(0.95f)
                            .setDuration(100)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate()
                                            .scaleX(1f)
                                            .scaleY(1f)
                                            .setDuration(100);
                                }
                            });
                }
            });
        } */

        // Add click listeners for the new navigation buttons
        setupNavigationButtons();

        // Configuración del Bottom Navigation

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        if (bottomNavigation != null) {
            bottomNavigation.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_inicio) {
                    // Ya estamos en MainActivity
                    return true;
                } else if (itemId == R.id.nav_servicios) {
                    // Ir a ServiciosActivity
                    Intent intent = new Intent(MainActivity.this, LoginPageActivity.class);
                    startActivity(intent);
                    return true; // Cambiado a true
                } else if (itemId == R.id.nav_explorar) {
                    Intent intent = new Intent(this, MapsActivity.class);
                    intent.putExtra("MODO", "TODOS");
                    startActivity(intent);
                    return true; // Cambiado a true
                } else if (itemId == R.id.nav_perfil) {
                    Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                    startActivity(intent);
                    return true; // Cambiado a true
                }
                return false;
            });

            // Establecer Inicio como seleccionado por defecto
            bottomNavigation.setSelectedItemId(R.id.nav_inicio);
        }

    }

    // Set up click listeners for the main navigation buttons
    private void setupNavigationButtons() {
        // Explorar Cenotes button
        View btnExplorarCenotes = findViewById(R.id.btnExplorarCenotes);
        if (btnExplorarCenotes != null) {
            btnExplorarCenotes.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, ExplorarCenotesActivity.class);
                startActivity(intent);
            });
        }

        // Reservar Transporte button
        View btnReservarTransporte = findViewById(R.id.btnReservarTransporte);
        if (btnReservarTransporte != null) {
            btnReservarTransporte.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, ReservatranspActivity.class);
                startActivity(intent);
            });
        }

        // Experiencias button
        View btnExperiencias = findViewById(R.id.btnExperiencias);
        if (btnExperiencias != null) {
            btnExperiencias.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, ServiciosActivity.class);
                startActivity(intent);
            });
        }

        // Login options buttons
        setupLoginButtons();
    }

    // Set up click listeners for login option buttons
    private void setupLoginButtons() {
        // Registro button
        View btnRegistro = findViewById(R.id.btnRegistro);
        if (btnRegistro != null) {
            btnRegistro.setOnClickListener(view -> {
                String url = "https://www.booking.com"; // replace with your actual URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }

        // Google button
        View btnGoogle = findViewById(R.id.btnGoogle);
        if (btnGoogle != null) {
            btnGoogle.setOnClickListener(view -> {
                String url = "https://www.google.com"; // replace with your actual URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }

        // Facebook button
        View btnFacebook = findViewById(R.id.btnFacebook);
        if (btnFacebook != null) {
            btnFacebook.setOnClickListener(view -> {
                String url = "https://www.facebook.com"; // replace with your actual URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }

        // Profile card
        View profileCard = findViewById(R.id.profileCard);
        if (profileCard != null) {
            profileCard.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
            });
        }

    }


    private void inicializarComponentes() {
        // Inicializar tarjetas de cenotes
        searchBar = findViewById(R.id.searchBar);
        tvCenotesPopulares = findViewById(R.id.tvCenotesPopularesTitle);
        tvRecomendados = findViewById(R.id.tvCenotesRecomendadosTitle);

        cardCenoteZaci = findViewById(R.id.cardCenoteZaci);
        cardCenoteSuytun = findViewById(R.id.cardCenoteSuytun);
        cardCenoteXkeken = findViewById(R.id.cardCenoteXkeken);
        cardCenoteSamula = findViewById(R.id.cardCenoteSamula);
    }

    private void configurarListenersTarjetas() {
        // Listener común para todas las tarjetas
        View.OnClickListener tarjetaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCenote = "";

                // Determinar el cenote seleccionado
                if (v.getId() == R.id.cardCenoteZaci) {
                    nombreCenote = "Cenote Zaci";
                } else if (v.getId() == R.id.cardCenoteSuytun) {
                    nombreCenote = "Cenote Suytun";
                } else if (v.getId() == R.id.cardCenoteXkeken) {
                    nombreCenote = "Cenote Xkekén";
                } else if (v.getId() == R.id.cardCenoteSamula) {
                    nombreCenote = "Cenote Samula";
                }

                // Iniciar la actividad de detalle del cenote
                if (!nombreCenote.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, DetalleCenoteActivity.class);
                    intent.putExtra("CENOTE_NOMBRE", nombreCenote); // Pasar el nombre del cenote
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Error: Cenote no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Asignar el listener a cada tarjeta
        cardCenoteZaci.setOnClickListener(tarjetaListener);
        cardCenoteSuytun.setOnClickListener(tarjetaListener);
        cardCenoteXkeken.setOnClickListener(tarjetaListener);
        cardCenoteSamula.setOnClickListener(tarjetaListener);
    }

    private void configurarBarraBusqueda() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarCenotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filtrarCenotes(String texto) {
        texto = texto.toLowerCase();

        boolean hayCoincidencias = false;

        // Verificar coincidencia para cada cenote
        if ("cenote zaci".contains(texto)) {
            cardCenoteZaci.setVisibility(View.VISIBLE);
            hayCoincidencias = true;
        } else {
            cardCenoteZaci.setVisibility(View.GONE);
        }

        if ("cenote suytun".contains(texto)) {
            cardCenoteSuytun.setVisibility(View.VISIBLE);
            hayCoincidencias = true;
        } else {
            cardCenoteSuytun.setVisibility(View.GONE);
        }

        if ("cenote xkekén".contains(texto) || "cenote xkeken".contains(texto)) {
            cardCenoteXkeken.setVisibility(View.VISIBLE);
            hayCoincidencias = true;
        } else {
            cardCenoteXkeken.setVisibility(View.GONE);
        }

        if ("cenote samula".contains(texto)) {
            cardCenoteSamula.setVisibility(View.VISIBLE);
            hayCoincidencias = true;
        } else {
            cardCenoteSamula.setVisibility(View.GONE);
        }

        // Mostrar encabezados apropiados
        if (texto.isEmpty()) {
            tvCenotesPopulares.setText("Cenotes Populares");
            tvCenotesPopulares.setVisibility(View.VISIBLE);
            tvRecomendados.setVisibility(View.VISIBLE);
        } else {
            tvRecomendados.setVisibility(View.GONE);
            tvCenotesPopulares.setVisibility(View.VISIBLE);

            if (hayCoincidencias) {
                tvCenotesPopulares.setText("Cenotes Encontrados");
            } else {
                tvCenotesPopulares.setText("No se encontró");

                // Ocultar todos los cenotes si no hay coincidencias
                cardCenoteZaci.setVisibility(View.GONE);
                cardCenoteSuytun.setVisibility(View.GONE);
                cardCenoteXkeken.setVisibility(View.GONE);
                cardCenoteSamula.setVisibility(View.GONE);
            }
        }
    }


}