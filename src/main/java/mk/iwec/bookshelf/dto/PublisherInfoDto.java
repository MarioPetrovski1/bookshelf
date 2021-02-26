package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.Publisher;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherInfoDto {

    private Integer id;

    private String name;

    private String country;

    private List<BookShortInfoWithAuthors> books;

    public PublisherInfoDto(Publisher publisher) {
        if (publisher != null) {
            this.id = publisher.getId();
            this.name = publisher.getName();
            this.country = publisher.getCountry();
            books = new ArrayList<>();
            publisher.getBooks().stream().filter(book -> book.getAuthors() != null)
                    .forEach(book -> {
                        List<AuthorShortInfoDto> authors = new ArrayList<>();
                        book.getAuthors().forEach(author -> authors.add(new AuthorShortInfoDto(author.getId(), author.getFirstName(), author.getLastName())));
                        BookShortInfoWithAuthors tempBook = new BookShortInfoWithAuthors(book.getId(), book.getTitle(), book.getIsbn(), book.getCategory(), authors);
                        books.add(tempBook);
                    });
        }
    }


}
