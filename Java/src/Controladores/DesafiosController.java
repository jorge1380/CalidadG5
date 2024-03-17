package Controladores;

import ORIGEN.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.time.LocalDate;

public class DesafiosController {
    private List<Desafio> listaDesafio = new ArrayList<Desafio>();
    public Desafio iniciarDesafio(Desafio desafio, List<Usuario> listausuarios) throws IOException {
        Personaje jugador1 = desafio.getUserUno().getPersonaje();
        Personaje jugador2 = desafio.getUserDos().getPersonaje();
        int saludjugador1 = jugador1.getSalud();
        int saludjugador2 = jugador2.getSalud();
        int saludEsbirrosAtq = jugador1.saludEsbirros();
        int saludEsbirrosDef = jugador2.saludEsbirros();
        int rondas=0;
        Pantalla.imprimir("El combate va a empezar...");
    
        while (saludjugador1 > 0 && saludjugador2 > 0) {
            saludjugador1 = realizarTurno(desafio, jugador1, jugador2, saludjugador1, saludEsbirrosDef);
            saludjugador2 = realizarTurno(desafio, jugador2, jugador1, saludjugador2, saludEsbirrosAtq);
            Pantalla.imprimir("Fin ronda " + rondas + ". Vida " + jugador1.getNombre() + ": " + saludjugador1 + ". Vida " + jugador2.getNombre() + ": " + saludjugador2 + ".");
            rondas++;
        }
    
        return guardarDesafio(desafio, listausuarios, jugador1, jugador2, saludjugador1, saludjugador2, rondas);
    }
    
    private int realizarTurno(Desafio desafio, Personaje atacante, Personaje defensor, int saludAtacante, int saludEsbirrosDef) {
        Pantalla.imprimir("Turno " + atacante.getNombre());
        int ataqueAtacante = calcularAtaque(desafio, atacante);
        int defensaDefensor = calcularDefensa(desafio, defensor);
        if (ataqueAtacante >= defensaDefensor) {
            saludAtacante = realizarAtaque(atacante, defensor, saludAtacante, saludEsbirrosDef);
        }
        return saludAtacante;
    }
    
    private int calcularAtaque(Desafio desafio, Personaje jugador) {
        int ataqueJugador = jugador.calcularAtaque();
        ataqueJugador += calcularModificadorCombate(desafio, jugador,true);
        return calcularPotencial(ataqueJugador);
    }
    
    private int calcularDefensa(Desafio desafio, Personaje jugador) {
        int defensaJugador = jugador.calcularDefensa();
        defensaJugador += calcularModificadorCombate(desafio, jugador,false);
        return calcularPotencial(defensaJugador);
    }
    
    private int realizarAtaque(Personaje atacante, Personaje defensor, int saludAtacante, int saludEsbirrosDef) {
        int ataque = atacante.calcularAtaque();
        int defensa = defensor.calcularDefensa();
        int daño = ataque - defensa;
        if (daño > 0) {
            saludAtacante -= daño;
        }
        else {
            saludEsbirrosDef -= daño;
        }
        return saludAtacante;
    }
    
    private Desafio guardarDesafio(Desafio desafio, List<Usuario> listausuarios, Personaje jugador1, Personaje jugador2, int saludjugador1, int saludjugador2, int rondas) throws IOException {
        if (saludjugador1 > 0) {
            desafio.setGanador(1);
            desafio.getUserUno().getPersonaje().setOro(desafio.getUserUno().getPersonaje().getOro() + desafio.getOroApostado());
            desafio.getUserDos().getPersonaje().setOro(desafio.getUserDos().getPersonaje().getOro() - desafio.getOroApostado());
        }
        else {
            desafio.setGanador(2);
            desafio.getUserUno().getPersonaje().setOro(desafio.getUserUno().getPersonaje().getOro() - desafio.getOroApostado());
            desafio.getUserDos().getPersonaje().setOro(desafio.getUserDos().getPersonaje().getOro() + desafio.getOroApostado());
        }
        desafio.setFecha(LocalDate.now());
        desafio.setRondas(rondas);
        guardardesafiocomp(listaDesafio,desafio);
        return desafio;
    }

    public int calcularModificadorCombate(Desafio desafio, Personaje jugador, boolean esAtaque) {
        int modificador = 0;
        switch (desafio.getModificador()) {
            case 1:
                if ((esAtaque && jugador instanceof Cazador) || (!esAtaque && jugador instanceof Vampiro)) {
                    modificador = 1;
                }
                break;
            case 2:
                if ((esAtaque && jugador instanceof Licantropo) || (!esAtaque && jugador instanceof Cazador)) {
                    modificador = 1;
                }
                break;
            case 3:
                if ((esAtaque && jugador instanceof Vampiro) || (!esAtaque && jugador instanceof Licantropo)) {
                    modificador = 1;
                }
                break;
        }
        return modificador;
    }

    public void guardardesafiocomp(List<Desafio>lista, Desafio desafio) throws IOException {//guarda en un archivo los desafíos completados
        File file = new File("listaDesafiosCompletados.dat");
        lista.add(desafio);
        if (file.exists()){//si el archivo no existe, se crea
            file.delete();
            file.createNewFile();
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaDesafiosCompletados.dat"));
        for (int i = 0; i <lista.size(); i++) {
            if (lista.get(i) == null)
                continue;
            Desafio des = lista.get(i);
            oos.writeObject(des);
        }
        oos.close();
    }
    public List<Desafio> historial(){
        return  this.listaDesafio;
    }
    public void aceptarDesafio(List<Usuario> listausuario, Usuario u) throws IOException {
        if (u.getDesafio() != null){//si el usuario tiene algún desafío pendiente se le notifica
            UsuarioController ucontroller = new UsuarioController();
            Usuario u1 = u.getDesafio().getUserUno();
            Usuario u2 = u.getDesafio().getUserDos();
            Pantalla.imprimir("Hay un nuevo desafio de " + u.getDesafio().getUserUno().getNickname() + " con una apuesta de " + u.getDesafio().getOroApostado() + " de oro.");
            int respuesta = Pantalla.pedirenteros("¿Desea aceptar el desafio? Si no lo acepta, deberá pagar el 10% de la apuesta. 0 = No ; 1 = Si");
            if (respuesta == 1) {//si acepta el desafío, este da comienzo
                Desafio d = this.iniciarDesafio(u.getDesafio(), listausuario);
            }
            else{//si lo rechaza paga el 10% de la apuesta
                Usuario usuario = ucontroller.seleccionarUsuario(listausuario, u2.getNombre());
                usuario.getPersonaje().setOro((int) Math.ceil((usuario.getPersonaje().getOro() - u.getDesafio().getOroApostado() * 0.10)));
                usuario = ucontroller.seleccionarUsuario(listausuario, u1.getNombre());
                usuario.getPersonaje().setOro((int) Math.ceil((usuario.getPersonaje().getOro() + u.getDesafio().getOroApostado() * 0.10)));
            }
            //se borra el desafío como pendiente de acpetar
            Usuario usu1 = ucontroller.seleccionarUsuario(listausuario, u1.getNombre());
            Usuario usu2 = ucontroller.seleccionarUsuario(listausuario, u2.getNombre());
            usu1.setDesafio(null);
            usu2.setDesafio(null);
        }
    }
    //cargamos la lista con los desafios y la guardamos en el controlador
    public void cargarDatos() throws IOException, ClassNotFoundException {
        this.listaDesafio = cargarDesafios();
    }
    public void cargarDatos(List<Desafio> listaDesafios) throws IOException, ClassNotFoundException {
        guardarDesafios(listaDesafios);
        this.listaDesafio=listaDesafios;
    }
    //creamos los desafios
    private List<Desafio> cargarDesafios() throws IOException, ClassNotFoundException {
        List<Desafio> lista = new ArrayList<Desafio>();
        try {
            File file = new File("listaDesafios.dat");
            if (!file.exists()) {
                file.createNewFile();
                return lista;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaDesafios.dat"));
            Object aux = ois.readObject();
            while (aux != null) {
                if (aux instanceof Desafio)
                    lista.add((Desafio) aux);
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e1) {
            //Fin del fichero.
        }
        return lista;
    }
    //guardamos la lista de desafios en el fichero
    public List<Desafio> guardarDatos() throws  IOException{
        guardarDesafios(this.listaDesafio);
        return listaDesafio;
    }
    //guardamos los datos de los desafios creados
    public void guardarDesafios(List<Desafio>lista) throws IOException{
        File file = new File("listaDesafios.dat");
        if (file.exists()){
            file.delete();
            file.createNewFile();
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaDesafios.dat"));
        for (int i = 0; i <lista.size(); i++) {
            if (lista.get(i) == null)
                continue;
            Desafio desafio = lista.get(i);
            oos.writeObject(desafio);
        }
        oos.close();
    }
    public void agregarDesafio(Desafio desafio) throws IOException, ClassNotFoundException {
        cargarDatos(); //refresca cambios
        this.listaDesafio.add(desafio); //añade nuevo desafío al fichero
        guardarDatos();
    }
    public void validarDesafio(List<Usuario> listausuario) throws IOException, ClassNotFoundException {//operación exclusiva de operadores
        List<Desafio> lista = cargarDesafios();
        if (lista.size() > 0) {
            int i = 0;
            for (Desafio d : lista) {//se muestran por pantalla todos los desafíos pendientes
                Pantalla.imprimir(i + (". ") + d.getUserUno().getNombre() + (" vs ") + d.getUserDos().getNombre());
                i += 1;
            }
            int index = Pantalla.pedirenteros("Indique el desafio a validar");
            Desafio desafio = lista.get(index);//selecciona el desafío validado
            //selecciona la característica que implementa las fortalezas y debilidades en combate
            Pantalla.imprimir("1. Día Soleaddo.");
            Pantalla.imprimir("2. Luna llena.");
            Pantalla.imprimir("3. Eclipse lunar");
            int opcion = 0;
            while (opcion != 1 && opcion != 2 && opcion != 3){
                opcion = Pantalla.pedirenteros("Seleccione modificador");
                if (opcion != 1 && opcion != 2 && opcion != 3){
                    Pantalla.imprimir("No es una opción válida");
                }
            }
            //se valida todo el desafío y se envía la notificación al usuario desafiado.
            desafio.setModificador(opcion);
            lista.remove(index);
            this.listaDesafio = lista;
            guardarDatos();
            Usuario u2 = desafio.getUserDos();
            UsuarioController ucontroller = new UsuarioController();
            Usuario u = ucontroller.seleccionarUsuario(listausuario, u2.getNombre());
            u.setDesafio(desafio);
        }
        else{
            Pantalla.imprimir("No hay desafíos por validar.");
        }
    }

    
    public void pagarGanador(String nombreGanador, int cantidadGanada, List<Usuario> listausuarios) {
        UsuarioController ucontroller = new UsuarioController();
        Usuario ganador = ucontroller.seleccionarUsuario(listausuarios, nombreGanador);
        if (ganador != null) {
            ganador.getPersonaje().setOro(ganador.getPersonaje().getOro() + cantidadGanada);
        }
    }



    public int calcularPotencial(int valor) { //se calcula el potencial de un personaje
        int exito = 0;
        for (int i = 1; i <= valor; i++) { //se generan tantos números entre 1 y 6 como el valor del ataque o defensa
            Random random = new Random();
            int rango = random.nextInt(7);
            if (rango == 5 || rango == 6) { //si el número es 5 o 6, se considera un éxito
                exito++;
            }
        }
        return exito; //se devuelve el número de éxitos
    }
    
}
