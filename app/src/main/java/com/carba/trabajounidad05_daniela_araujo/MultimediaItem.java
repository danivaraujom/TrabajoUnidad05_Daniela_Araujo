package com.carba.trabajounidad05_daniela_araujo;

import java.io.Serializable;

public class MultimediaItem implements Serializable {
    public static final int TIPO_VIDEO=1;
    public static final int TIPO_AUDIO=2;
    public static final int TIPO_WEB=3;

    private String titulo;
    private String url;
    private int tipo;
    private String descripcion;
    private int imagen;

    public MultimediaItem(String titulo, String url, int tipo, String descripcion, int imagen) {
        this.titulo = titulo;
        this.url = url;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public int getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }
}
