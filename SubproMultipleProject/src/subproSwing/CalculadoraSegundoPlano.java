/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subproSwing;

import java.util.concurrent.ExecutionException;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 *
 * @author antonio
 */
public class CalculadoraSegundoPlano extends SwingWorker<String, Object> {

    private final int n;
    private final JLabel resultadoJLabel;

    public CalculadoraSegundoPlano(int numero, JLabel etiqueta) {
        n = numero;
        resultadoJLabel = etiqueta;
    }

    @Override
    protected String doInBackground() throws Exception {
        return String.valueOf(fibonacci(n));
    }

    @Override
    protected void done() {
        try {
            resultadoJLabel.setText(get());
        } catch (InterruptedException | ExecutionException ex) {
            resultadoJLabel.setText("Se encontro un error al realizar el calculo.");
        }
    }

    private long fibonacci(long numero) {
        if (numero == 0 || numero == 1) {
            return numero;
        } else {
            return fibonacci(numero - 1) + fibonacci(numero - 2);
        }
    }
}
