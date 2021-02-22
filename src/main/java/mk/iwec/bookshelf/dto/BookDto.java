package mk.iwec.bookshelf.dto;

import lombok.Data;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.Publisher;

import java.util.List;

@Data
public class BookDto {

    private Integer id;

    private String title;

    private String isbn;

    private Book.Genre genre;

    private List<Author> authors;

    private Publisher publisher;


}
