package Controladores;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public abstract class Pantalla {// se ha utilizado esta clase auxiliar para poder utillizar el metodos scanner en diversas clases que no podian tenerlo como propio ya que no es serializable y no permitia la serializacion en ficheros

    private static Scanner scanner = new Scanner(System.in);
    public static void imprimir(String texto) {//imprime por pantalla la cadena de caracteres que recibe
        System.out.println(texto);
    }

    public static int pedirenteros(String texto) {//imprime por pantalla la cadena de texto que recibe y lee enteros por teclado
        imprimir(texto);
        return scanner.nextInt();
    }

    public static String pedircadena(String texto) {//imprime por pantalla la cadena de texto que recibe y lee cadenas de caracteres por teclado
        imprimir(texto);
        return scanner.nextLine();
    }
}
