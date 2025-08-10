package com.aluracursos.LiterAlura.util;

import com.aluracursos.LiterAlura.model.AutorT;
import com.aluracursos.LiterAlura.model.LibroT;

import static com.aluracursos.LiterAlura.util.StyleAnsi.*;

public class PrintUtil {

    public static String imprimirLibro(LibroT libro) {
        StringBuilder sb = new StringBuilder();

        sb.append(VERDE).append("\t\t--------------------------\n")
                .append("\t\t📚 Título: " + RESET).append(libro.getTitulo()).append("\n" + VERDE)
                .append("\t\t#️⃣ Id Gutendex: " + RESET).append(libro.getIdGutendex()).append("\n" + VERDE)
                .append("\t\t🌎 Idiomas: " + RESET).append(libro.getIdiomas()).append("\n" + VERDE)
                .append("\t\t📥 Descargas: " + RESET).append(libro.getNumeroDeDescarga()).append("\n" + VERDE)
                .append("\t\t👨‍💼👩‍💼 Autor(es): ");
        if (libro.getAutor() != null && !libro.getAutor().isEmpty()) {
            libro.getAutor().forEach(autorT -> sb.append(VERDE + "\n\t\t    -  " + RESET).append(autorT.getNombre()));
        } else {
            sb.append("\n\t\t    -" + RESET + "Desconocido").append("\n");
        }
        sb.append(VERDE).append("\n\t\t--------------------------\n").append(RESET);

        return sb.toString();
    }

    public static String imprimirAutor(AutorT autor) {
        StringBuilder sb = new StringBuilder();

        sb.append(VERDE).append("\t\t--------------------------\n")
                .append("\t\t📝 Nombre: " + RESET).append(autor.getNombre()).append("\n" + VERDE)
                .append("\t\t👶 Nacimiento: " + RESET).append(autor.getFechaDeNacimiento()).append("\n" + VERDE)
                .append("\t\t👻 Fallecimiento: " + RESET).append(autor.getFechaDeMuerte()).append("\n" + VERDE)
                .append("\t\t📚 Libros: ");
        autor.getLibros().forEach(libroT -> sb.append(VERDE + "\n\t\t    -  " + RESET).append(libroT.getTitulo()));
        sb.append(VERDE).append("\n\t\t--------------------------\n").append(RESET);

        return sb.toString();
    }
}
