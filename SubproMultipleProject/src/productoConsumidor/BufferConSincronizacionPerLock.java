/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productoConsumidor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author Sandra
 */
public class BufferConSincronizacionPerLock extends BufferLock implements Buffer {

    private final int[] bufer;
    private int celdasOcupadas = 0;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;

    public BufferConSincronizacionPerLock(int size) {
        this.bufer = new int[size];
        for (int i = 0; i < this.bufer.length; i++) {
            this.bufer[i] = -1;
        }
    }

    @Override
    public synchronized void write(int valor) throws InterruptedException {
        while (celdasOcupadas == bufer.length - 1) {
            wait();
        }
        bufer[indiceEscritura] = valor;
        indiceEscritura = (indiceEscritura + 1) % bufer.length;
        celdasOcupadas++;
        notifyAll();
    }

    @Override
    public synchronized int read() throws InterruptedException {
        while (celdasOcupadas == 0) {
            wait();
        }
        int value = bufer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % bufer.length;
        celdasOcupadas--;
        notifyAll();
        return value;
    }

    @Override
    public void lock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unlock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition newCondition() {
        return new WaitCondition();
    }
}
