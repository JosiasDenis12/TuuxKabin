package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ServiciosActivity extends AppCompatActivity {

    // Declaración de variables para componentes de UI
    private CardView btnTodos, btnCenoteZaci, btnCenoteSuytun, btnCenoteSaamal,
            btnCenoteXkeken, btnCenoteChukum, btnCenoteXuxHa, btnCenoteXlakaj,
            btnCenoteSamula, btnCenoteOxman, btnCenoteNoolha;
    private LinearLayout btnRestaurantes, btnAlquileres, btnTiendas;

    // Tarjetas de cenotes individuales
    private CardView cenoteZaciCard, cenoteSuytunCard, cenoteChukumCard,
            cenoteXuxHaCard, cenoteSaamalCard, cenoteXkekenCard, cenoteXlakajCard,
            cenoteSamulaCard, cenoteOxmanCard, cenoteNoolhaCard;

    // Variable para rastrear la categoría actualmente seleccionada (si existe)
    private String categoriaActual = null;
    private boolean categoriaModoActivo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        // Inicializar componentes de UI
        inicializarComponentes();

        // Aplicar estilos iniciales a los botones de categoría
        estilizarBotonesCategorias();

        // Configurar listeners para filtros de cenotes
        configurarFiltrosCenotes();

        // Configurar listeners para categorías de servicios
        configurarCategoriasServicios();

        // Configurar listener para la barra de navegación inferior
        configurarBottomNavigation();

        // Ocultar todas las tarjetas de cenotes inicialmente
        ocultarTodasLasTarjetas();

        // Mostrar por defecto todos los cenotes (como si se hubiera hecho clic en "Todos")
        mostrarTodosCenotes();

        // Destacar el filtro "Todos" inicialmente
        btnTodos.setCardBackgroundColor(getResources().getColor(android.R.color.holo_purple));
        TextView textoTodos = (TextView) btnTodos.getChildAt(0);
        textoTodos.setTextColor(getResources().getColor(android.R.color.white));
    }

    private void estilizarBotonesCategorias() {
        // Configurar aspecto visual inicial de los botones de categoría
        for (LinearLayout btn : new LinearLayout[]{btnRestaurantes, btnAlquileres, btnTiendas}) {
            // Configurar padding para mejor apariencia
            btn.setPadding(
                    getResources().getDimensionPixelSize(android.R.dimen.app_icon_size) / 3,
                    getResources().getDimensionPixelSize(android.R.dimen.app_icon_size) / 4,
                    getResources().getDimensionPixelSize(android.R.dimen.app_icon_size) / 3,
                    getResources().getDimensionPixelSize(android.R.dimen.app_icon_size) / 4
            );

            // Establecer un borde sutil
            btn.setBackgroundResource(android.R.drawable.btn_default_small);

            // Encontrar TextView dentro del LinearLayout para configurarlo
            for (int i = 0; i < btn.getChildCount(); i++) {
                if (btn.getChildAt(i) instanceof TextView) {
                    TextView texto = (TextView) btn.getChildAt(i);
                    texto.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    break;
                }
            }
        }
    }

    private void inicializarComponentes() {
        // Inicializar botones de filtro de cenotes
        btnTodos = findViewById(R.id.btnTodos);
        btnCenoteZaci = findViewById(R.id.btnCenoteZaci);
        btnCenoteSuytun = findViewById(R.id.btnCenoteSuytun);
        btnCenoteSaamal = findViewById(R.id.btnCenoteSaamal);
        btnCenoteXkeken = findViewById(R.id.btnCenoteXkeken);
        btnCenoteChukum = findViewById(R.id.btnCenoteChukum);
        btnCenoteXuxHa = findViewById(R.id.btnCenoteXuxHa);
        btnCenoteXlakaj = findViewById(R.id.btnCenoteXlakaj);
        btnCenoteSamula = findViewById(R.id.btnCenoteSamula);
        btnCenoteOxman = findViewById(R.id.btnCenoteOxman);
        btnCenoteNoolha = findViewById(R.id.btnCenoteNoolha);

        // Inicializar botones de categorías
        btnRestaurantes = findViewById(R.id.btnRestaurantes);
        btnAlquileres = findViewById(R.id.btnAlquileres);
        btnTiendas = findViewById(R.id.btnTiendas);

        // Inicializar tarjetas de cenotes
        cenoteZaciCard = findViewById(R.id.cenoteZaciCard);
        cenoteSuytunCard = findViewById(R.id.cenoteSuytunCard);
        cenoteChukumCard = findViewById(R.id.cenoteChukumCard);
        cenoteXuxHaCard = findViewById(R.id.cenoteXuxHaCard);
        cenoteSaamalCard = findViewById(R.id.cenoteSaamalCard);
        cenoteXkekenCard = findViewById(R.id.cenoteXkekenCard);
        cenoteXlakajCard = findViewById(R.id.cenoteXlakajCard);
        cenoteSamulaCard = findViewById(R.id.cenoteSamulaCard);
        cenoteOxmanCard = findViewById(R.id.cenoteOxmanCard);
        cenoteNoolhaCard = findViewById(R.id.cenoteNoolhaCard);

        // Inicializar botones "Ver Cenote"
        configurarBotonesVerCenote();
    }

    private void configurarBotonesVerCenote() {
        // Configurar cada botón "Ver Cenote" para mostrar un mensaje (por ahora)
        Button btnVerCenoteZaci = findViewById(R.id.btnVerCenoteZaci);
        Button btnVerCenoteSuytun = findViewById(R.id.btnVerCenoteSuytun);
        Button btnVerCenoteChukum = findViewById(R.id.btnVerCenoteChukum);
        Button btnVerCenoteXuxHa = findViewById(R.id.btnVerCenoteXuxHa);
        Button btnVerCenoteSaamal = findViewById(R.id.btnVerCenoteSaamal);
        Button btnVerCenoteXkeken = findViewById(R.id.btnVerCenoteXkeken);
        Button btnVerCenoteXlakaj = findViewById(R.id.btnVerCenoteXlakaj);
        Button btnVerCenoteSamula = findViewById(R.id.btnVerCenoteSamula);
        Button btnVerCenoteOxman = findViewById(R.id.btnVerCenoteOxman);
        Button btnVerCenoteNoolha = findViewById(R.id.btnVerCenoteNoolha);

        View.OnClickListener verCenoteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCenote = "";

                if (v.getId() == R.id.btnVerCenoteZaci) {
                    nombreCenote = "Cenote Zaci";
                } else if (v.getId() == R.id.btnVerCenoteSuytun) {
                    nombreCenote = "Cenote Suytun";
                } else if (v.getId() == R.id.btnVerCenoteChukum) {
                    nombreCenote = "Cenote Chukum";
                } else if (v.getId() == R.id.btnVerCenoteXuxHa) {
                    nombreCenote = "Cenote X'ux Ha";
                } else if (v.getId() == R.id.btnVerCenoteSaamal) {
                    nombreCenote = "Cenote Saamal";
                } else if (v.getId() == R.id.btnVerCenoteXkeken) {
                    nombreCenote = "Cenote Xkeken";
                } else if (v.getId() == R.id.btnVerCenoteXlakaj) {
                    nombreCenote = "Cenote Xlakaj";
                } else if (v.getId() == R.id.btnVerCenoteSamula) {
                    nombreCenote = "Cenote Samula";
                } else if (v.getId() == R.id.btnVerCenoteOxman) {
                    nombreCenote = "Cenote Oxman";
                } else if (v.getId() == R.id.btnVerCenoteNoolha) {
                    nombreCenote = "Cenote Noolha";
                }

                // Mostrar un mensaje temporal en lugar de abrir una nueva actividad
                Toast.makeText(
                        ServiciosActivity.this,
                        "Ver detalles de " + nombreCenote,
                        Toast.LENGTH_SHORT
                ).show();

                /*
                // Para el futuro, cuando crees la actividad de detalle:
                Intent intent = new Intent(ServiciosActivity.this, DetalleCenoteActivity.class);
                intent.putExtra("CENOTE_NOMBRE", nombreCenote);
                startActivity(intent);
                */
            }
        };

        // Asignar el listener a cada botón
        btnVerCenoteZaci.setOnClickListener(verCenoteListener);
        btnVerCenoteSuytun.setOnClickListener(verCenoteListener);
        btnVerCenoteChukum.setOnClickListener(verCenoteListener);
        btnVerCenoteXuxHa.setOnClickListener(verCenoteListener);
        btnVerCenoteSaamal.setOnClickListener(verCenoteListener);
        btnVerCenoteXkeken.setOnClickListener(verCenoteListener);
        btnVerCenoteXlakaj.setOnClickListener(verCenoteListener);
        btnVerCenoteSamula.setOnClickListener(verCenoteListener);
        btnVerCenoteOxman.setOnClickListener(verCenoteListener);
        btnVerCenoteNoolha.setOnClickListener(verCenoteListener);
    }

    private void configurarFiltrosCenotes() {
        // Configurar listener para cada botón de filtro
        View.OnClickListener filtroListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restablecer todos los filtros a su estado normal
                resetearFiltros();

                // Cambiar el color del filtro seleccionado
                CardView botonSeleccionado = (CardView) v;
                botonSeleccionado.setCardBackgroundColor(getResources().getColor(android.R.color.holo_purple));

                // Buscar la TextView dentro del CardView y cambiar su color
                TextView texto = (TextView) ((CardView) v).getChildAt(0);
                texto.setTextColor(getResources().getColor(android.R.color.white));

                // Ocultar todas las tarjetas primero
                ocultarTodasLasTarjetas();

                // Mostrar las tarjetas correspondientes al filtro seleccionado
                if (v.getId() == R.id.btnTodos) {
                    mostrarTodosCenotes();
                } else if (v.getId() == R.id.btnCenoteZaci) {
                    cenoteZaciCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteSuytun) {
                    cenoteSuytunCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteChukum) {
                    cenoteChukumCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteXuxHa) {
                    cenoteXuxHaCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteSaamal) {
                    cenoteSaamalCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteXkeken) {
                    cenoteXkekenCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteXlakaj) {
                    cenoteXlakajCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteSamula) {
                    cenoteSamulaCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteOxman) {
                    cenoteOxmanCard.setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.btnCenoteNoolha) {
                    cenoteNoolhaCard.setVisibility(View.VISIBLE);
                }

                // Restaurar todos los servicios a su estado visible
                restaurarTodosLosServicios();

                // Si hay una categoría activa, volver a aplicar el filtro de categoría
                if (categoriaModoActivo && categoriaActual != null) {
                    aplicarCategoriaAVisibles(categoriaActual);
                } else {
                    // Resetear el estado visual de las categorías
                    resetearCategorias();
                    categoriaModoActivo = false;
                    categoriaActual = null;
                }
            }
        };

        // Asignar listener a cada botón
        btnTodos.setOnClickListener(filtroListener);
        btnCenoteZaci.setOnClickListener(filtroListener);
        btnCenoteSuytun.setOnClickListener(filtroListener);
        btnCenoteSaamal.setOnClickListener(filtroListener);
        btnCenoteXkeken.setOnClickListener(filtroListener);
        btnCenoteChukum.setOnClickListener(filtroListener);
        btnCenoteXuxHa.setOnClickListener(filtroListener);
        btnCenoteXlakaj.setOnClickListener(filtroListener);
        btnCenoteSamula.setOnClickListener(filtroListener);
        btnCenoteOxman.setOnClickListener(filtroListener);
        btnCenoteNoolha.setOnClickListener(filtroListener);
    }

    private void resetearCategorias() {
        // Restablecer el estado visual de las categorías
        btnRestaurantes.setBackgroundResource(0);
        btnRestaurantes.setElevation(0f);

        btnAlquileres.setBackgroundResource(0);
        btnAlquileres.setElevation(0f);

        btnTiendas.setBackgroundResource(0);
        btnTiendas.setElevation(0f);

        // Restaurar el color de texto original
        for (LinearLayout btn : new LinearLayout[]{btnRestaurantes, btnAlquileres, btnTiendas}) {
            TextView texto = null;
            for (int i = 0; i < btn.getChildCount(); i++) {
                if (btn.getChildAt(i) instanceof TextView) {
                    texto = (TextView) btn.getChildAt(i);
                    break;
                }
            }
            if (texto != null) {
                texto.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    }

    private void configurarCategoriasServicios() {
        // Configurar listeners para botones de categorías
        View.OnClickListener categoriaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear el estado visual de todas las categorías
                resetearCategorias();

                String categoria = "";
                LinearLayout botonSeleccionado = null;

                // Determinar qué categoría se seleccionó
                if (v.getId() == R.id.btnRestaurantes) {
                    categoria = "restaurante";
                    botonSeleccionado = btnRestaurantes;
                } else if (v.getId() == R.id.btnAlquileres) {
                    categoria = "alquiler";
                    botonSeleccionado = btnAlquileres;
                } else if (v.getId() == R.id.btnTiendas) {
                    categoria = "tienda";
                    botonSeleccionado = btnTiendas;
                }

                // Si la categoría seleccionada es la misma que ya estaba activa, la desactivamos
                if (categoria.equals(categoriaActual) && categoriaModoActivo) {
                    categoriaActual = null;
                    categoriaModoActivo = false;
                    restaurarTodosLosServicios();
                    return;
                }

                // Aplicar estilo profesional al botón seleccionado
                if (botonSeleccionado != null) {
                    // Establecer fondo con gradiente o color sólido según el tema de la app
                    botonSeleccionado.setBackgroundResource(android.R.drawable.btn_default);
                    botonSeleccionado.setElevation(8f); // Añadir elevación para efecto 3D

                    // Cambiar el color del texto si existe dentro del LinearLayout
                    TextView texto = null;
                    for (int i = 0; i < botonSeleccionado.getChildCount(); i++) {
                        if (botonSeleccionado.getChildAt(i) instanceof TextView) {
                            texto = (TextView) botonSeleccionado.getChildAt(i);
                            break;
                        }
                    }
                    if (texto != null) {
                        texto.setTextColor(getResources().getColor(android.R.color.holo_purple));
                    }

                    Toast.makeText(ServiciosActivity.this,
                            "Mostrando " + (categoria.equals("restaurante") ? "restaurantes" :
                                    categoria.equals("alquiler") ? "servicios de alquiler" : "tiendas"),
                            Toast.LENGTH_SHORT).show();
                }

                // Actualizar categoría actual
                categoriaActual = categoria;
                categoriaModoActivo = true;

                // Aplicar filtro por categoría a las tarjetas visibles
                aplicarCategoriaAVisibles(categoria);
            }
        };

        btnRestaurantes.setOnClickListener(categoriaListener);
        btnAlquileres.setOnClickListener(categoriaListener);
        btnTiendas.setOnClickListener(categoriaListener);
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
                Intent intent = new Intent(ServiciosActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual
                return true;
            } else if (itemId == R.id.nav_explorar) {
                // Ir a la actividad de exploración
                Intent intent = new Intent(ServiciosActivity.this, MapsActivity.class);
                intent.putExtra("MODO", "TODOS"); // Indicamos que es modo "todos"
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_perfil) {
                Intent intent = new Intent(ServiciosActivity.this, PerfilActivity.class);
                startActivity(intent);
                return true; // Cambiado a true
            }

            return false;
        });
    }

    private void resetearFiltros() {
        // Restaurar el color de fondo y texto de todos los filtros
        CardView[] filtros = {btnTodos, btnCenoteZaci, btnCenoteSuytun, btnCenoteSaamal,
                btnCenoteXkeken, btnCenoteChukum, btnCenoteXuxHa, btnCenoteXlakaj,
                btnCenoteSamula, btnCenoteOxman, btnCenoteNoolha};

        for (CardView filtro : filtros) {
            filtro.setCardBackgroundColor(getResources().getColor(android.R.color.white));
            TextView texto = (TextView) filtro.getChildAt(0);
            texto.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }
    }

    private void ocultarTodasLasTarjetas() {
        // Ocultar todas las tarjetas de cenotes
        cenoteZaciCard.setVisibility(View.GONE);
        cenoteSuytunCard.setVisibility(View.GONE);
        cenoteChukumCard.setVisibility(View.GONE);
        cenoteXuxHaCard.setVisibility(View.GONE);
        cenoteSaamalCard.setVisibility(View.GONE);
        cenoteXkekenCard.setVisibility(View.GONE);
        cenoteXlakajCard.setVisibility(View.GONE);
        cenoteSamulaCard.setVisibility(View.GONE);
        cenoteOxmanCard.setVisibility(View.GONE);
        cenoteNoolhaCard.setVisibility(View.GONE);
    }

    private void mostrarTodosCenotes() {
        // Mostrar todas las tarjetas de cenotes
        cenoteZaciCard.setVisibility(View.VISIBLE);
        cenoteSuytunCard.setVisibility(View.VISIBLE);
        cenoteChukumCard.setVisibility(View.VISIBLE);
        cenoteXuxHaCard.setVisibility(View.VISIBLE);
        cenoteSaamalCard.setVisibility(View.VISIBLE);
        cenoteXkekenCard.setVisibility(View.VISIBLE);
        cenoteXlakajCard.setVisibility(View.VISIBLE);
        cenoteSamulaCard.setVisibility(View.VISIBLE);
        cenoteOxmanCard.setVisibility(View.VISIBLE);
        cenoteNoolhaCard.setVisibility(View.VISIBLE);

        // Ajustar las restricciones para que las tarjetas se muestren en orden correcto
        ConstraintLayout.LayoutParams paramsSuytun = (ConstraintLayout.LayoutParams) cenoteSuytunCard.getLayoutParams();
        paramsSuytun.topToBottom = R.id.cenoteZaciCard;
        cenoteSuytunCard.setLayoutParams(paramsSuytun);

        ConstraintLayout.LayoutParams paramsChukum = (ConstraintLayout.LayoutParams) cenoteChukumCard.getLayoutParams();
        paramsChukum.topToBottom = R.id.cenoteSuytunCard;
        cenoteChukumCard.setLayoutParams(paramsChukum);

        ConstraintLayout.LayoutParams paramsXuxHa = (ConstraintLayout.LayoutParams) cenoteXuxHaCard.getLayoutParams();
        paramsXuxHa.topToBottom = R.id.cenoteChukumCard;
        cenoteXuxHaCard.setLayoutParams(paramsXuxHa);

        ConstraintLayout.LayoutParams paramsSaamal = (ConstraintLayout.LayoutParams) cenoteSaamalCard.getLayoutParams();
        paramsSaamal.topToBottom = R.id.cenoteXuxHaCard;
        cenoteSaamalCard.setLayoutParams(paramsSaamal);

        ConstraintLayout.LayoutParams paramsXkeken = (ConstraintLayout.LayoutParams) cenoteXkekenCard.getLayoutParams();
        paramsXkeken.topToBottom = R.id.cenoteSaamalCard;
        cenoteXkekenCard.setLayoutParams(paramsXkeken);

        ConstraintLayout.LayoutParams paramsXlakaj = (ConstraintLayout.LayoutParams) cenoteXlakajCard.getLayoutParams();
        paramsXlakaj.topToBottom = R.id.cenoteXkekenCard;
        cenoteXlakajCard.setLayoutParams(paramsXlakaj);

        ConstraintLayout.LayoutParams paramsSamula = (ConstraintLayout.LayoutParams) cenoteSamulaCard.getLayoutParams();
        paramsSamula.topToBottom = R.id.cenoteXlakajCard;
        cenoteSamulaCard.setLayoutParams(paramsSamula);

        ConstraintLayout.LayoutParams paramsOxman = (ConstraintLayout.LayoutParams) cenoteOxmanCard.getLayoutParams();
        paramsOxman.topToBottom = R.id.cenoteSamulaCard;
        cenoteOxmanCard.setLayoutParams(paramsOxman);

        ConstraintLayout.LayoutParams paramsNoolha = (ConstraintLayout.LayoutParams) cenoteNoolhaCard.getLayoutParams();
        paramsNoolha.topToBottom = R.id.cenoteOxmanCard;
        cenoteNoolhaCard.setLayoutParams(paramsNoolha);
    }

    /**
     * Restaura todos los servicios a su estado visible original dentro de las tarjetas
     */
    private void restaurarTodosLosServicios() {
        // Array con todas las tarjetas de cenotes
        CardView[] cenotesCards = {
                cenoteZaciCard, cenoteSuytunCard, cenoteChukumCard, cenoteXuxHaCard,
                cenoteSaamalCard, cenoteXkekenCard, cenoteXlakajCard, cenoteSamulaCard,
                cenoteOxmanCard, cenoteNoolhaCard
        };

        // Hacer visibles todos los servicios en cada tarjeta
        for (CardView cenoteCard : cenotesCards) {
            LinearLayout serviciosContainer = (LinearLayout) ((LinearLayout) cenoteCard.getChildAt(0));

            // El primer elemento es el FrameLayout con la imagen principal, empezamos desde el segundo
            for (int i = 1; i < serviciosContainer.getChildCount(); i++) {
                View servicioView = serviciosContainer.getChildAt(i);

                if (servicioView instanceof LinearLayout) {
                    servicioView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Aplica un filtro de categoría solo a las tarjetas de cenotes que están actualmente visibles
     */
    private void aplicarCategoriaAVisibles(String categoria) {
        // Array con todas las tarjetas de cenotes
        CardView[] cenotesCards = {
                cenoteZaciCard, cenoteSuytunCard, cenoteChukumCard, cenoteXuxHaCard,
                cenoteSaamalCard, cenoteXkekenCard, cenoteXlakajCard, cenoteSamulaCard,
                cenoteOxmanCard, cenoteNoolhaCard
        };

        // Filtrar servicios solo en las tarjetas visibles
        for (CardView cenoteCard : cenotesCards) {
            if (cenoteCard.getVisibility() == View.VISIBLE) {
                filtrarServiciosPorCategoria(cenoteCard, categoria);
            }
        }
    }

    private void filtrarServiciosPorCategoria(CardView cenoteCard, String categoria) {
        // Encontrar el LinearLayout dentro de la tarjeta que contiene los servicios
        LinearLayout serviciosContainer = (LinearLayout) ((LinearLayout) cenoteCard.getChildAt(0));

        // El primer elemento es el FrameLayout con la imagen principal, empezamos desde el segundo
        for (int i = 1; i < serviciosContainer.getChildCount(); i++) {
            View servicioView = serviciosContainer.getChildAt(i);

            // Si no es un LinearLayout (podría ser un separador), continuamos
            if (!(servicioView instanceof LinearLayout)) {
                continue;
            }

            LinearLayout servicioLinearLayout = (LinearLayout) servicioView;

            // Obtener el título del servicio
            LinearLayout contenidoServicio = (LinearLayout) servicioLinearLayout.getChildAt(1);
            TextView tituloServicio = (TextView) contenidoServicio.getChildAt(0);
            String titulo = tituloServicio.getText().toString().toLowerCase();

            // Verificar si el servicio pertenece a la categoría seleccionada
            boolean mostrarServicio = false;

            if (categoria.equals("restaurante")) {
                mostrarServicio = titulo.contains("restaurante") ||
                        titulo.contains("comida") ||
                        titulo.contains("buffet") ||
                        titulo.contains("menú");
            } else if (categoria.equals("alquiler")) {
                mostrarServicio = titulo.contains("renta") ||
                        titulo.contains("alquiler") ||
                        titulo.contains("chaleco") ||
                        titulo.contains("taquilla") ||
                        titulo.contains("locker") ||
                        titulo.contains("casillero");
            } else if (categoria.equals("tienda")) {
                mostrarServicio = titulo.contains("tienda") ||
                        titulo.contains("artesanía") ||
                        titulo.contains("souvenir") ||
                        titulo.contains("gift") ||
                        titulo.contains("shop");
            }

            // Mostrar u ocultar el servicio según la categoría
            servicioLinearLayout.setVisibility(mostrarServicio ? View.VISIBLE : View.GONE);
        }
    }
}