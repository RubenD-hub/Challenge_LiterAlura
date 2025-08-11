package com.aluracursos.LiterAlura.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class LibroT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String idGutendex;
    private String titulo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<AutorT> autor = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private Set<String> idiomas = new HashSet<>();
    private Double numeroDeDescarga;

    public LibroT() {
    }

    public LibroT(LibroRes libroRes) {
        this.idGutendex = libroRes.idGutendex();
        this.titulo = libroRes.titulo();
        this.idiomas = new HashSet<>(libroRes.idiomas());
        this.numeroDeDescarga = OptionalDouble.of(libroRes.numeroDeDescargas()).orElse(0);

        if (libroRes.autores() != null) {
            Set<AutorT> autoresSet = libroRes.autores().stream()
                    .map(a -> {
                        AutorT autor = new AutorT(a); // usa el constructor AutorT(AutorRes)
                        autor.getLibros().add(this); // mantiene relación bidireccional
                        return autor;
                    }).collect(Collectors.toSet());
            this.autor = autoresSet;
        }
    }

    //  Getters and Setters
    //  @formatter:off
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdGutendex() { return idGutendex; }
    public void setIdGutendex(String idGutendex) { this.idGutendex = idGutendex; }
    public Double getNumeroDeDescarga() { return numeroDeDescarga; }
    public void setNumeroDeDescarga(Double numeroDeDescarga) { this.numeroDeDescarga = numeroDeDescarga; }
    public Set<String> getIdiomas() { return idiomas; }
    public void setIdiomas(Set<String> idiomas) { this.idiomas = idiomas; }
    public Set<AutorT> getAutor() { return autor; }
    public void setAutor(Set<AutorT> autor) { this.autor = autor; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    //  @formatter:on
}
