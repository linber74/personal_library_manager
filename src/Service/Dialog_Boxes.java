package Service;

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
            outputMessage( Dialogtext.EMPTY,
                    Dialogtext.TITLEEMPTY, JOptionPane.ERROR_MESSAGE);
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

