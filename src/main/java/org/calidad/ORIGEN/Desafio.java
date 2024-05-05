package org.calidad.ORIGEN;
/**
 *
 * @author paula
 */


import org.calidad.Controladores.Pantalla;

import java.io.Serializable;
import java.time.LocalDate;

public class Desafio implements Serializable {
    private  Usuario UserUno;
    private  Usuario UserDos;
    private int oroApostado;
    private int oroGanado;
    private int modificador;
    private Boolean validado;
    private int ganador;

    private String fecha;

    private int rondas;

    //CONSTRUCTOR
    public Desafio(Usuario oponente1, Usuario oponente2, int oroApostado){
        this.UserUno = oponente1;
        this.UserDos = oponente2;
        Boolean aux=true;
        while(aux){
            this.setOroApostado(oroApostado);
            aux=false;
        }
    }

    public Usuario getUserUno() {
        return UserUno;
    }

    public void setUserUno(Usuario userUno) {
        UserUno = userUno;
    }

    public Usuario getUserDos() {
        return UserDos;
    }

    public void setUserDos(Usuario userDos) {
        UserDos = userDos;
    }

    public int getOroApostado() {
        return oroApostado;
    }

    public void setOroApostado(int oroApostado) {
        this.oroApostado = oroApostado;
    }

    public int getOroGanado() {
        return oroGanado;
    }

    public void setOroGanado(int oroGanado) {
        this.oroGanado = oroGanado;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }
    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha.toString();
    }

    public int getModificador() {
        return modificador;
    }

    public void setModificador(int modificador) {
        this.modificador = modificador;
    }
}