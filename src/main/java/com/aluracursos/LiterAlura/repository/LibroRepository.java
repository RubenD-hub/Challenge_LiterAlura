package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.LibroT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<LibroT,Long> {

    // 🔹 Evalua si existe el libro registrado en DB con ese idGutendex
    Optional<LibroT> findByIdGutendex(String idGutendex);

    // 🔹 Si quieres traer todos los libros con sus autores
    @Query("SELECT DISTINCT l FROM LibroT l LEFT JOIN FETCH l.autor ORDER BY l.id ASC")
    List<LibroT> findAllConAutores();
}
