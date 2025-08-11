package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.model.*;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.api.ConsumoAPI;
import com.aluracursos.LiterAlura.service.api.ConvierteDatos;
import com.aluracursos.LiterAlura.ui.ValidarEntrada;
import com.aluracursos.LiterAlura.util.IdiomaUtil;
import com.aluracursos.LiterAlura.util.PrintUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.aluracursos.LiterAlura.util.Constantes.URL_API;
import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class LibroService {
    //  Iniciar Instancias
    private final Scanner scan = new Scanner(System.in);
    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos conversor;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final ValidarEntrada validarEntrada = new ValidarEntrada();

    //  Variables
    List<LibroT> librosGuardados;

    //  Constructor
    public LibroService(ConsumoAPI consumoAPI, ConvierteDatos conversor, LibroRepository repository, AutorRepository autorRepository) {
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.libroRepository = repository;
        this.autorRepository = autorRepository;
    }

    //  M. - Busca el libro por título
    public void buscarLibroPorTitulo() {
        System.out.print(AMARILLO + "\n\t\tIngrese el nombre del libro que desea buscar: ");
        var tituloLibro = scan.nextLine();

        var json = consumoAPI.ObtenerDatos(URL_API + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosResponse.class);
        Optional<LibroRes> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            LibroRes libroRes = libroBuscado.get();
            GuardarLibro(libroRes);
        } else {
            System.out.println(ROJO + "\t\t❌ Libro no encontrado");
        }
    }

    //  M. - Guarda el libro encontrado en la base de datos, además de guardar y hacer la relacion de sus autores
    private void GuardarLibro(LibroRes libroRes) {
        // Buscar si ya existe por ID Gutendex
        Optional<LibroT> libroExistente = libroRepository.findByIdGutendex(libroRes.idGutendex());

        if (libroExistente.isPresent()) {
            System.out.println(ROJO + "\t\t❗ El libro ya está registrado: " + RESET
                    + libroExistente.get().getTitulo());
            return;
        }

        System.out.println(VERDE + "\t\t✅ Libro Nuevo Encontrado ");
        LibroT libroT = new LibroT(libroRes);
        Set<AutorT> autores = new HashSet<>();

        // Obtener o crear autores
        for (AutorRes autorRes : libroRes.autores()) {
            AutorT autor = autorRepository.findByNombre(autorRes.nombre())
                    .orElseGet(() -> autorRepository.save(new AutorT(autorRes)));
            autores.add(autor);
        }

        libroT.setAutor(autores);
        libroRepository.save(libroT);

        System.out.println("\t\t✅ Libro guardado correctamente");
        System.out.println(PrintUtil.imprimirLibro(libroT));
    }

    //  M. - Muestra la lista de libros guardados en la DB
    public void MostrarLibrosGuardados() {
        librosGuardados = libroRepository.findAllConAutores();
        if (librosGuardados.isEmpty()) {
            System.out.println(ROJO + "\t\t❗❗ Aún no hay libros registrados.\n\t\t🧐 Ingrese un primer libro👍👍");
            return;
        }
        librosGuardados.forEach(libroT -> System.out.println(PrintUtil.imprimirLibro(libroT)));
    }

    //  M. - Lista los libros existentes de la DB po idioma
    public void ListarLibrosPorIdioma() {
        List<String> idiomas = libroRepository.findAllIdiomas();

        if (idiomas.isEmpty()) {
            System.out.println(ROJO + "\t\t❗❗ Aún no hay idiomas registrados.\n\t\t🧐 Ingrese un primer libro👍👍");
            return;
        }

        System.out.println("\n\t🌎 Idiomas disponibles:");
        for (int i = 0; i < idiomas.size(); i++) {
            System.out.printf(AMARILLO + "\t\t%d) " + MAGENTA
                    + IdiomaUtil.obtenerBanderaPorIdioma(idiomas.get(i)) + "\n", i + 1);
        }

        String msg = AMARILLO + "\t\tSeleccione un idioma: ";
        int opcion = validarEntrada.OpcionAValidar(msg, idiomas.size());
        String idiomaSeleccionado = idiomas.get(opcion - 1);
        List<LibroT> libros = libroRepository.findLibrosByIdioma(idiomaSeleccionado);

        System.out.printf(AMARILLO + "\n\t\t📚 Libros en "
                + VERDE + IdiomaUtil.obtenerBanderaPorIdioma(idiomaSeleccionado)
                + AMARILLO + ":\n");
        libros.forEach(libro ->
                System.out.println(VERDE + "\t\t - " + RESET + libro.getTitulo())
        );
    }

    //  M. - Lista los top 10 libros de gutendex y DB
    public void MostrarTop10Libros() {
        System.out.println(AMARILLO + "\t1) Top 10 libros Gutendex \n\t2) Top 10 libros registrados");
        String msg = "\n\tSeleccione una opcion: ";
        int opc = validarEntrada.OpcionAValidar(msg, 2);

        if (opc == 1) {
            var json = consumoAPI.ObtenerDatos(URL_API);
            var datos = conversor.obtenerDatos(json, DatosResponse.class);
            //Top 10 libros más descargados gutendex
            List<LibroT> top10 = datos.libros().stream()
                    .sorted(Comparator.comparing(LibroRes::numeroDeDescargas).reversed())
                    .limit(10)
                    .map(LibroT::new) // ya inyecta autores
                    .collect(Collectors.toList());

            top10.forEach(libro -> System.out.println(PrintUtil.imprimirLibro(libro)));

        } else {
            //Top 10 libros más descargados DB
            librosGuardados = libroRepository.obtenerTop10Libros();
            if (librosGuardados.isEmpty()) {
                System.out.println(ROJO + "\t\t❗❗ Aún no hay libros registrados.\n\t\t🧐 Ingrese un primer libro👍👍");
                return;
            }
            AtomicInteger contador = new AtomicInteger(1);
            librosGuardados.forEach(libroT ->
                    System.out.println("\t\t" + VERDE + contador.getAndIncrement() + ".-\n" + PrintUtil.imprimirLibro(libroT)));
        }
    }
}
