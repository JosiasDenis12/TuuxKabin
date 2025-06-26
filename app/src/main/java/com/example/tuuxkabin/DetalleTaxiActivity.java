package com.example.tuuxkabin;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

public class DetalleTaxiActivity extends AppCompatActivity {
    private static final int REQUEST_ADD_REVIEW = 1;
    private List<Resena> resenas;
    private Transporte transporte;

    private ResenaAdapter resenaAdapter;

    private ImageView ivTaxiImagen;
    private TextView tvNombreTaxi;
    private RatingBar ratingBar;
    private TextView tvResenasPromedio;
    private TextView tvTarifaLocal;
    private TextView tvTarifaCenotes;
    private TextView tvTarifaAeropuerto;
    private Button btnLlamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_taxi);
        transporte = (Transporte) getIntent().getSerializableExtra("transporte");
        resenas = transporte.getResenas();

        // Obtener el transporte desde el intent
        Transporte transporte = (Transporte) getIntent().getSerializableExtra("transporte");

        // Inicializar las vistas
        ivTaxiImagen = findViewById(R.id.ivTaxiImagen);
        tvNombreTaxi = findViewById(R.id.tvNombreTaxi);
        ratingBar = findViewById(R.id.ratingBar);
        tvResenasPromedio = findViewById(R.id.tvResenasPromedio);
        tvTarifaLocal = findViewById(R.id.tvTarifaLocal);
        tvTarifaCenotes = findViewById(R.id.tvTarifaCenotes);
        tvTarifaAeropuerto = findViewById(R.id.tvTarifaHotel);
        btnLlamar = findViewById(R.id.btnLlamar);

        // Configurar las vistas con los datos del transporte
        tvNombreTaxi.setText(transporte.getNombre());
        tvResenasPromedio.setText(String.format(Locale.getDefault(),
                "%.1f (%d reseñas)", transporte.getRating(), transporte.getNumResenas()));

        // Establecer la calificación
        ratingBar.setRating(transporte.getRating());

        // Cambiar los colores de las estrellas según el rating
        if (transporte.getRating() >= 1) {
            ratingBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.star_filled)));
            ratingBar.setSecondaryProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.star_empty)));
        } else {
            ratingBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.star_empty)));
            ratingBar.setSecondaryProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.star_empty)));
        }

        // Configurar las tarifas
        tvTarifaLocal.setText(String.format(Locale.getDefault(), "Local: %.2f - %.2f MXN", transporte.getTarifaLocalMin(), transporte.getTarifaLocalMax()));
        tvTarifaCenotes.setText(String.format(Locale.getDefault(), "Cenote: %.2f - %.2f MXN", transporte.getTarifaCenotesMin(), transporte.getTarifaCenotesMax()));
        tvTarifaAeropuerto.setText(String.format(Locale.getDefault(), "Hotel: %.2f - %.2f MXN", transporte.getTarifaAeropuertoMin(), transporte.getTarifaAeropuertoMax()));

        // Cargar la imagen
        try {
            ivTaxiImagen.setImageResource(transporte.getImagenResId());
        } catch (Resources.NotFoundException e) {
            ivTaxiImagen.setImageResource(R.drawable.ic_google2);
        }

        // Configurar el botón de llamada
        btnLlamar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + transporte.getNumeroTelefono()));
            startActivity(intent);
        });

        // Configuración del RecyclerView para las reseñas
        RecyclerView rvResenas = findViewById(R.id.rvResenas);
        rvResenas.setLayoutManager(new LinearLayoutManager(this));
        resenaAdapter = new ResenaAdapter(resenas);
        rvResenas.setAdapter(resenaAdapter);

        // Configurar el botón para agregar reseña
        FloatingActionButton fab = findViewById(R.id.fabAgregarResena);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarResenaActivity.class);
            startActivityForResult(intent, REQUEST_ADD_REVIEW);
        });

        //Reseñas
        if (resenas.isEmpty()) {
            if ("Radio Taxi Amarillos".equals(transporte.getNombre())) {
                resenas.add(new Resena("Carlos", 4.5f, "Muy buen servicio, llegó puntual y fue amable.", "01/05/2025"));
                resenas.add(new Resena("Ana", 5.0f, "Excelente experiencia. Lo recomiendo totalmente.", "03/05/2025"));
                resenas.add(new Resena("Jorge", 4.0f, "El coche estaba limpio y el conductor fue cordial.", "04/05/2025"));
                resenas.add(new Resena("Sofía", 4.8f, "Llegó muy rápido y el viaje fue cómodo.", "06/05/2025"));
                resenas.add(new Resena("Lucía", 5.0f, "Excelente atención, muy recomendable.", "07/05/2025"));
                resenas.add(new Resena("Eduardo", 3.5f, "Todo bien aunque el conductor hablaba mucho.", "08/05/2025"));
                resenas.add(new Resena("Valeria", 4.7f, "Buen servicio y precio justo.", "09/05/2025"));
                resenas.add(new Resena("Miguel", 4.9f, "Muy profesional y seguro.", "10/05/2025"));
                resenas.add(new Resena("Andrea", 5.0f, "Sin quejas, perfecto desde la app hasta el viaje.", "11/05/2025"));
                resenas.add(new Resena("Raúl", 4.6f, "Fue puntual y muy respetuoso.", "12/05/2025"));
            } else if ("Radio Taxi Gavilanes".equals(transporte.getNombre())) {
                resenas.add(new Resena("María", 4.0f, "Buen trato y tarifa razonable.", "05/05/2025"));
                resenas.add(new Resena("Tomás", 3.8f, "Todo bien, aunque algo lento.", "06/05/2025"));
                resenas.add(new Resena("Elena", 4.2f, "Buen servicio, repetiría.", "07/05/2025"));
                resenas.add(new Resena("Roberto", 4.5f, "Muy amable y rápido.", "08/05/2025"));
                resenas.add(new Resena("Daniela", 4.0f, "Cómodo y sin problemas.", "09/05/2025"));
                resenas.add(new Resena("Fernando", 3.9f, "Nada excepcional pero cumplió.", "10/05/2025"));
            } else if ("Radio Taxi A.A.O".equals(transporte.getNombre())) {
                resenas.add(new Resena("Luis", 3.0f, "El conductor fue amable pero llegó tarde.", "04/05/2025"));
                resenas.add(new Resena("Pedro", 2.5f, "El conductor se perdió y tardó más de lo esperado.", "06/05/2025"));
                resenas.add(new Resena("Natalia", 3.8f, "Todo correcto, pero el auto era viejo.", "07/05/2025"));
                resenas.add(new Resena("Héctor", 4.0f, "Buen precio y atención, aunque puede mejorar.", "08/05/2025"));
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_REVIEW && resultCode == RESULT_OK && data != null) {
            Resena nueva = (Resena) data.getSerializableExtra("resena");
            if (nueva != null) {
                transporte.agregarResena(nueva);
                resenaAdapter.notifyItemInserted(0);
            }
        }
    }
}