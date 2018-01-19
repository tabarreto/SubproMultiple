/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subpromultipleproject;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author antonio
 */
public class SubproMultipleProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread supro1 = new Thread(new Tarea("Tarea 1"));
        Thread supro2 = new Thread(new Tarea("Tarea 2"));
        Thread supro3 = new Thread(new Tarea("Tarea 3"));

        System.out.println("Iniciandi executor");

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(supro1);
        executor.execute(supro2);
        executor.execute(supro3);
        
        executor.shutdown();
 
        System.out.println("Termina de ejecutarse el main.\n");
    }
}
