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
                    Dialogtext.TITLEEMPTY, JOptionPane.ERROR_MESSAGE);
        }
    }
    //För framtiden, kommer fortsätta jobba på detta
    public static void outputMessage (String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public static String category(String[] CHOICES) {
        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(
                frame,
                Dialogtext.WHICHCATEGORI,
                Dialogtext.TITLECATEGORI,
                JOptionPane.QUESTION_MESSAGE,
                null,
                Dialogtext.CHOICES,
                Dialogtext.CHOICES[0]);

        return (result == null) ? null : result.toString();
    }
}

