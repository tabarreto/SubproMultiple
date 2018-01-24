/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productoConsumidor;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Sandra
 */
public class BufferConSincronizacionAPI implements Buffer {

    private final ArrayBlockingQueue<Integer> bufer;

    public BufferConSincronizacionAPI() {
        this.bufer = new ArrayBlockingQueue<>(5);
    }

    @Override
    public int read() throws InterruptedException {
        return bufer.take();
    }

    @Override
    public void write(int valor) throws InterruptedException {
        bufer.put(valor);
    }
}
