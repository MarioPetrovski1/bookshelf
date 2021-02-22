package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Book;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDto {

    private Integer id;

    private String title;

    private String isbn;

    private Book.Genre genre;

    private Map<String, Object> authors;

    private Map<String, Object> publisher;


    public BookInfoDto(Book book) {
        if (book != null) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.isbn = book.getIsbn();
            this.genre = book.getGenre();
            authors = new HashMap<>();
            book.getAuthors().forEach(author -> authors.put("id", author.getId()));
            book.getAuthors().forEach(author -> authors.put("firstName", author.getFirstName()));
            book.getAuthors().forEach(author -> authors.put("lastName", author.getLastName()));
            publisher = new HashMap<>();
            publisher.put("id", book.getPublisher().getId());
            publisher.put("name", book.getPublisher().getName());
            publisher.put("country", book.getPublisher().getCountry());
        }
    }

}
