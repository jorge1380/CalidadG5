package org.calidad.ORIGEN;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;

/**
 *
 * @author paula
 */
public class Usuario implements Serializable {

    private String nombre;
    private String nickname;
    private String contrasena;
    private Desafio desafio;
    private Personaje personaje;
    private boolean baneado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public boolean isBaneado() {
        return baneado;
    }
    public void setBaneado(boolean baneado) {
        this.baneado = baneado;
    }

    //Constructor
    /* public Usuario (String nom, String nick, String password){
        this.nombre = nom;
        this.nickname = nick;
        this.contrasena = password;
        this.baneado = false;
        this.oro = 500;
        this.personaje = null;
    }    creo que con los constructores por defecto vamos bien*/

    public Personaje getPersonaje(){
        return this.personaje;
    }

    public void setPersonaje(Personaje pj){
        this.personaje = pj;
    }

    public void setDesafio(Desafio d){
        this.desafio = d;
    }

    public Desafio getDesafio(){
        return this.desafio;
    }
}