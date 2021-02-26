package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfoDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private List<BookShortInfoWithPublisher> books;

    public AuthorInfoDto(Author author) {
        if (author != null) {
            this.id = author.getId();
            this.firstName = author.getFirstName();
            this.lastName = author.getLastName();
            books = new ArrayList<>();
            author.getBooks().stream().filter(book -> book.getPublisher() != null)
                    .forEach(book -> books.add(new BookShortInfoWithPublisher(
                            book.getId(), book.getTitle(), book.getIsbn(), book.getCategory(),
                            new PublisherShortInfoDto(book.getPublisher().getId(), book.getPublisher().getName(), book.getPublisher().getCountry())
                    )));

            author.getBooks().stream().filter(book -> book.getPublisher() == null)
                    .forEach(book -> books.add(new BookShortInfoWithPublisher(
                            book.getId(), book.getTitle(), book.getIsbn(), book.getCategory(), null
                    )));
        }
    }


}
