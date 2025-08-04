package com.aluracursos.LiterAlura.principal;

import com.aluracursos.LiterAlura.model.Datos;
import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.service.api.ConsumoAPI;
import com.aluracursos.LiterAlura.service.api.ConvierteDatos;
import com.aluracursos.LiterAlura.ui.MenuPrincipal;

import java.util.Optional;
import java.util.Scanner;

import static com.aluracursos.LiterAlura.util.Constantes.URL_API;
import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class Principal {
    //  Iniciar Instancias
    private final Scanner scan = new Scanner(System.in);
    private final MenuPrincipal menuPrincipal = new MenuPrincipal();
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();


    //  Metodos
    //  M. - Principal
    public void run() {

        //  Titulo Principal
        System.out.println(VERDE + "╔════════════════════════════════════════════╗");
        System.out.println("║   📚 Bienvenido/a a la App LiterAlura 📚   ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);

        //  Bucle principal
        while (true) {
            int opc = menuPrincipal.menuInicial();
            switch (opc) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2, 3, 4, 5:
                    break;
            }

            //  Rompe el bucle por seleccionar la opción salir
            if (opc == 6) {
                break;
            }
        }

        scan.close();
        //  Fin del programa
        System.out.println(ROJO + "\n\t* Fin de la Ejecución de la aplicación *");
        System.out.println("***************************************************" + RESET);
    }

    //  M. - Busca el libro por título
    private void buscarLibroPorTitulo() {
        System.out.print(AMARILLO + "\nIngrese el nombre del libro que desea buscar: ");
        var tituloLibro = scan.nextLine();

        var json = consumoAPI.ObtenerDatos(URL_API + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<Libro> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro Encontrado ");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }
    }
}
