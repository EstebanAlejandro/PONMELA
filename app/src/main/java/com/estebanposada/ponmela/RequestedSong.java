package com.estebanposada.ponmela;

/**
 * Created by usuario on 13/01/2016.
 */
public class RequestedSong {

    private String nombre;
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public RequestedSong(String nombre, String usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


}
