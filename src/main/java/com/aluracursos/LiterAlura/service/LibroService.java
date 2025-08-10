package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.model.*;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.api.ConsumoAPI;
import com.aluracursos.LiterAlura.service.api.ConvierteDatos;
import com.aluracursos.LiterAlura.util.PrintUtil;
import jakarta.transaction.Transactional;

import java.util.*;

import static com.aluracursos.LiterAlura.util.Constantes.URL_API;
import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class LibroService {
    //  Iniciar Instancias
    private final Scanner scan = new Scanner(System.in);
    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos conversor;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

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
    private void GuardarLibro(LibroRes libroRes){
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

    @Transactional
    public void MostrarLibrosGuardados() {
        libroRepository.findAllConAutores()
                .forEach(libroT -> System.out.println(PrintUtil.imprimirLibro(libroT)));
    }
}
