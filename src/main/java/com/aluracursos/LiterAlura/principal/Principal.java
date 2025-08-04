package com.aluracursos.LiterAlura.principal;

import com.aluracursos.LiterAlura.ui.MenuPrincipal;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class Principal {
    //  Iniciar Instancias
    private final MenuPrincipal menuPrincipal = new MenuPrincipal();


    //  Metodos
    public void run() {

        //  Titulo Principal
        System.out.println(VERDE + "╔════════════════════════════════════════════╗");
        System.out.println("║   📚 Bienvenido/a a la App LiterAlura 📚   ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);

        //  Bucle principal
        while (true) {
            int opc = menuPrincipal.menuInicial();
            switch (opc) {
                case 1, 2, 3, 4, 5: break;
            }

            //  Rompe el bucle por seleccionar la opción salir
            if (opc == 6) {
                break;
            }
        }

        //  Fin del programa
        System.out.println(ROJO + "\n\t* Fin de la Ejecución de la aplicación *");
        System.out.println("***************************************************" + RESET);
    }
}
