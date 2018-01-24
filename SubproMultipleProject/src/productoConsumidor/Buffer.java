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
public interface Buffer {

    int read() throws InterruptedException;

    void write(int valor) throws InterruptedException;

}
