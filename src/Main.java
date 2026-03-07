import repository.ConnectionManager;
import repository.DatabaseRepository;

import static java.lang.IO.println;


void main() {
    ConnectionManager cm = new ConnectionManager();
    DatabaseRepository data = new DatabaseRepository(cm);
    //data.addGenre("Fantasy");
    //data.removeGenre("Fantasy");
    //println(data.getAllGenres());
}
