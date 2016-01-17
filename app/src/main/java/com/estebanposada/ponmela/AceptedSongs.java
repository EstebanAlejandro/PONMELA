package com.estebanposada.ponmela;

/**
 * Created by usuario on 16/01/2016.
 */
public class AceptedSongs {

    private String nombre;
    private String usuario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public AceptedSongs(String nombre, String usuario) {

        this.nombre = nombre;
        this.usuario = usuario;
    }
}
