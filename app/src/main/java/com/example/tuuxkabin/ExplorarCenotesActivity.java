package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExplorarCenotesActivity extends AppCompatActivity {

    private TextView tvCenotesCercanos, tvCenotesPopulares, tvRecomendados;

    private EditText searchBar;


    // Declaración de variables para las tarjetas de cenotes
    private CardView cardCenoteZaci, cardCenoteSuytun, cardCenoteChukum, cardCenoteXuxha,
            cardCenoteSaamal, cardCenoteXkeken, cardCenoteXlakaj, cardCenoteOxman, cardCenoteNoolha, cardCenoteSamula;

    // Declaración de variables para los botones de favoritos
    private ImageView favZaci, favSuytun, favChukum, favXuxha, favSaamal, favXkeken, favXlakaj, favOxman, favNoolha, favSamula;

    // Arreglos para almacenar el estado de favorito de cada cenote
    private boolean[] esFavorito = new boolean[10]; // Incluye el Cenote Samula

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorar_cenotes);

        // Inicializar componentes
        inicializarComponentes();

        // Configurar listeners para las tarjetas de cenotes
        configurarListenersTarjetas();

        // Configurar listeners para botones de favoritos
        configurarListenersFavoritos();

        // Configurar barra de navegación inferior
        configurarBottomNavigation();

        configurarBarraBusqueda();


    }

    private void inicializarComponentes() {
        searchBar = findViewById(R.id.searchBar);
        tvCenotesCercanos = findViewById(R.id.tvCenotesCercanos);
        tvCenotesPopulares = findViewById(R.id.tvCenotesPopulares);
        tvRecomendados = findViewById(R.id.tvRecomendados);


        // Inicializar tarjetas de cenotes
        cardCenoteZaci = findViewById(R.id.cardCenoteZaci);
        cardCenoteSuytun = findViewById(R.id.cardCenoteSuytun);
        cardCenoteChukum = findViewById(R.id.cardCenoteChukum);
        cardCenoteXuxha = findViewById(R.id.cardCenoteXuxha);
        cardCenoteSaamal = findViewById(R.id.cardCenoteSaamal);
        cardCenoteXkeken = findViewById(R.id.cardCenoteXkeken);
        cardCenoteXlakaj = findViewById(R.id.cardCenoteXlakaj);
        cardCenoteOxman = findViewById(R.id.cardCenoteOxman);
        cardCenoteNoolha = findViewById(R.id.cardCenoteNoolha);
        cardCenoteSamula = findViewById(R.id.cardCenoteSamula);

        // Inicializar botones de favoritos
        favZaci = findViewById(R.id.favZaci);
        favSuytun = findViewById(R.id.favSuytun);
        favChukum = findViewById(R.id.favChukum);
        favXuxha = findViewById(R.id.favXuxha);
        favSaamal = findViewById(R.id.favSaamal);
        favXkeken = findViewById(R.id.favXkeken);
        favXlakaj = findViewById(R.id.favXlakaj);
        favOxman = findViewById(R.id.favOxman);
        favNoolha = findViewById(R.id.favNoolha);
        favSamula = findViewById(R.id.favSamula);

        // Inicializar todos los cenotes como no favoritos
        for (int i = 0; i < esFavorito.length; i++) {
            esFavorito[i] = false;
        }
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
                } else if (v.getId() == R.id.cardCenoteChukum) {
                    nombreCenote = "Cenote Chukum";
                } else if (v.getId() == R.id.cardCenoteXuxha) {
                    nombreCenote = "Cenote X'ux Ha";
                } else if (v.getId() == R.id.cardCenoteSaamal) {
                    nombreCenote = "Cenote Saamal";
                } else if (v.getId() == R.id.cardCenoteXkeken) {
                    nombreCenote = "Cenote Xkekén";
                } else if (v.getId() == R.id.cardCenoteXlakaj) {
                    nombreCenote = "Cenote Xlakaj";
                } else if (v.getId() == R.id.cardCenoteOxman) {
                    nombreCenote = "Hacienda Cenote Oxmán";
                } else if (v.getId() == R.id.cardCenoteNoolha) {
                    nombreCenote = "Cenote Noolha by Chichikan";
                } else if (v.getId() == R.id.cardCenoteSamula) {
                    nombreCenote = "Cenote Samula";
                }

                // Iniciar la actividad de detalle del cenote
                if (!nombreCenote.isEmpty()) {
                    Intent intent = new Intent(ExplorarCenotesActivity.this, DetalleCenoteActivity.class);
                    intent.putExtra("CENOTE_NOMBRE", nombreCenote); // Pasar el nombre del cenote
                    startActivity(intent);
                } else {
                    Toast.makeText(ExplorarCenotesActivity.this, "Error: Cenote no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Asignar el listener a cada tarjeta
        cardCenoteZaci.setOnClickListener(tarjetaListener);
        cardCenoteSuytun.setOnClickListener(tarjetaListener);
        cardCenoteChukum.setOnClickListener(tarjetaListener);
        cardCenoteXuxha.setOnClickListener(tarjetaListener);
        cardCenoteSaamal.setOnClickListener(tarjetaListener);
        cardCenoteXkeken.setOnClickListener(tarjetaListener);
        cardCenoteXlakaj.setOnClickListener(tarjetaListener);
        cardCenoteOxman.setOnClickListener(tarjetaListener);
        cardCenoteNoolha.setOnClickListener(tarjetaListener);
        cardCenoteSamula.setOnClickListener(tarjetaListener);
    }

    private void configurarListenersFavoritos() {
        // Listener común para los botones de favoritos
        View.OnClickListener favoritoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -1;

                if (v.getId() == R.id.favZaci) {
                    index = 0;
                } else if (v.getId() == R.id.favSuytun) {
                    index = 1;
                } else if (v.getId() == R.id.favChukum) {
                    index = 2;
                } else if (v.getId() == R.id.favXuxha) {
                    index = 3;
                } else if (v.getId() == R.id.favSaamal) {
                    index = 4;
                } else if (v.getId() == R.id.favXkeken) {
                    index = 5;
                } else if (v.getId() == R.id.favXlakaj) {
                    index = 6;
                } else if (v.getId() == R.id.favOxman) {
                    index = 7; // Ajusta el índice según el tamaño del arreglo
                } else if (v.getId() == R.id.favNoolha) {
                    index = 8; // Ajusta el índice según el tamaño del arreglo
                } else if (v.getId() == R.id.favSamula) {
                    index = 9; // Ajusta el índice según el tamaño del arreglo
                }

                if (index != -1) {
                    esFavorito[index] = !esFavorito[index];
                    ImageView favButton = (ImageView) v;
                    favButton.setImageResource(esFavorito[index] ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star);
                    Toast.makeText(
                            ExplorarCenotesActivity.this,
                            esFavorito[index] ? "Añadido a favoritos" : "Eliminado de favoritos",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        };

        // Asignar el listener a cada botón de favorito
        favZaci.setOnClickListener(favoritoListener);
        favSuytun.setOnClickListener(favoritoListener);
        favChukum.setOnClickListener(favoritoListener);
        favXuxha.setOnClickListener(favoritoListener);
        favSaamal.setOnClickListener(favoritoListener);
        favXkeken.setOnClickListener(favoritoListener);
        favXlakaj.setOnClickListener(favoritoListener);
        favOxman.setOnClickListener(favoritoListener);
        favNoolha.setOnClickListener(favoritoListener);
        favSamula.setOnClickListener(favoritoListener);
    }

    private void configurarBottomNavigation() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        if (bottomNavigation == null) {
            Toast.makeText(this, "Error: Barra de navegación no encontrada", Toast.LENGTH_SHORT).show();
            return;
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_inicio) {
                // Regresar a MainActivity
                Intent intent = new Intent(ExplorarCenotesActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual
                return true;
            } else if (itemId == R.id.nav_servicios) {
                // Ir a ServiciosActivity
                Intent intent = new Intent(ExplorarCenotesActivity.this, LoginPageActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_explorar) {
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra("MODO", "TODOS"); // Indicamos que es modo "todos"
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_perfil) {
                Intent intent = new Intent(ExplorarCenotesActivity.this, PerfilActivity.class);
                startActivity(intent);
                return true; // Cambiado a true
            }

            return false;
        });
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

        // Verificar coincidencias individuales
        boolean zaciVisible = "cenote zaci".contains(texto);
        boolean suytunVisible = "cenote suytun".contains(texto);
        boolean chukumVisible = "cenote chukum".contains(texto);
        boolean xuxhaVisible = "cenote x'ux ha".contains(texto);
        boolean saamalVisible = "cenote saamal".contains(texto);
        boolean xkekenVisible = "cenote xkekén".contains(texto);
        boolean xlakajVisible = "cenote xlakaj".contains(texto);
        boolean oxmanVisible = "hacienda cenote oxmán".contains(texto);
        boolean noolhaVisible = "cenote noolha by chichikan".contains(texto);
        boolean samulaVisible = "cenote samula".contains(texto);

        // Mostrar u ocultar cada tarjeta
        cardCenoteZaci.setVisibility(zaciVisible ? View.VISIBLE : View.GONE);
        cardCenoteSuytun.setVisibility(suytunVisible ? View.VISIBLE : View.GONE);
        cardCenoteChukum.setVisibility(chukumVisible ? View.VISIBLE : View.GONE);
        cardCenoteXuxha.setVisibility(xuxhaVisible ? View.VISIBLE : View.GONE);
        cardCenoteSaamal.setVisibility(saamalVisible ? View.VISIBLE : View.GONE);
        cardCenoteXkeken.setVisibility(xkekenVisible ? View.VISIBLE : View.GONE);
        cardCenoteXlakaj.setVisibility(xlakajVisible ? View.VISIBLE : View.GONE);
        cardCenoteOxman.setVisibility(oxmanVisible ? View.VISIBLE : View.GONE);
        cardCenoteNoolha.setVisibility(noolhaVisible ? View.VISIBLE : View.GONE);
        cardCenoteSamula.setVisibility(samulaVisible ? View.VISIBLE : View.GONE);

        // Determinar si hubo al menos una coincidencia
        boolean hayCoincidencia = zaciVisible || suytunVisible || chukumVisible || xuxhaVisible ||
                saamalVisible || xkekenVisible || xlakajVisible || oxmanVisible ||
                noolhaVisible || samulaVisible;

        // Control de encabezados
        if (texto.isEmpty()) {
            tvCenotesCercanos.setText("Cenotes Cercanos");
            tvCenotesCercanos.setVisibility(View.VISIBLE);
            tvCenotesPopulares.setVisibility(View.VISIBLE);
            tvRecomendados.setVisibility(View.VISIBLE);
        } else if (hayCoincidencia) {
            tvCenotesCercanos.setText("Cenotes Encontrados");
            tvCenotesCercanos.setVisibility(View.VISIBLE);
            tvCenotesPopulares.setVisibility(View.GONE);
            tvRecomendados.setVisibility(View.GONE);
        } else {
            tvCenotesCercanos.setText("No se encontró");
            tvCenotesCercanos.setVisibility(View.VISIBLE);
            tvCenotesPopulares.setVisibility(View.GONE);
            tvRecomendados.setVisibility(View.GONE);
        }
    }



}