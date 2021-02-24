package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDto {

    private Integer id;

    private String title;

    private String isbn;

    private Book.Genre genre;

    private List<AuthorShortInfoDto> authors;

    private PublisherShortInfoDto publisher;


    public BookInfoDto(Book book) {
        if (book != null) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.isbn = book.getIsbn();
            this.genre = book.getGenre();
            authors = new ArrayList<>();
            publisher = new PublisherShortInfoDto(book.getPublisher().getId(), book.getPublisher().getName(), book.getPublisher().getCountry());
            List<Author> tempAuthors = book.getAuthors();
            for (Author a : tempAuthors) {
                AuthorShortInfoDto author = new AuthorShortInfoDto(a.getId(), a.getFirstName(), a.getLastName());
                authors.add(author);
            }
        }
    }

}
