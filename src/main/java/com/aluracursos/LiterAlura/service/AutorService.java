package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.ui.ValidarEntrada;
import com.aluracursos.LiterAlura.util.PrintUtil;
import jakarta.transaction.Transactional;

import java.util.Scanner;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class AutorService {
    //  Instancias
    private final Scanner scan = new Scanner(System.in);
    private final AutorRepository autorRepository;
    private final ValidarEntrada validarEntrada = new ValidarEntrada();

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void MostrarAutoresGuardados() {
        autorRepository.findAllConLibros()
                .forEach(AutorT -> System.out.println(PrintUtil.imprimirAutor(AutorT)));
    }

    @Transactional
    public void AutoresVivosYear(){
        String msg = AMARILLO + "\n\tIngresa el año que deseas consultar: ";
        int year = validarEntrada.ValidarNumeroEntero(msg);
        autorRepository.AutoresVivosYear(year)
                .forEach(AutorT -> System.out.println(PrintUtil.imprimirAutor(AutorT)));
    }
}
