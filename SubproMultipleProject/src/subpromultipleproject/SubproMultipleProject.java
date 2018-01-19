/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subpromultipleproject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonio
 */
public class SubproMultipleProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Tarea supro1 = new Tarea("Tarea 1");
//        Tarea supro2 = new Tarea("Tarea 2");
//        Tarea supro3 = new Tarea("Tarea 3");
//
//        System.out.println("Iniciandi executor");
//
//        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.execute(supro1);
//        executor.execute(supro2);
//        executor.execute(supro3);
//        
//        executor.shutdown();
// 
//        System.out.println("Termina de ejecutarse el main.\n");

        ArregloSimple arregloSimpleCompartido = new ArregloSimple(6);

        // crea dos tareas para escribir en el objeto ArregloSimple compartido
        EscritorArreglo escritor1 = new EscritorArreglo(arregloSimpleCompartido, 1);
        EscritorArreglo escritor2 = new EscritorArreglo(arregloSimpleCompartido, 11);

        // ejecuta las tareas con un objeto ExecutorService
        ExecutorService ejecutor = Executors.newCachedThreadPool();
        ejecutor.execute(escritor1);
        ejecutor.execute(escritor2);

        ejecutor.shutdown();

        try {
            boolean tareasTerminaron = ejecutor.awaitTermination(10, TimeUnit.SECONDS);
            if (tareasTerminaron) {
                System.out.println(arregloSimpleCompartido);
            } else {
                System.out.println("Se agoto el tiempo esperando a que las tareas terminaran.");
            }
        } catch (InterruptedException ex) {
            System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
        }

    }
}
