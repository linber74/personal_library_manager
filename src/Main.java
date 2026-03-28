import model.enums.MediaFormat;
import model.enums.TranslationInfo;
import model.media.Film;

import repository.ConnectionManager;
import repository.DatabaseRepository;
import service.LibraryItemFactory;
import service.LibraryService;




void main() {
    ConnectionManager cm = new ConnectionManager();
    DatabaseRepository data = new DatabaseRepository(cm);
    LibraryItemFactory factory = new LibraryItemFactory();
    LibraryService service = new LibraryService(data, factory);

    //data.addGenre("Fakta");
    //data.addLanguage("Svenska");

    /*Game game = factory.createGame("Starfield", List.of("Sci-fi"), "Engelska", 2023,
            null,"Bethesda");
    data.save(game);

    Book bok = factory.createBook("Java direkt med swing", List.of("Fakta"), "Svenska", 2021, null,
            List.of("Jan Skansholm"), BookFormat.PRINT_BOOK, null, null);
    data.save(bok);*/

    data.addGenre("Drama");
    data.addGenre("Romans");

    Film film = factory.createFilm("Titanic", List.of("Drama", "Romans"),"Engelska",1997 ,
            null, "James Cameron", List.of("Kate Winslet", "Leonardo DiCaprio",
                    "Kathy Bates", "Frances Fisher"), MediaFormat.DVD, TranslationInfo.SWEDISH);

    data.save(film);



    //data.deleteById(1);
    //data.deleteById(3);

    System.out.println(data.loadAll());
    //data.removeGenre("Fantasy");
    //println(data.getAllGenres());


}
