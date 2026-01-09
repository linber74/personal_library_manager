package repository;

import java.io.*;

import static ui.LibraryUI.outputMessage;
import static ui.Dialogtext.*;


import javax.swing.*;


public class ReadAndWriteFile {


    public void ReadFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {}

        catch (FileNotFoundException e) {
            outputMessage(NOFILE, TITLEWRONG, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        } catch (IOException e) {
            outputMessage(WRONG, TITLEWRONG, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void WriteFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {}

        catch (IOException e) {
            outputMessage(WRONG, TITLEWRONG, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}