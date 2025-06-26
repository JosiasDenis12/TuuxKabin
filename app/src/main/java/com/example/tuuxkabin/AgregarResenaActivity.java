package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgregarResenaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_resena);

        EditText etUsuario = findViewById(R.id.etUsuario);
        RatingBar rbPuntuacion = findViewById(R.id.rbNuevaPuntuacion);
        EditText etComentario = findViewById(R.id.etComentario);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        ImageButton btnClose = findViewById(R.id.btnClose);

        btnGuardar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            float puntuacion = rbPuntuacion.getRating();
            String comentario = etComentario.getText().toString().trim();
            String fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

            Resena nueva = new Resena(usuario, puntuacion, comentario, fecha);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("resena", nueva);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnClose.setOnClickListener(v -> {
            finish();
        });

    }
}