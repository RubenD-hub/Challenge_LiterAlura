package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.util.PrintUtil;
import jakarta.transaction.Transactional;

public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void MostrarAutoresGuardados() {
        autorRepository.findAllConLibros()
                .forEach(AutorT -> System.out.println(PrintUtil.imprimirAutor(AutorT)));
    }
}
