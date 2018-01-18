/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subpromultipleproject;

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

        System.out.println("Subprocesos creados, iniciando tareas.");

        supro1.start();
        supro2.start();
        supro3.start();

        System.out.println("Termina de ejecutarse el main.\n");
    }
}
