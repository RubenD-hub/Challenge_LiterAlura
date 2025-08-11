package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.LibroT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<LibroT, Long> {

    // 🔹 Evalua sí existe el libro registrado en DB con ese idGutendex
    Optional<LibroT> findByIdGutendex(String idGutendex);

    // 🔹 Si quieres traer todos los libros con sus autores
    @Query("SELECT DISTINCT l FROM LibroT l LEFT JOIN FETCH l.autor LEFT JOIN FETCH l.idiomas ORDER BY l.id ASC")
    List<LibroT> findAllConAutores();

    // 🔹 Si quieres traer todos los idiomas registrados en DB
    @Query("SELECT DISTINCT i FROM LibroT l JOIN l.idiomas i")
    List<String> findAllIdiomas();

    // 🔹 Si quieres traer todos los libros por idioma
    @Query("SELECT DISTINCT l FROM LibroT l JOIN l.idiomas i WHERE i = :idioma")
    List<LibroT> findLibrosByIdioma(String idioma);

    // 🔹 Si quieres traer el top 10 libros
    @Query("SELECT DISTINCT l FROM LibroT l LEFT JOIN FETCH l.autor LEFT JOIN FETCH l.idiomas ORDER BY l.numeroDeDescarga DESC LIMIT 10")
    List<LibroT> obtenerTop10Libros();
}
