package com.example.tuuxkabin;

public class Resena implements java.io.Serializable {
    private String usuario;
    private float puntuacion;
    private String comentario;
    private String fecha;

    // Constructor
    public Resena(String usuario, float puntuacion, String comentario, String fecha) {
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Métodos getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
