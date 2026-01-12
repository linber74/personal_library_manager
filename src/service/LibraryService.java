package service;

import model.LibraryItem;
import repository.LibraryRepository;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private final LibraryRepository repository;
    private final LibraryItemFactory factory;
    private final List<LibraryItem> items;

    public LibraryService(LibraryRepository repository, LibraryItemFactory factory) {
        if (repository == null) {
            throw new IllegalArgumentException("repository cannot be null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("factory cannot be null");
        }
        this.repository = repository;
        this.factory = factory;

        List<LibraryItem> loaded = repository.load();
        this.items = (loaded != null) ? new ArrayList<>(loaded) : new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        items.add(item);
        repository.save(items);
    }

    public List<LibraryItem> getAll() {
        return new ArrayList<>(items);
    }

    public LibraryItem getById (String itemId) {

        if (itemId == null || itemId.isBlank()) {
            return null;
        }
        for (LibraryItem item : items) {
            if (itemId.equals(item.getItemId())) {
                return item;
            }
        }
        return  null;
    }

    public boolean removeById (String itemId) {
        if (itemId == null || itemId.isBlank()) {
            return false;
        }
        boolean removed = items.removeIf(item -> itemId.equals(item.getItemId()));

        if (removed) {
            repository.save(items);
        }
        return removed;
    }
}
