/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subpromultipleproject;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sandra
 */
public class Tarea implements Runnable {

    private String name;
    private final int inactivity_time;
    private final Random generator = new Random();

    public Tarea(String name) {
        this.inactivity_time = generator.nextInt(5000);
        this.name = name;
    }

    public int getTime_inactivity() {
        return inactivity_time;
    }

    public String getName() {
        return name;
    }

    public Random getGenerator() {
        return generator;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s va a estar inactivo durante %d milisegundos.\n", name, inactivity_time);
        try {
            Thread.sleep(inactivity_time);
        } catch (InterruptedException ex) {
            System.out.printf("%s %s\n", name, "termino en forma prematura, debido a una interrupción");
        }
        System.out.printf("%s termino su inactividad\n", name);
    }
}
