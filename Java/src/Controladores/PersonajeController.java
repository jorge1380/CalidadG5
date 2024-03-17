package Controladores;

import ORIGEN.Factory.CazadorFactory;
import ORIGEN.Factory.LicantropoFactory;
import ORIGEN.Factory.VampiroFactory;


import ORIGEN.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonajeController {
    ArmaController armaController = new ArmaController();
    ArmaduraController armaduraController = new ArmaduraController();
    FortalezaController fortalezaController = new FortalezaController();
    DebilidadController debilidadController = new DebilidadController();


    public void menu(){
        Pantalla.imprimir("Seleccione una opción");
        Pantalla.imprimir("1. Vampiro");
        Pantalla.imprimir("2. Licántropo");
        Pantalla.imprimir("3. Cazador");
        Pantalla.imprimir("4. Cancelar");
    }
    public Usuario registrarPersonaje(Usuario user){//operación de los usuarios para poder crear su propio personaje
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (option != 1 && option != 2 && option != 3 && option != 4) {//mientras que no se elija una opción de las dadas en el menú no avanza en el código
            menu();
            option = sc.nextInt();
        }
        switch (option) {
            case 1://Crea un vampiro
                VampiroFactory vampiro = new VampiroFactory();
                user.setPersonaje(vampiro.crearPersonaje());
                user.getPersonaje().setOro(500);
                break;
            case 2://Crea un licántropo
                LicantropoFactory licantropo = new LicantropoFactory();
                user.setPersonaje(licantropo.crearPersonaje());
                break;
            case 3://Crea un cazador
                CazadorFactory cazador = new CazadorFactory();
                user.setPersonaje(cazador.crearPersonaje());
                break;
            case 4://Cancela la operación de crear personaje
                break;
        }
       return user;
    }

   
   
    public Personaje modificarPersonaje(Personaje personaje) {//operación exclusiva de los operadores que permite cambiar datos de los personajes propios de cada usuario
        boolean salir = false;
        while (!salir) {
            Pantalla.imprimir("  1.Editar Arma o Armadura");
            Pantalla.imprimir("  2.Editar Fotalezas o Debilidades");
            Pantalla.imprimir("  3.Añadir Arma o Armadura");
            Pantalla.imprimir("  4.Añadir Fortaleza o Debilidad Arma");
            Pantalla.imprimir("  5.Añadir Esbirro");
            Pantalla.imprimir("  6.Modificar Estadisticas");
            Pantalla.imprimir("  7.Cancelar");
            int o = Pantalla.pedirenteros("Elige Opcion");
            switch (o) {
                case 1://editar armas o armaduras
                    Pantalla.imprimir("  1.Editar Arma");
                    Pantalla.imprimir("  2.Editar Armadura");
                    Pantalla.imprimir("  Otro.Cancelar");
                    int b = Pantalla.pedirenteros("Elige Opcion");
                    if (b == 1) {//editar arma
                        return armaController.editarArma(personaje);
                    } else if (b == 2) {//editar armadura
                        return armaduraController.editarArmadura(personaje);
                    }
                    break;
                case 2://editar fortalezas y debilidades
                    Pantalla.imprimir("  1.Editar Fortaleza");
                    Pantalla.imprimir("  2.Editar Debilidad");
                    Pantalla.imprimir("  Otro.Salir");
                    int c = Pantalla.pedirenteros("Elige Opcion");
                    if (c == 1) {//editar fortaleza
                        return fortalezaController.editarFortaleza(personaje);
                    } else if (c == 2) {//editar debilidad
                        return debilidadController.editarDebilidad(personaje);
                    }
                    break;
                case 3://crear armas y armaduras
                    Pantalla.imprimir("  1.Crear Arma");
                    Pantalla.imprimir("  2.Crear Armadura");
                    Pantalla.imprimir("  Otro.Cancelar");
                    int a = Pantalla.pedirenteros("Elige Opcion");
                    if (a == 1) {//crear arma
                        Arma nueva = armaController.crearArma();
                        ArrayList<Arma> aux = personaje.getArmas();
                        if (aux == null)//si un personaje no tiene lista de armas, se crea una.
                            aux = new ArrayList<>();
                        aux.add(nueva);//se añade a la lista de armas el arma creada.
                        personaje.setArmas(aux);
                        Pantalla.imprimir("Arma " + nueva.getNombre() + " creada");
                    } else if (a == 2) {//crear armadura
                        Armadura nueva = armaduraController.crearArmadura();
                        ArrayList<Armadura> aux = personaje.getArmadura();
                        if (aux == null)//si un personaje no tiene lista de armaduras, se crea una.
                            aux = new ArrayList<>();
                        aux.add(nueva);//se añade a la lista la nueva armadura
                        personaje.setArmadura(aux);
                        Pantalla.imprimir("Armadura  " + nueva.getNombre()  + "  creada");
                    }
                    break;
                case 4://crear debilidades y fortalezas
                    Pantalla.imprimir("1. Añadir Fortaleza");
                    Pantalla.imprimir("2. Añadir Debilidad");
                    Pantalla.imprimir("Otro. Cancelar");
                    o = Pantalla.pedirenteros("Elije una opción: ");
                    if (o == 1) {//añadir fortaleza
                        Fortaleza nueva = fortalezaController.crearFortaleza();
                        ArrayList<Fortaleza> aux = personaje.getFortalezas();
                        if (aux == null)//si un personaje no tiene lista de fortalezas, se crea una.
                            aux = new ArrayList<>();
                        aux.add(nueva);//se añade a la lista la nueva fortaleza
                        personaje.setFortalezas(aux);
                    }
                    else if (o == 2) {
                        Debilidad nueva = debilidadController.crearDebilidad();
                        ArrayList<Debilidad> aux = personaje.getDebilidades();
                        if (aux == null)//si un personaje no tiene lista de debilidades, se crea una.
                            aux = new ArrayList<>();
                        aux.add(nueva);//se añade a la lista la nueva debilidad
                        personaje.setDebilidades(aux);
                    }
                    break;
                case 5://añadir esbirros
                    ArrayList<Esbirro> aux = personaje.getEsbirros();
                    if (aux == null)//si un personaje no tiene lista de esbirros, se crea una.
                        aux = new ArrayList<>();
                    Esbirro esbirro = personaje.crearEsbirros();
                    if (esbirro != null){
                        aux.add(esbirro);//se añade a la lista el nuevo  esbirro
                        personaje.setEsbirros(aux);
                    }
                    break;
                case 6://modifiar estadisticas
                    personaje.modificarDatos();
                    break;
                case 7://salir
                    salir = true;
                    break;
            }
        }
        return personaje;
    }


  
  
}

