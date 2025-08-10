package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.AutorT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorT, Long> {

    Optional<AutorT> findByNombre(String nombre);

    // 🔹 Si quieres traer todos los autores con sus libros
    @Query("SELECT DISTINCT a FROM AutorT a LEFT JOIN FETCH a.libros ORDER BY a.id ASC")
    List<AutorT> findAllConLibros();
}
