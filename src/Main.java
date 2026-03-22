import model.media.Game;

import repository.ConnectionManager;
import repository.DatabaseRepository;
import service.LibraryItemFactory;
import service.LibraryService;




void main() {
    ConnectionManager cm = new ConnectionManager();
    DatabaseRepository data = new DatabaseRepository(cm);
    LibraryItemFactory factory = new LibraryItemFactory();
    LibraryService service = new LibraryService(data, factory);

    //data.addGenre("Sci-fi");
    //data.addLanguage("Engelska");

    Game game = factory.createGame("Starfield", List.of("Sci-fi"), "Engelska", null,
            null,"Bethesda");
    data.save(game);

    //data.deleteById(2);
    //data.deleteById(3);

    System.out.println(data.loadAll());
    //data.removeGenre("Fantasy");
    //println(data.getAllGenres());


}
