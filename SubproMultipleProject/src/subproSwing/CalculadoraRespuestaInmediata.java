/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subproSwing;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author antonio
 */
public class CalculadoraRespuestaInmediata extends SwingWorker<Integer, Integer> {

    private final Random generador = new Random();
    private final JTextArea intermedioJTextArea;
    private final JButton iniciar;
    private final JButton cancelarJButton;
    private final JLabel estadoJLabel;
    private final int num;
    private boolean detenido = false;

    public CalculadoraRespuestaInmediata(int num, JTextArea intermedioJTextArea, JButton iniciarJButton, JButton cancelarJButton, JLabel estadoJLabel) {
        this.intermedioJTextArea = intermedioJTextArea;
        this.iniciar = iniciarJButton;
        this.cancelarJButton = cancelarJButton;
        this.estadoJLabel = estadoJLabel;
        this.num = num;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int cuenta = 0;

        for (int i = 1; i <= num; i++) {

            if (detenido) {
                return cuenta;
            } else {
                double parte = i;
                double total = num;
                int res = (int) (parte/total * 100);
                setProgress(res);
                try {
                    Thread.currentThread().sleep(generador.nextInt(1000));
                } catch (InterruptedException ex) {
                    estadoJLabel.setText("Se interrumpio subproceso Trabajador");
                    return cuenta;
                }
                publish(cuenta);
                cuenta++;
            }
        }
        return cuenta;
    }

    @Override
    protected void process(List<Integer> chunks) {
        chunks.forEach((chunk) -> {
            intermedioJTextArea.append(chunk + "\n");
        });
    }

    @Override
    protected void done() {

        iniciar.setEnabled(true);  // habilita el botón Obtener primos
        cancelarJButton.setEnabled(false); // deshabilita el botón Cancelar

        int cuenta;

        try {
            cuenta = get(); // obtiene el valor de retorno de doInBackground
        } catch (InterruptedException ex) {
            estadoJLabel.setText("Se interrumpio mientras se esperaban los resultados.");
            return;
        } // fin de catch
        catch (ExecutionException ex) {
            estadoJLabel.setText("Error al realizar el calculo.");
            return;
        }

        estadoJLabel.setText("La suma es: " + cuenta);
    }

    // establece la bandera para dejar de buscar números primos
    public void detenerCalculo() {
        detenido = true;
    }
}
