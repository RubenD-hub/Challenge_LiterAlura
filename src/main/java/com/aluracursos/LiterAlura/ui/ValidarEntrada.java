package com.aluracursos.LiterAlura.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidarEntrada {
    //  Intancias
    private final Scanner scan = new Scanner(System.in);

    //  M. - Válida que la opcion ingresada sea correcta
    public int OpcionAValidar(String msg, int numOpciones) {
        while (true) {
            System.out.print(msg);
            try {
                int opc = scan.nextInt();
                if (opc < 1 || opc > numOpciones) {
                    System.out.println("\t¡¡Opción incorrecta!!\n\tVuelva a intentarlo.");
                    continue;
                }
                // Devuelve la respuesta correspondiente
                return opc;
            } catch (InputMismatchException e) {
                System.out.println("\t¡¡Error: debe ingresar un número!!\n\tVuelva a intentarlo.");
                scan.nextLine(); // Limpiar el buffer
            }
        }
    }
}
