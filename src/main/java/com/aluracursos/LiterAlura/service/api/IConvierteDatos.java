package com.aluracursos.LiterAlura.service.api;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
