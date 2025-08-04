package com.aluracursos.LiterAlura.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class MenuPrincipal {

    //  Intancias
    private final Scanner scan;

    //  Variables
    private final String[] menu_inicio = {
            "Buscar libro por título",
            "Listar libros registrados",
            "Listar autores registrados",
            "Listar autores vivos en un determinado año",
            "Listar libros por idioma",
            "Salir"};

    //  Constructor
    public MenuPrincipal() {
        this.scan = new Scanner(System.in);
    }

    //  Metodos

    //  M. - Muestra el menu inicial
    public int menuInicial() {
        System.out.println(MAGENTA + "\n♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦  MENÚ  ♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
        for (int i = 0; i < menu_inicio.length; i++) {
            if (i != menu_inicio.length - 1) {
                System.out.printf(CYAN + "\t%d. %s\n", i + 1, menu_inicio[i]);
            } else {
                System.out.printf(ROJO + "\t%d. %s\n" + RESET, i + 1, menu_inicio[i]);
            }
        }
        String msg = AMARILLO + "\n\t-> ¿Cuál es la opción que desea hacer?: ";
        return OpcionAValidar(msg, menu_inicio.length);
    }

    //  M. - Válida que la opcion ingresada sea correcta
    private int OpcionAValidar(String msg, int numOpciones) {
        while (true) {
            System.out.print(msg);
            try {
                int opc = scan.nextInt();
                if (opc < 1 || opc > numOpciones) {
                    System.out.println("\t¡¡Opción incorrecta!!\n\tVuelva a intentarlo.");
                    continue;
                }
                // Devuelve la respuesta correspondiente
                return opc;
            } catch (InputMismatchException e) {
                System.out.println("\t¡¡Error: debe ingresar un número!!\n\tVuelva a intentarlo.");
                scan.nextLine(); // Limpiar el buffer
            }
        }
    }
}
