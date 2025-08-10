package com.aluracursos.LiterAlura.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autores")
public class AutorT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;
    @ManyToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private Set<LibroT> libros = new HashSet<>();

    public AutorT() {
    }

    public AutorT(AutorRes autorRes) {
        this.nombre = autorRes.nombre();
        this.fechaDeNacimiento = autorRes.fechaDeNacimiento();
        this.fechaDeMuerte = autorRes.fechaDeMuerte();
    }

    //  Getters and Setters
    //  @formatter:off
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(Integer fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }
    public Integer getFechaDeMuerte() { return fechaDeMuerte; }
    public void setFechaDeMuerte(Integer fechaDeMuerte) { this.fechaDeMuerte = fechaDeMuerte; }
    public Set<LibroT> getLibros() { return libros; }
    public void setLibros(Set<LibroT> libros) { this.libros = libros; }
    //  @formatter:on
}
