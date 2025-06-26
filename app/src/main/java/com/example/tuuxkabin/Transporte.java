package com.example.tuuxkabin;
import java.util.ArrayList;
import java.util.List;
public class Transporte implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private final String nombre;
    private float rating;
    private int numResenas;
    private final String horario;
    private final String numeroTelefono;
    private final int imagenResId;
    private final double tarifaLocalMin;
    private final double tarifaLocalMax;
    private final double tarifaCenotesMin;
    private final double tarifaCenotesMax;
    private final double tarifaAeropuertoMin;
    private final double tarifaAeropuertoMax;

    private final List<Resena> resenas;

    public Transporte(String nombre, float rating, int numResenas, String horario,
                      String numeroTelefono, int imagenResId,
                      double tarifaLocalMin, double tarifaLocalMax,
                      double tarifaCenotesMin, double tarifaCenotesMax,
                      double tarifaAeropuertoMin, double tarifaAeropuertoMax) {
        this.nombre = nombre;
        this.rating = rating;
        this.numResenas = numResenas;
        this.horario = horario;
        this.numeroTelefono = numeroTelefono;
        this.imagenResId = imagenResId;
        this.tarifaLocalMin = tarifaLocalMin;
        this.tarifaLocalMax = tarifaLocalMax;
        this.tarifaCenotesMin = tarifaCenotesMin;
        this.tarifaCenotesMax = tarifaCenotesMax;
        this.tarifaAeropuertoMin = tarifaAeropuertoMin;
        this.tarifaAeropuertoMax = tarifaAeropuertoMax;
        this.resenas = new ArrayList<>();
    }

    // Getters
    public String getNombre() { return nombre; }
    public float getRating() { return rating; }
    public int getNumResenas() { return numResenas; }
    public String getHorario() { return horario; }
    public String getNumeroTelefono() { return numeroTelefono; }
    public int getImagenResId() { return imagenResId; }
    public double getTarifaLocalMin() { return tarifaLocalMin; }
    public double getTarifaLocalMax() { return tarifaLocalMax; }
    public double getTarifaCenotesMin() { return tarifaCenotesMin; }
    public double getTarifaCenotesMax() { return tarifaCenotesMax; }
    public double getTarifaAeropuertoMin() { return tarifaAeropuertoMin; }
    public double getTarifaAeropuertoMax() { return tarifaAeropuertoMax; }

    // Reseñas
    public List<Resena> getResenas() {
        return resenas;
    }

    public void agregarResena(Resena resena) {
        resenas.add(0, resena);
        // Actualiza promedio y número de reseñas
        float suma = rating * numResenas + resena.getPuntuacion();
        numResenas++;
        rating = suma / numResenas;
    }
}