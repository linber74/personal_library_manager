package Bibliotek;

import javax.swing.*;

public class Dialog_Boxes {
    public static String input(String message) {
        while (true) {
            String userInput = JOptionPane.showInputDialog(message);
            if (userInput == null) {
                return null;
            }
            userInput = userInput.trim();
            if (!userInput.isEmpty()) {
                return userInput;
            }
            JOptionPane.showMessageDialog(null, Dialogtext.EMPTY,
                    "FEL", JOptionPane.ERROR_MESSAGE);
        }
    }
//    För framtiden, kommer fortsätta jobba på detta
//    public static void output (String message){
//        JOptionPane.showMessageDialog(null,message);
//    }

    public static int category(String[] options) {
        return JOptionPane.showOptionDialog(
                null, "Vilken Kategori?\n", "Kategorier",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]
        );
    }
}

