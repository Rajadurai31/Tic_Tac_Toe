package com.tictactoe.app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TICTACTOAGUI {
    private JFrame frame;
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TICTACTOAGUI() {
        
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeButtons();

        frame.setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                frame.add(buttons[i][j]);

                int row = i;
                int col = j;

                buttons[i][j].addActionListener(e -> buttonClicked(row, col));
            }
        }
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkWin()) {
                JOptionPane.showMessageDialog(frame, currentPlayer + " Wins!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "It's a Draw!");
                resetBoard();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return checkLine(buttons[0][0], buttons[1][1], buttons[2][2]) ||
               checkLine(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        String t1 = b1.getText();
        return !t1.equals("") && t1.equals(b2.getText()) && t1.equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                if (b.getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                b.setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TICTACTOAGUI::new);
    }
}
