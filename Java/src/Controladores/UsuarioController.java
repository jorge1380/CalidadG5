package Controladores;
import ORIGEN.Desafio;
import ORIGEN.Usuario;
import  ORIGEN.Personaje;
import Controladores.ArmaController;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;


public class UsuarioController {

    public void verRanking() throws IOException, ClassNotFoundException {//permite al usuario acceder al ranking de los jugadores basado en la cantidad de oro total
        Appcontroller aux = new Appcontroller();
        aux.Ranking();
    }

    public Usuario menuUsuario(Usuario usuario, List<Usuario> usuarios) throws IOException, ClassNotFoundException {//muestra el menu de los usuarios
        boolean salir = false;
        if (usuario.getDesafio() != null){ //el usuario tiene desafios pendientes
            DesafiosController dcontroller = new DesafiosController(); // crea una instancia de desafios controller para poder utilizar su smétodos
            dcontroller.cargarDatos();// carga los datos del deafio
            dcontroller.aceptarDesafio(usuarios, usuario);
        }
        while (!salir) {
            Pantalla.imprimir(" ------MENU USUARIO------");
            Pantalla.imprimir("  1.Crear personaje");
            Pantalla.imprimir("  2.Eliminar personaje");
            Pantalla.imprimir("  3.Cambiar equipo activo");
            Pantalla.imprimir("  4.Lanzar desafio");
            Pantalla.imprimir("  5.Resultados combates anteriores");
            Pantalla.imprimir("  6.Ver Ranking global");
            Pantalla.imprimir("  7.Darse de baja");
            Pantalla.imprimir("  8.Cerrar sesion");
            int option = Pantalla.pedirenteros("Opcion");
            switch (option) {
                case 1://crear personaje
                    Personaje personaje = usuario.getPersonaje();
                    if (personaje == null) { //el usuario no tiene ningun personaje creado
                        PersonajeController controller = new PersonajeController();
                        controller.registrarPersonaje(usuario);
                    } else {
                        Pantalla.imprimir("Ya tiene un personaje creado");
                        break;
                    }
                    break;
                case 2://elimina el personaje del usuario.
                    usuario.setPersonaje(null);
                    Pantalla.imprimir("Personaje eliminado con exito");
                    break;
                case 3:// cambiar equipo activo
                    personaje = usuario.getPersonaje();
                    if (personaje == null) {
                        Pantalla.imprimir("No tienes personaje creado");
                    break;
                    }
                    Pantalla.imprimir("1.Cambiar arma activa");
                    Pantalla.imprimir("2.Cambiar armadura activa");
                    Pantalla.imprimir("3.Cancelar");
                    int option1 = Pantalla.pedirenteros("opcion");
                    switch (option1) {
                        case 1://cambia las armas activas por algun arma disponible en su lista de armas
                            ArmaController contoller = new ArmaController();
                            personaje = contoller.cambiarArma(personaje);
                            usuario.setPersonaje(personaje);
                            break;
                        case 2://cambia la armadura activa por una de la lista de armaduras
                            ArmaduraController controller = new ArmaduraController();
                            personaje = controller.cambiarArmadura(personaje);
                            usuario.setPersonaje(personaje);
                            break;
                        case 3://cancelar
                            break;
                        }
                    break;
                case 4://desafia a otro usuario a un combate
                    for (Usuario a : usuarios){
                        Pantalla.imprimir("Usuario: "+a.getNombre());
                    }
                    if (usuario.getPersonaje() != null) {
                        desafiar(usuario, usuarios);
                    } else {
                        Pantalla.imprimir("No puedes desafiar, necesitas un personaje");
                    }
                    break;
                case 5://muestra un registro con los resultados de los deasfios anteriores.
                    verCombate(usuario);
                    break;
                case 6://Ver ranking
                    verRanking();
                    break;
                case 7://Dar de baja usuario
                    usuario = null;
                    Pantalla.imprimir("Se elimino el usuario");
                    salir = true;
                    break;
                case 8://cerrar sesion
                    salir = true;
                    break;
            }
        }
        return usuario;
    }




    public List<Usuario> menuOperador(List<Usuario> listaUsuarios, ORIGEN.Operador usu) throws IOException, ClassNotFoundException {//muestra el menu para los operadores
        boolean salir = false;
        while (!salir){
            Pantalla.imprimir(" ------MENU OPERADOR------");
            Pantalla.imprimir(" 1.Modificar personaje");
            Pantalla.imprimir(" 2.Validar desafio");
            Pantalla.imprimir(" 3.Banear Usuario");
            Pantalla.imprimir(" 4.Desbanear Ususario");
            Pantalla.imprimir(" 5.Darse de baja");
            Pantalla.imprimir(" 6.Cerrar sesion");

            int o = Pantalla.pedirenteros("Elegir opcion");

            switch (o){
                case 1://modifica el personaje de un usuario
                    for (Usuario a: listaUsuarios){
                        if (a.getNombre().compareTo("OPERADOR") != 0) {
                            Pantalla.imprimir(a.getNombre());
                        }
                    }
                    Pantalla.imprimir("Escribe...  cancelar  ... para Salir");
                    String nombre = Pantalla.pedircadena("Usuario a buscar");
                    if (nombre.equals("cancelar")){
                        Pantalla.imprimir("Saliendo...");
                        break;
                    }
                    Usuario u = seleccionarUsuario(listaUsuarios,nombre);

                    if (u.getPersonaje() == null){
                        Pantalla.imprimir("El usuario no tiene personaje");
                        break;
                    }
                    PersonajeController pjController = new PersonajeController();
                    listaUsuarios.remove(u);
                    u.setPersonaje(pjController.modificarPersonaje(u.getPersonaje()));
                    listaUsuarios.add(u);
                    break;
                case 2://valida desafios pendientes
                    DesafiosController dcontroller = new DesafiosController();
                    dcontroller.validarDesafio(listaUsuarios);
                    break;
                case 3://banear usuarios
                    String banear = Pantalla.pedircadena("Nombre del Usuario a banear:");
                    Usuario baneado = seleccionarUsuario(listaUsuarios,banear);
                    if (baneado != null){
                        baneado.setBaneado(true);
                    }
                    break;
                case 4://desbanear usuarios
                    banear = Pantalla.pedircadena("Nombre del Usuario a desbanear:");
                    baneado = seleccionarUsuario(listaUsuarios,banear);
                    if (baneado != null){
                        baneado.setBaneado(false);
                    }
                    break;
                case 5: //darse de baja
                    usu = null;
                    Pantalla.imprimir("Se elimino el usuario");
                    salir = true;
                    break;
                case 6: //cerrar sesion
                    salir = true;
                    break;
            }
        }
        return listaUsuarios;
    }

    public Usuario seleccionarUsuario(List<Usuario> listaUsuarios, String nombre) {//devuelve un usuario de la lista de registrados, buscándolo por nombre
        int size = listaUsuarios.size();
        if (size == 0){
            Pantalla.imprimir("No hay Usuarios registrados");
            return null;
        }
        for (Usuario u : listaUsuarios){     // busca el usuario en la lista de usuarios comparando el nombre del usuario
            if (u.getNombre().equals(nombre)){
                return u;  // si encuentra coincidencia con el nombre devuelve el usuario
            }
        }
        Pantalla.imprimir("No existe tal Usuario");
        return null;
    }
    public void verCombate(Usuario user){//permite ver los resultados de los combates anteriores
        List<Desafio> lista = new ArrayList<Desafio>();
        try {
            File file = new File("listaDesafiosCompletados.dat");
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaDesafiosCompletados.dat"));
            Object aux = ois.readObject();
            while (aux != null) {
                if (aux instanceof Desafio)
                    lista.add((Desafio) aux);
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e1) {
            //Fin del fichero.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(Desafio d : lista){
            if (d.getUserUno().getNombre().compareTo(user.getNombre()) == 1 || d.getUserDos().getNombre().compareTo(user.getNombre()) == 1) {
                Pantalla.imprimir(d.getUserUno().getNombre() + " vs " + d.getUserDos().getNombre() + ". Oro ganado: " + d.getOroGanado() + ". Fecha: " + d.getFecha() + " Rondas: " + d.getRondas() + ".");
            }
        }
        if (lista.isEmpty()){
            Pantalla.imprimir("No hay combates anteriormente realizados");
        }
    }
    public void desafiar(Usuario user, List<Usuario> listaUsuarios) throws IOException, ClassNotFoundException {//envia un desafio al operador para confirmar.
        boolean encontrado = false;
        boolean aux = true;
        String desafiado = Pantalla.pedircadena("Indica al usuario al que quieres desafiar");
        for (Usuario a : listaUsuarios) {
            if (a.getNickname().equals(desafiado)){
                encontrado = true;
                if(a.getPersonaje()!=null){
                    if (a.getDesafio() != null) {
                        Pantalla.imprimir("El usuario ya tiene un desafío");
                    } else {
                        DesafiosController desafioController = new DesafiosController();
                        int oroApostado = 0;
                        while (aux) {
                            oroApostado = (Pantalla.pedirenteros("Cantidad de oro a apostar:"));
                            if (user.getPersonaje().getOro() > oroApostado) {
                                aux = false;
                            }
                            else{
                                Pantalla.imprimir("El oro apostado es mayor al oro disponible, ingrese una cantidad menor");
                            }
                        }
                        Desafio desafio = new Desafio(user, a, oroApostado);
                        desafio.setUserUno(user);
                        desafio.setUserDos(a);
                        desafio.setFecha(LocalDate.now());
                        desafioController.agregarDesafio(desafio);
                        desafio.getOroApostado();
                        desafioController.guardarDatos();
                        Pantalla.imprimir("Desafio guardado a espera de la confirmacion");
                    }
                }
                else{
                    Pantalla.imprimir("No se puede desafiar al usuario "+ a.getNickname()+ " porque no tiene personajes");
                    encontrado=false;
                }
            }
        }
        if (!encontrado){
            Pantalla.imprimir("El usuario no existe");
        }
    }
}

