package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroRes(
        @JsonAlias("id") String idGutendex,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorRes> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDeDescargas
) {
}
