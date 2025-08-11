package com.aluracursos.LiterAlura.util;

public class IdiomaUtil {
    public static String obtenerBanderaPorIdioma(String idioma) {
        return switch (idioma.toLowerCase()) {
            case "es" -> "Español"; // Español - España
            case "en" -> "Inglés"; // Inglés - Reino Unido (puedes usar 🇺🇸 para EE.UU.)
            case "fr" -> "Francés"; // Francés - Francia
            case "de" -> "Alemán"; // Alemán - Alemania
            case "it" -> "Italiano"; // Italiano - Italia
            case "pt" -> "Portugués"; // Portugués - Portugal
            case "ru" -> "Ruso"; // Ruso - Rusia
            case "ja" -> "Japonés"; // Japonés - Japón
            case "zh" -> "Chino"; // Chino - China
            default -> "🏳️";   // Bandera blanca / desconocido
        };
    }
}
