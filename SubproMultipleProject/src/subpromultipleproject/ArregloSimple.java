/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subpromultipleproject;

import java.util.Random;

/**
 *
 * @author Sandra
 */
public class ArregloSimple {

    private final int arreglo[]; // el arreglo entero compartido
    private int indiceEscritura = 0; // índice del siguiente elemento a escribir
    private final Random generador = new Random();

    // construye un objeto ArregloSimple de un tamaño dado
    public ArregloSimple(int tamanio) {
        arreglo = new int[tamanio];
    } // fin del constructor

    // agrega un valor al arreglo compartido
    public void agregar(int valor) {
        int posicion = indiceEscritura;  // almacena el índice de escritura
        try {
            // pone el subproceso en inactividad de 0 a 499 milisegundos
            Thread.sleep(generador.nextInt(9000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // coloca el valor en el elemento apropiado
        arreglo[posicion] = valor;
        System.out.printf("%s escribio%2d en el elemento %d.\n", Thread.currentThread().getName(), valor, posicion);

        ++indiceEscritura; // incrementa el índice del siguiente elemento a escribir
        System.out.printf("Siguiente indice de escritura: %d\n", indiceEscritura);
    } // fin del método agregar

    // se utiliza para imprimir el contenido del arreglo entero compartido
    @Override
    public String toString() {
        String cadenaArreglo = "\nContenido de ArregloSimple:\n";

        for (int i = 0; i < arreglo.length; i++) {
            cadenaArreglo += arreglo[i] + " ";
        }

        return cadenaArreglo;
    } // fin del método toString
}
