/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productoConsumidor;

import java.util.Random;

/**
 *
 * @author Sandra
 */
public class Productor implements Runnable {

    private static final Random GENERDOR = new Random();
    private final Buffer ubicacionCompartida;

    public Productor(Buffer ubicacionCompartida) {
        this.ubicacionCompartida = ubicacionCompartida;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(GENERDOR.nextInt(1000));
                ubicacionCompartida.write(i);
                System.out.printf("Productor escribio:\t%2d\n", i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Terminano Productor\n");
    }
}
