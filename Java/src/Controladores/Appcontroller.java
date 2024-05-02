package Controladores;

import ORIGEN.Operador;
import ORIGEN.Personaje;
import ORIGEN.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Appcontroller{
    private List<Usuario> usuarios;
    public Appcontroller(){
        usuarios = new ArrayList<>();
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void iniciarSesion() throws IOException, ClassNotFoundException {
        String usuario;
        String contraseña;
        usuario = Pantalla.pedircadena("Usuario");
        contraseña = Pantalla.pedircadena("Contraseña");
        boolean encontrado = false;
        for (Usuario usu: usuarios){
            if (usu == null){
                continue;
            }
            if (usu.getNickname().equals(usuario) && usu.getContrasena().equals(contraseña)){
                encontrado = true;
                UsuarioController usuarioController = new UsuarioController();
                if(usu.isBaneado()){//Comprobar si esta baneado el personaje
                    Pantalla.imprimir("Jugador baneado");
                    Pantalla.imprimir("No puede iniciar Sesión");
                    Pantalla.imprimir("Contacte Operador");
                    start();
                }
                if (usu instanceof Operador){// si el usuario es un operador muestra el menu de operador
                    List<Usuario> modificados = usuarioController.menuOperador(usuarios, (Operador) usu);
                    usuarios = modificados;
                    guardarDatos();
                }
                else {// si usuario es un usuario normal muestrael menu de los usuarios
                    Usuario modificado = usuarioController.menuUsuario(usu, usuarios);
                    usuarios.remove(usu);
                    if (modificado != null)
                        usuarios.add(modificado);
                }
                break;
            }
        } if (!encontrado){
            Pantalla.imprimir("No existe el usuario...");
            Pantalla.imprimir(("Regresando al menu"));
        }
    }

    public void registrarse() throws IOException {
        Usuario usu = new Usuario();
        usu.setNombre(Pantalla.pedircadena("Introduce el Nombre"));
        String nick = Pantalla.pedircadena("Introduce el Nickname que también será tu usuario");
        boolean repetido = false;
        boolean encontrado;
        int i;
        while (!repetido){
            encontrado = false;
            i = 0;
            while(!encontrado && i < this.usuarios.size()){
                Usuario aux = this.usuarios.get(i);
                encontrado = aux.getNickname().compareTo(nick) == 0;
                i++;
            }
            repetido = encontrado;
            if (repetido) {
                nick = Pantalla.pedircadena("Ese nickname ya está escogido, por favor ingrese uno nuevo...");
                repetido = false;
            }
            else{
                repetido = true;
            }
        }
        usu.setNickname(nick);
        usu.setContrasena(Pantalla.pedircadena("Introduce la Contraseña"));
        usu.setBaneado(false);

        if (usu.getNickname().equals("") || usu.getContrasena().equals("") || usu.getNombre().equals("")){//obliga al usuario a rellenar todos los campos de registro
            Pantalla.imprimir("Rellena todos los campos");
            return;
        }
        boolean UsuarioExistente = false;
        if (usuarios.isEmpty()){
            usuarios.add(usu);
            Pantalla.imprimir("Usuario Registrado");
            return;
        }
        for (Usuario aux :usuarios){
            if (aux.getNickname().equals(usu.getNickname())){
                UsuarioExistente = true;
                break;
            }
        }
        if (UsuarioExistente){
            Pantalla.imprimir("El Usuario ya existe");
        }
        else{
            usuarios.add(usu);
            Pantalla.imprimir("Usuario creado con éxito");
        }
    }

    public void cargarDatos() throws IOException, ClassNotFoundException {
        this.usuarios = cargarUsuarios();
        if (usuarios.isEmpty()) {//si la lista de usuarios esta vacia agregamos el usuario operador diractamente desde el método
            Operador operador = new Operador();
            operador.setNickname("OPERADOR");
            operador.setNombre("OPERADOR");
            operador.setContrasena("OPERADOR");
            operador.setBaneado(false);
            operador.setPersonaje(null);
            usuarios.add(operador);
        }
    }

    private List<Usuario> cargarUsuarios() throws IOException,ClassNotFoundException {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            File file = new File("listaUsuarios.dat");
            if (!file.exists()){
                file.createNewFile();
                return lista;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaUsuarios.dat"));
            Object aux = ois.readObject();
            while (aux!=null) {
                if (aux instanceof Operador)
                    lista.add((Operador) aux);
                else if (aux instanceof Usuario)
                    lista.add((Usuario) aux);
                aux = ois.readObject();
            }
            ois.close();
        }
        catch (EOFException e1)
        {
            //Fin del fichero.
        }
        return lista;
    }
    public void guardarDatos() throws  IOException{
        guardarUsuarios(this.usuarios);
    }
    public void guardarUsuarios(List<Usuario>lista) throws IOException{
        File file = new File("listaUsuarios.dat");
        if (file.exists()){
            file.delete();
            file.createNewFile();
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaUsuarios.dat"));
        for (int i = 0; i <lista.size(); i++) {
            if (lista.get(i)==null)
                continue;
            Usuario usuario = lista.get(i);
            oos.writeObject(usuario);
        }
        oos.close();
    }

    public void menu(){
        Pantalla.imprimir("**----Bienvenido Dark Chronicles----**");
        logo();
        Pantalla.imprimir("Seleccione que quiere hacer:");
        Pantalla.imprimir("  1.Iniciar Sesión");
        Pantalla.imprimir("  2.Registrarse");
        Pantalla.imprimir("  3.Salir");
        Pantalla.imprimir("");
    }
    public void start() throws IOException, ClassNotFoundException {
        boolean salir = false;
        while(!salir) {
            menu();
            cargarDatos();
            int option = Pantalla.pedirenteros("Seleccione opcion");
            while ((option != 1) && (option != 2) && (option != 3)) {
                Pantalla.imprimir("La opción escogida no es válida.");
                Pantalla.imprimir("");
                menu();
                option = Pantalla.pedirenteros("Seleccione opcion");
            }
            switch (option) {
                case 1:
                    this.iniciarSesion();
                    break;
                case 2:
                    this.registrarse();
                    break;
                case 3:
                    salir = true;
                    Pantalla.imprimir("Cerrando aplicacion");
                    break;
            }
            guardarDatos();
        }
    }

    public void Ranking() throws IOException, ClassNotFoundException {
        cargarDatos();
        List<Usuario> aux = new ArrayList<>();
        Pantalla.imprimir("RANKING ACTUAL (desde último reinicio): ");
        int a;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getPersonaje() != null) {
                aux.add(usuarios.get(i));
            }
        }
        Comparator<Usuario> comparador = new Comparator<Usuario>() {
            @Override
            public int compare(Usuario usuario1, Usuario usuario2) {
                return Integer.compare(usuario1.getPersonaje().getOro(), usuario2.getPersonaje().getOro());
            }
        };
        Collections.sort(aux, comparador);
        a = aux.size() - 1;
        for (int i = 0; i < aux.size(); i++){
            Pantalla.imprimir(Integer.toString(i + 1) + ".- " + aux.get(a).getNickname() + " " + aux.get(a).getPersonaje().getOro());
            a -= 1;
        }
    }

    public void logo(){//se podria mejorar el dibujo del logo con cualquier otro dibujo.
     Pantalla.imprimir("            |`--,___,--'|");
     Pantalla.imprimir("            |          C|" );
     Pantalla.imprimir("            |  )     ( R|");
     Pantalla.imprimir("            |D ((,-,)) O|");
     Pantalla.imprimir("            |A `|v v|' N|");
     Pantalla.imprimir("            |R    Y.Y  I|");
     Pantalla.imprimir("            |K    {.}  C|");
     Pantalla.imprimir("            |     {.}  L|");
     Pantalla.imprimir("            |     ~    E/");
     Pantalla.imprimir("            TTTTTTTTTTTT");

    }
}

