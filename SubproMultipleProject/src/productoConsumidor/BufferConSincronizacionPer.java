/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productoConsumidor;

/**
 *
 * @author Sandra
 */
public class BufferConSincronizacionPer implements Buffer {

    private final int[] bufer;
    private int celdasOcupadas = 0;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;

    public BufferConSincronizacionPer(int size) {
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
        int x = 0;
        if (indiceLectura > bufer.length - 1) {
            indiceLectura = 0;
        } else {
            x = indiceLectura;
            ++indiceLectura;
        }
        celdasOcupadas--;
        notifyAll();
        return bufer[x];
    }
}
