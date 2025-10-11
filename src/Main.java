import Archive_Superklass.Bibliotek.Bibliotek;
import Service.Dialog_Boxes;
import Service.Dialogtext;

public class Main {
    public static void main(String[] arg) {

        while (true) {
            String choose = (Dialog_Boxes.category(Dialogtext.CHOICES));
            if (choose == null) {
                Dialog_Boxes.outputMessage(Dialogtext.CLOSE);
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

            if (choose.equals(Dialogtext.BOK)){

            }
            Bibliotek.saveBibliotek("Bibliotek.txt");
        }
    }
}
