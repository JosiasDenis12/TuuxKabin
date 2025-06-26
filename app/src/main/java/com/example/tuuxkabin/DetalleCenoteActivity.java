package com.example.tuuxkabin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleCenoteActivity extends AppCompatActivity {

    // Declaración de variables para las vistas
    private ImageView imagenCenote, btnCerrar;
    private TextView nombreCenote, descripcionCenote, horarioCenote, precioCenote, btnMapa, btnReservar;
    // Información del cenote seleccionado
    private String cenoteNombre;
    private int cenoteImagen;
    private String cenoteDescripcion;
    private String cenoteHorario;
    private String cenotePrecio;
    private String cenoteUbicacion;
    private String cenoteUrlReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cenote_detail);

        // Inicializar componentes
        inicializarComponentes();

        // Obtener datos del intent
        obtenerDatosIntent();

        // Cargar información del cenote
        cargarInformacionCenote();

        // Configurar listeners
        configurarListeners();
    }

    private void inicializarComponentes() {
        imagenCenote = findViewById(R.id.imagenCenote);
        btnCerrar = findViewById(R.id.btnCerrar);
        nombreCenote = findViewById(R.id.nombreCenote);
        descripcionCenote = findViewById(R.id.descripcionCenote);
        horarioCenote = findViewById(R.id.horarioCenote);
        precioCenote = findViewById(R.id.precioCenote);
        btnMapa = findViewById(R.id.btnMapa);
        btnReservar = findViewById(R.id.btnReservar);
    }

    private void obtenerDatosIntent() {
        // Verificar si el Intent contiene el nombre del cenote
        if (getIntent().hasExtra("CENOTE_NOMBRE")) {
            cenoteNombre = getIntent().getStringExtra("CENOTE_NOMBRE");

            // Cargar los datos del cenote basado en el nombre
            if (cenoteNombre != null) {
                cargarDatosCenote(cenoteNombre);
            } else {
                mostrarErrorYSalir();
            }
        } else {
            mostrarErrorYSalir();
        }
    }

    private void mostrarErrorYSalir() {
        Toast.makeText(this, "Error: No se pudo cargar la información del cenote", Toast.LENGTH_SHORT).show();
        finish(); // Cerrar la actividad si no hay datos válidos
    }

    private void cargarDatosCenote(String nombre) {
        // Cargamos los datos específicos según el cenote seleccionado
        switch (nombre) {
            case "Cenote Zaci":
                cenoteImagen = R.drawable.cenote_zaci;
                cenoteDescripcion = "Cenote abierto ubicado en el centro de Valladolid. " +
                        "Cuenta con plataformas para saltar y áreas para nadar.";
                cenoteHorario = "Horario: 9:00 AM - 5:30 PM";
                cenotePrecio = "Entrada: $30 MXN";
                cenoteUbicacion = "Centro de Valladolid";
                cenoteUrlReserva = "http://cenotezaci.com/";
                break;
            case "Cenote Suytun":
                cenoteImagen = R.drawable.cenote_suytun;
                cenoteDescripcion = "Famoso por su plataforma circular en el centro. " +
                        "Ideal para fotografías cuando el rayo de luz entra por la apertura.";
                cenoteHorario = "Horario: 9:00 AM - 6:00 PM";
                cenotePrecio = "Entrada: $120 MXN";
                cenoteUbicacion = "A 8 km de Valladolid, carretera a Cancún";
                cenoteUrlReserva = "https://cenotessuytun.com/boletos";
                break;
            case "Cenote Chukum":
                cenoteImagen = R.drawable.cenote_chukum;
                cenoteDescripcion = "Cenote subterráneo con aguas cristalinas y tranquilas. " +
                        "Cuenta con instalaciones modernas y restaurante.";
                cenoteHorario = "Horario: 10:00 AM - 5:00 PM";
                cenotePrecio = "Entrada: $150 MXN";
                cenoteUbicacion = "A 20 km de Valladolid";
                cenoteUrlReserva = "https://www.haciendachukum.com/precios/";
                break;
            case "Cenote X'ux Ha":
                cenoteImagen = R.drawable.cenote_xuxha;
                cenoteDescripcion = "Cenote poco visitado con aguas azul turquesa. " +
                        "Perfecto para quienes buscan un lugar tranquilo para nadar.";
                cenoteHorario = "Horario: 8:00 AM - 6:00 PM";
                cenotePrecio = "Entrada: $100 MXN";
                cenoteUbicacion = "Hacienda San Lorenzo, 15 km de Valladolid";
                cenoteUrlReserva = "https://www.haciendaxuxha.com";
                break;
            case "Cenote Saamal":
                cenoteImagen = R.drawable.cenote_saamal;
                cenoteDescripcion = "También conocido como Cenote Dzitnup. " +
                        "Uno de los más fotografiados por su impresionante rayo de luz.";
                cenoteHorario = "Horario: 8:00 AM - 6:00 PM";
                cenotePrecio = "Entrada: $80 MXN";
                cenoteUbicacion = "Hacienda Selva Maya, 7 km de Valladolid";
                cenoteUrlReserva = "https://www.haciendaselvamaya.com";
                break;
            case "Cenote Xlakaj":
                cenoteImagen = R.drawable.cenote_xlakaj;
                cenoteDescripcion = "Cenote abierto rodeado de vegetación. " +
                        "Ideal para familias por su poca profundidad en algunas áreas.";
                cenoteHorario = "Horario: 9:00 AM - 5:00 PM";
                cenotePrecio = "Entrada: $70 MXN";
                cenoteUbicacion = "Dzitas, a 12 km de Valladolid";
                cenoteUrlReserva = "https://www.visitayucatan.com/cenote-xlakaj";
                break;
            case "Cenote Xkekén":
                cenoteImagen = R.drawable.cenote_xkeken;
                cenoteDescripcion = "Un cenote semi abierto con aguas cristalinas y " +
                        "formaciones rocosas espectaculares. Perfecto para nadar y tomar fotos.";
                cenoteHorario = "Horario: 8:00 AM - 5:00 PM";
                cenotePrecio = "Entrada: $80 MXN";
                cenoteUbicacion = "A 5 km del centro de Valladolid";
                cenoteUrlReserva = "https://parquexkeken.mx/";
                break;
            case "Cenote Samula":
                cenoteImagen = R.drawable.cenote_samula;
                cenoteDescripcion = "Un cenote semiabierto con aguas cristalinas y formaciones rocosas impresionantes. Perfecto para nadar y relajarse.";
                cenoteHorario = "Horario: 8:00 AM - 6:00 PM";
                cenotePrecio = "Entrada: $100 MXN";
                cenoteUbicacion = "A 5 km del centro de Valladolid";
                cenoteUrlReserva = "https://cenotesamula.com/";
                break;
            case "Hacienda Cenote Oxmán":
                cenoteImagen = R.drawable.cenote_oxman;
                cenoteDescripcion = "El Cenote Oxman se encuentra en la Península de Yucatán, cerca de Valladolid. Está en los terrenos de la Hacienda San Lorenzo Oxman, una propiedad histórica con hermosos alrededores.";
                cenoteHorario = "Horario: Lunes a Sábado 7:00 AM - 5:00 PM";
                cenotePrecio = "Paquete básico: $200 MXN adultos, $100 MXN niños. Paquete plus: $400 MXN adultos, $250 MXN niños.";
                cenoteUbicacion = "Hacienda San Lorenzo Oxman, Valladolid";
                cenoteUrlReserva = "https://haciendaoxman.com/";
                break;
            case "Cenote Noolha by Chichikan":
                cenoteImagen = R.drawable.cenote_noolha;
                cenoteDescripcion = "Un cenote abierto con características únicas: 24 metros de altura, 8 metros de profundidad, paredes de piedra caliza, una isla central y una cascada que genera una energía que alimenta el alma.";
                cenoteHorario = "Horario: Lunes a Sábado 7:00 AM - 5:00 PM";
                cenotePrecio = "Paquete Ha: consultar precios en el sitio web.";
                cenoteUbicacion = "Chichikan, cerca de Valladolid";
                cenoteUrlReserva = "https://cenotenoolha.com/";
                break;
            default:
                cenoteImagen = R.drawable.cenote_xkeken;
                cenoteDescripcion = "Un cenote semi abierto con aguas cristalinas y " +
                        "formaciones rocosas espectaculares. Perfecto para nadar y tomar fotos.";
                cenoteHorario = "Horario: 8:00 AM - 5:00 PM";
                cenotePrecio = "Entrada: $80 MXN";
                cenoteUbicacion = "A 5 km del centro de Valladolid";
                cenoteUrlReserva = "https://parquexkeken.mx/";
                break;
        }
    }

    private void cargarInformacionCenote() {
        // Establecemos los datos en las vistas
        imagenCenote.setImageResource(cenoteImagen);
        nombreCenote.setText(cenoteNombre);
        descripcionCenote.setText(cenoteDescripcion);
        horarioCenote.setText(cenoteHorario);
        precioCenote.setText(cenotePrecio);
    }

    private void configurarListeners() {
        // Botón de cerrar
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cerrar la actividad
            }
        });



        // Botón de reservar
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cenoteNombre.equals("Cenote X'ux Ha")) {
                    abrirWhatsApp("9851136573", "Hola, me gustaría reservar en el Cenote X'ux Ha.");
                } else if (cenoteNombre.equals("Cenote Saamal")) {
                    abrirWhatsApp("9858526095", "Hola, me gustaría reservar en el Cenote Saamal.");
                } else if (cenoteNombre.equals("Cenote Xlakaj")) {
                    abrirWhatsApp("9851145053", "Hola, me gustaría reservar en el Cenote Xlakaj.");
                } else if (cenoteNombre.equals("Cenote Samula")) {
                    abrirWhatsApp("9858526005", "Hola, me gustaría reservar en el Cenote Samula.");
                } else if (cenoteNombre.equals("Hacienda Cenote Oxmán")) {
                    abrirWhatsApp("9851065066", "Hola, me gustaría reservar en el Cenote Oxmán.");
                } else if (cenoteNombre.equals("Cenote Noolha by Chichikan")) {
                    abrirWhatsApp("9999026005", "Hola, me gustaría reservar en el Cenote Noolha by Chichikan.");
                } else {
                    // Abrir sitio web de reservas si está disponible
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse(cenoteUrlReserva));
                    startActivity(intentWeb);
                }
            }
        });
    }

    private void abrirWhatsApp(String numero, String mensaje) {
        try {
            String url = "https://wa.me/" + numero + "?text=" + Uri.encode(mensaje);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir WhatsApp", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirMapaIndividual(View view) {
        TextView tvNombreCenote = findViewById(R.id.nombreCenote);
        String nombreCenote = tvNombreCenote.getText().toString();

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("MODO", "INDIVIDUAL"); // Indicamos que es modo individual
        intent.putExtra("NOMBRE_CENOTE", nombreCenote);
        startActivity(intent);
    }

}
