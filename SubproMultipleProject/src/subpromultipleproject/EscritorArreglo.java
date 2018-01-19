/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subpromultipleproject;

/**
 *
 * @author Sandra
 */
public class EscritorArreglo implements Runnable {

    private final ArregloSimple arregloSimpleCompartido;
    private final int valorInicial;

    public EscritorArreglo(ArregloSimple arregloSimpleCompartido, int valorInicial) {
        this.arregloSimpleCompartido = arregloSimpleCompartido;
        this.valorInicial = valorInicial;
    }

    @Override
    public void run() {
        for (int i = valorInicial; i < valorInicial + 3; i++) {
            arregloSimpleCompartido.agregar(i);
        }
    }
}
