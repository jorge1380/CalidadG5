package org.calidad.ORIGEN;

import java.util.ArrayList;
import java.util.List;

public interface Personaje {

    public void setNombre(String nombre);

    public String getNombre();

    public void setArmas(ArrayList<Arma> armas);

    public ArrayList<Arma> getArmas();

    public void setArmadura(ArrayList<Armadura> armaduras);

    public ArrayList<Armadura> getArmadura();

    public void setArmasActivas(ArrayList<Arma> armasActivas);
    public List<Arma> getArmasActivas();

    public void setArmaduraActiva(Armadura armaduraActiva);

    public Armadura getArmaduraActiva();

    public void setEsbirros(ArrayList<Esbirro> esbirros);

    public ArrayList<Esbirro> getEsbirros();

    public void setOro(int oro);

    public int getOro();

    public void setSalud(int salud);

    public int getSalud();

    public void setPoder(int poder);

    public void setDebilidades(ArrayList<Debilidad> Debilidad);

    public ArrayList<Debilidad> getDebilidades();

    public void setFortalezas(ArrayList<Fortaleza> fortalezas);

    public ArrayList<Fortaleza> getFortalezas();

    public void setHabilidad(String habilidad);


    public void setAtqHab(int ataque);

    public void setDefHab(int defensa);

    public void usarHabilidad();

    public void ganarRonda();

    public void perderRonda();

    public int calcularAtaque();

    public int calcularDefensa();

    public int saludEsbirros();

    public void modificarDatos();

    public Esbirro crearEsbirros();

}
