package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.model.AutorT;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.ui.ValidarEntrada;
import com.aluracursos.LiterAlura.util.PrintUtil;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Scanner;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class AutorService {
    //  Iniciar Instancias
    private final Scanner scan = new Scanner(System.in);
    private final AutorRepository autorRepository;
    private final ValidarEntrada validarEntrada = new ValidarEntrada();

    //  Variables
    List<AutorT> autoresGuardados;

    //  Constructor
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    //  M. - Muestra la lista de autores guardados en la DB
    @Transactional
    public void MostrarAutoresGuardados() {
        autoresGuardados = autorRepository.findAllConLibros();
        if (autoresGuardados.isEmpty()) {
            System.out.println(ROJO + "\t\t❗❗ Aún no hay autores registrados.\n\t\t🧐 Ingrese un primer libro👍👍");
            return;
        }
        autoresGuardados.forEach(AutorT -> System.out.println(PrintUtil.imprimirAutor(AutorT)));
    }

    //  M. - Lista los autores vivos en cierto año
    @Transactional
    public void AutoresVivosYear(){
        autoresGuardados = autorRepository.findAllConLibros();
        if (autoresGuardados.isEmpty()) {
            System.out.println(ROJO + "\t\t❗❗ Aún no hay autores registrados.\n\t\t🧐 Ingrese un primer libro👍👍");
            return;
        }
        String msg = AMARILLO + "\n\tIngresa el año que deseas consultar: ";
        int year = validarEntrada.ValidarNumeroEntero(msg);
        autorRepository.AutoresVivosYear(year)
                .forEach(AutorT -> System.out.println(PrintUtil.imprimirAutor(AutorT)));
    }
}
