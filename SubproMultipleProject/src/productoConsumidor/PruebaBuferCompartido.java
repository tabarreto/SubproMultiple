/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productoConsumidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Sandra
 */
public class PruebaBuferCompartido {

    public static void main(String[] args) {
        BufferConSincronizacionPer ubicacionCompartida = new BufferConSincronizacionPer(3);
//        BufferConSincronizacionAPI ubicacionCompartida = new BufferConSincronizacionAPI();

        Productor productor = new Productor(ubicacionCompartida);
        Consumidor consumidor = new Consumidor(ubicacionCompartida);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(productor);
        executor.execute(consumidor);
        executor.shutdown();
    }
}
