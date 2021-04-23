package tqs;

import java.time.LocalDateTime;
import java.util.*;

public class Library {

    private final List<Book> store = new ArrayList<Book>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final LocalDateTime from, final LocalDateTime to) {

        List<Book> filter = new ArrayList<Book>();

        for(Book book: this.store){
            if(book.getPublished().isAfter(from) && book.getPublished().isBefore(to) ){
                filter.add(book);
            }
        }

        return filter;
    }
}
