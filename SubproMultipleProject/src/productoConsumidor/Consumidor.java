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
public class Consumidor implements Runnable {

    private static final Random GENERDOR = new Random();
    private final Buffer ubicacionCompartida;

    public Consumidor(Buffer ubicacionCompartida) {
        this.ubicacionCompartida = ubicacionCompartida;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(GENERDOR.nextInt(1000));
                System.out.printf("Consumidor leyo:\t%2d\n", ubicacionCompartida.read());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.printf("Termino Consumidor\n");
    }
}
