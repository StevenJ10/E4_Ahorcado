/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package e4_ahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class E4_Ahorcado {

    private JFrame frame;
    private JTextField textField;
    private String palabra = "manzana"; // Palabra a adivinar
    private String adivinado = ""; // Palabra adivinada hasta ahora
    private int intentos = 7; // Número máximo de intentos permitidos
    private JLabel intentosLabel; // Etiqueta para mostrar los intentos restantes

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    E4_Ahorcado window = new E4_Ahorcado();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public E4_Ahorcado() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        intentosLabel = new JLabel("Intentos restantes: " + intentos);
        intentosLabel.setBounds(50, 10, 200, 30);
        frame.getContentPane().add(intentosLabel);

        textField = new JTextField();
        textField.setBounds(50, 50, 550, 50);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        // Crear botones para cada letra del alfabeto
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            JButton boton = new JButton(String.valueOf(letra));

            int buttonWidth = 50;
            int buttonHeight = 50;
            int xOffset = 50 + (i % 10) * (buttonWidth + 5);
            int yOffset = 100 + (i / 10) * (buttonHeight + 5);

            boton.setBounds(xOffset, yOffset, buttonWidth, buttonHeight);

            boton.setText(String.valueOf(letra)); // Asignar el texto al botón
            boton.setForeground(Color.WHITE); // Cambiar el color del texto
            boton.setBackground(Color.DARK_GRAY); // Cambiar el color de fondo
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (intentos > 0) {
                        boton.setEnabled(false); // Desactiva el botón después de ser presionado
                        if (palabra.contains(String.valueOf(letra))) {
                            for (int j = 0; j < palabra.length(); j++) {
                                if (palabra.charAt(j) == letra) {
                                    adivinado = adivinado.substring(0, j) + letra + adivinado.substring(j + 1);
                                }
                            }
                        } else {
                            intentos--; // Resta un intento si la letra no está en la palabra
                        }
                        textField.setText(adivinado);
                        intentosLabel.setText("Intentos restantes: " + intentos);
                        checkGanador();
                    }
                }
            });
            frame.getContentPane().add(boton);
        }

        // Inicializa la palabra adivinada con guiones bajos
        for (int i = 0; i < palabra.length(); i++) {
            adivinado += "_";
        }
        textField.setText(adivinado);
    }

    private void checkGanador() {
        if (adivinado.equals(palabra)) {
            textField.setText("¡Has ganado!" + palabra);
            desactivarBotones();
        } else if (intentos == 0) {
            textField.setText("¡Has perdido! La palabra era: " + palabra);
            desactivarBotones();
        }
    }

    private void desactivarBotones() {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setEnabled(false);
            }
        }
    }
}
