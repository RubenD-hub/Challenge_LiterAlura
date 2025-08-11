package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.AutorT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorT, Long> {

    // 🔹 Evalua si existe el autor registrado en la DB con ese nombre
    Optional<AutorT> findByNombre(String nombre);

    // 🔹 Si quieres traer todos los autores con sus libros
    @Query("SELECT DISTINCT a FROM AutorT a LEFT JOIN FETCH a.libros ORDER BY a.id ASC")
    List<AutorT> findAllConLibros();

    // 🔹 Si quieres traer todos los autores vivos en ese determinado año
    @Query("SELECT DISTINCT a FROM AutorT a LEFT JOIN FETCH a.libros WHERE a.fechaDeNacimiento <= :year AND a.fechaDeMuerte >=:year")
    List<AutorT> AutoresVivosYear(int year);

    // 🔹 Si quieres traer todos los autores con el nombre buscado
    @Query("SELECT DISTINCT a FROM AutorT a LEFT JOIN FETCH a.libros WHERE a.nombre ILIKE %:nombreAutor%")
    List<AutorT> autorPorNombre(String nombreAutor);
}
