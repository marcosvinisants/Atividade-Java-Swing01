package br.edu.univille.poo;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaFinanciamento janela = new JanelaFinanciamento();
            janela.setVisible(true);
        });
    }
}