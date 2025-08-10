package com.aluracursos.LiterAlura.ui;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class Menu {

    //  Intancias
    private final ValidarEntrada validarEntrada = new ValidarEntrada();

    //  Variables
    private final String[] menu_inicio = {
            "Buscar libro por título",
            "Listar libros registrados",
            "Listar autores registrados",
            "Listar autores vivos en un determinado año",
            "Listar libros por idioma",
            "Salir"};

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
        return validarEntrada.OpcionAValidar(msg, menu_inicio.length);
    }
}
