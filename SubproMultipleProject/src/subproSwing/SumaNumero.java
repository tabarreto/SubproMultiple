/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subproSwing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author antonio tonito desarrollo
 */
public class SumaNumero extends JFrame {

    private JPanel norteJPanel;
    private JPanel surJPanel;
    private JTextArea mostrarSumaJTextArea;
    private JTextField numeroJTextField;
    private JButton iniciarJButton;
    private JLabel estadoJLabel;
    private JButton cancelarJButton;
    private JProgressBar progresoJProgressBar;
    private CalculadoraRespuestaInmediata calculadora;

    public SumaNumero(String title) {
        super(title);
        this.setLayout(new BorderLayout());
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(800, 700));
        this.setLocation(250, 0);
        this.setVisible(true);
    }

    private void initComponents() {
        norteJPanel = new JPanel();
        iniciarJButton = new JButton("Iniciar");
        numeroJTextField = new JTextField();
        numeroJTextField.setColumns(5);
        norteJPanel.add(new JLabel("Buscar la suma de: "));
        norteJPanel.add(numeroJTextField);
        norteJPanel.add(iniciarJButton);
        add(norteJPanel, BorderLayout.NORTH);

        iniciarJButton.addActionListener((e) -> {
            progresoJProgressBar.setValue(0);
            mostrarSumaJTextArea.setText("");
            estadoJLabel.setText("");

            int numero;
            try {
                numero = Integer.parseInt(numeroJTextField.getText());
            } catch (NumberFormatException ex) {
                estadoJLabel.setText("Escriba un entero.");
                return;
            }

            calculadora = new CalculadoraRespuestaInmediata(numero, mostrarSumaJTextArea, iniciarJButton, cancelarJButton, estadoJLabel);
            calculadora.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                if (evt.getPropertyName().equals("progress")) {
                    int nuevoValor = (Integer) evt.getNewValue();
                    progresoJProgressBar.setValue(nuevoValor);
                }
            });

            iniciarJButton.setEnabled(false);
            cancelarJButton.setEnabled(true);
            calculadora.execute();
        });

        mostrarSumaJTextArea = new JTextArea();
        mostrarSumaJTextArea.setEditable(false);
        add(new JScrollPane(mostrarSumaJTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

        surJPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        cancelarJButton = new JButton("Cancelar");
        cancelarJButton.setEnabled(false);
        progresoJProgressBar = new JProgressBar();
        progresoJProgressBar.setStringPainted(true);
        estadoJLabel = new JLabel("Suma del numero: ");
        surJPanel.add(cancelarJButton);
        surJPanel.add(progresoJProgressBar);
        surJPanel.add(estadoJLabel);

        cancelarJButton.addActionListener((e) -> {
            calculadora.detenerCalculo();
        });
        add(surJPanel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SumaNumero nf = new SumaNumero("SwingWorker");
    }
}
