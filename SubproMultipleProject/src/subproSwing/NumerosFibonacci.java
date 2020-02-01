/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subproSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author antonio
 */
public class NumerosFibonacci extends JFrame {

    private JPanel trabajadorJPanel;
    private JTextField numeroJTextField;
    private JButton iniciarJButton;
    private JLabel fibonacciJLabel;

    private JPanel subprocesoEventosJPanel;
    private int cuenta = 1;
    private JLabel nJLabel;
    private JLabel nFibonacciJLabel;
    private JButton siguienteNumeroJButton;

    public NumerosFibonacci(String title) {
        super(title);
        this.setLayout(new GridLayout(2, 1, 10, 10));
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(800, 700));
        this.setLocation(250, 0);
        this.setVisible(true);
    }

    private void initComponents() {
        trabajadorJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        trabajadorJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Con SwingWorkerfdfd"));
        numeroJTextField = new JTextField();
        iniciarJButton = new JButton("Iniciar");
        fibonacciJLabel = new JLabel("Obtener Fibonacci de:");
        trabajadorJPanel.add(fibonacciJLabel);
        trabajadorJPanel.add(numeroJTextField);
        trabajadorJPanel.add(iniciarJButton);
        add(trabajadorJPanel);

        iniciarJButton.addActionListener((e) -> {
            int n;
            try {
                n = Integer.parseInt(numeroJTextField.getText());
            } catch (NumberFormatException ex) {
                fibonacciJLabel.setText("Escriba un entero.");
                return;
            }

            fibonacciJLabel.setText("Calculando...");
            CalculadoraSegundoPlano tarea = new CalculadoraSegundoPlano(n, fibonacciJLabel);
            tarea.execute();
        });

        subprocesoEventosJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        subprocesoEventosJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Sin SwingWorker"));
        nJLabel = new JLabel("Fibonacci de 1: ");
        nFibonacciJLabel = new JLabel(String.valueOf(cuenta));
        siguienteNumeroJButton = new JButton("Siguiente numero");
        subprocesoEventosJPanel.add(nJLabel);
        subprocesoEventosJPanel.add(nFibonacciJLabel);
        subprocesoEventosJPanel.add(siguienteNumeroJButton);
        add(subprocesoEventosJPanel);

        siguienteNumeroJButton.addActionListener((e) -> {
            nJLabel.setText("Calculando...");
            long res = (fibonacci(cuenta));
            nJLabel.setText(String.format("Fibonacci de %s:", cuenta));
            nFibonacciJLabel.setText(String.valueOf(res));
            cuenta++;
        });
    }

    public static void main(String[] args) {
        NumerosFibonacci nf = new NumerosFibonacci("SwingWorker");
    }

    private long fibonacci(long numero) {
        if (numero == 0 || numero == 1) {
            return numero;
        } else {
            return fibonacci(numero - 1) + fibonacci(numero - 2);
        }
    }
}
