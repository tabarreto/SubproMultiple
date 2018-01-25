/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productoConsumidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Sandra
 */
public class BufferConSincronizacionPerLock implements Buffer {

    private final Lock bloqueo = new ReentrantLock();
    private final Condition conditionWrite = bloqueo.newCondition();
    private final Condition conditionRead = bloqueo.newCondition();
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
    public void write(int valor) throws InterruptedException {
        bloqueo.lock();
        try {
            while (celdasOcupadas == bufer.length - 1) {
                conditionWrite.await();
            }
            bufer[indiceEscritura] = valor;
            indiceEscritura = (indiceEscritura + 1) % bufer.length;
            celdasOcupadas++;

            conditionRead.signal();
        } finally {
            bloqueo.unlock();
        }
    }

    @Override
    public synchronized int read() throws InterruptedException {
        bloqueo.lock();
        int value = 0;
        try {
            while (celdasOcupadas == 0) {
                conditionRead.await();
            }
            value = bufer[indiceLectura];
            indiceLectura = (indiceLectura + 1) % bufer.length;
            celdasOcupadas--;
            conditionWrite.signal();
        } finally {
            bloqueo.unlock();
        }
        return value;
    }
}
