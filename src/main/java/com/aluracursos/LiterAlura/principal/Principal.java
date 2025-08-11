package com.aluracursos.LiterAlura.principal;

import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.AutorService;
import com.aluracursos.LiterAlura.service.LibroService;
import com.aluracursos.LiterAlura.service.api.ConsumoAPI;
import com.aluracursos.LiterAlura.service.api.ConvierteDatos;
import com.aluracursos.LiterAlura.ui.Menu;

import java.util.Scanner;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class Principal {
    //  Iniciar Instancias
    private final Scanner scan = new Scanner(System.in);
    private final Menu menu = new Menu();
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final LibroService libroService;
    private final AutorService autorService;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.libroService = new LibroService(consumoAPI, conversor, libroRepository, autorRepository);
        this.autorRepository = autorRepository;
        this.autorService = new AutorService(autorRepository);
    }

    //  Metodo Principal
    public void run() {

        //  Titulo Principal
        System.out.println(VERDE + "╔════════════════════════════════════════════╗");
        System.out.println("║   📚 Bienvenido/a a la App LiterAlura 📚   ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);

        //  Bucle principal
        while (true) {
            int opc = menu.menuInicial();
            switch (opc) {
                case 1:
                    libroService.buscarLibroPorTitulo();
                    break;
                case 2:
                    libroService.MostrarLibrosGuardados();
                    break;
                case 3:
                    autorService.buscarAutorPorNombre();
                    break;
                case 4:
                    autorService.MostrarAutoresGuardados();
                    break;
                case 5:
                    autorService.AutoresVivosYear();
                    break;
                case 6:
                    libroService.ListarLibrosPorIdioma();
                    break;
                case 7:
                    libroService.MostrarTop10Libros();
                    break;
            }

            //  Rompe el bucle por seleccionar la opción salir
            if (opc == 9) {
                break;
            }
        }

        scan.close();
        //  Fin del programa
        System.out.println(ROJO + "\n\t* Fin de la Ejecución de la aplicación *");
        System.out.println("***************************************************" + RESET);
    }
}
