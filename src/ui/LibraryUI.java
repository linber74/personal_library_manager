package ui;

import javax.swing.*;

public class LibraryUI {
    public static String input(String message) {
        while (true) {
            String userInput = JOptionPane.showInputDialog(message);
            if (userInput == null || userInput.isEmpty()) {
                outputMessage( Dialogtext.EMPTY,
                        Dialogtext.TITLEWRONG, JOptionPane.ERROR_MESSAGE);
                continue;
            }


            String [] words = userInput.split("\\s+");
            StringBuilder builder = new StringBuilder();

            for (String word : words) {
                if (!word.isEmpty()) {
                    String firstLetter = word.substring(0, 1).toUpperCase();
                    String rest = word.substring(1);
                    builder.append(firstLetter).append(rest).append(" ");
                }
            }
            return builder.toString().trim();
        }
    }

    //För framtiden, kommer fortsätta jobba på detta
    public static void outputMessage (String message, String title, int messageType){

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public static String ShowDropDown(String title, String prompt, String [] choices) {
        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(
                frame,
                prompt,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[0]);

        return (result == null) ? null : result.toString();
    }
}

