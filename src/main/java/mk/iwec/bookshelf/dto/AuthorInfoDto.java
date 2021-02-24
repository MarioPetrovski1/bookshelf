package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfoDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private Author.Sex sex;

    private List<BookShortInfoWithPublisher> books;

    public AuthorInfoDto(Author author) {
        if (author != null) {
            this.id = author.getId();
            this.firstName = author.getFirstName();
            this.lastName = author.getLastName();
            this.sex = author.getSex();
            books = new ArrayList<>();
            PublisherShortInfoDto publisher;
            List<Book> tempBooks = author.getBooks();
            for (Book b : tempBooks) {
                if (b.getPublisher() != null) {
                    publisher =
                            new PublisherShortInfoDto(b.getPublisher().getId(), b.getPublisher().getName(), b.getPublisher().getCountry());
                } else {
                    publisher = null;
                }
                BookShortInfoWithPublisher book =
                        new BookShortInfoWithPublisher(b.getId(), b.getTitle(), b.getIsbn(), b.getGenre(), publisher);
                books.add(book);
            }
        }
    }


}
