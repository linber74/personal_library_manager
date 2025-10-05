package Bibliotek;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        while (true) {
            int choose = Dialog_Boxes.category(Dialogtext.menu);
            if (choose == JOptionPane.CLOSED_OPTION || choose == 3) {
                break;
            }
            String title = Dialog_Boxes.input(Dialogtext.TITLE);
            if (title == null) {
                break;
            }

            String author = Dialog_Boxes.input(Dialogtext.AUTHOR);
            if (author == null) {
                break;
            }

            String genre = Dialog_Boxes.input(Dialogtext.GENRE);
            if (genre == null) {
                break;
            }

            String language = Dialog_Boxes.input(Dialogtext.LANGUAGE);
            if (language == null) {
                break;
            }

            switch (choose) {
                //Bok
                case 0 -> {
                    String format = Dialog_Boxes.input(Dialogtext.COVER);
                    Bibliotek.addBibliotek(new Books(title, author, genre, language, format));
                }
                //E-bok
                case 1 -> {
                    Bibliotek.addBibliotek(new Ebooks(title, author, genre, language));
                }
                // Ljudbok
                case 2 -> {
                    Bibliotek.addBibliotek(new Audiobooks(title, author, genre, language));
                }


            }

        }
        Bibliotek.saveBibliotek("Bibliotek.txt");
    }
}
