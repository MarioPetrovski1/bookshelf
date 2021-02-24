package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private List<BookShortInfoDto> books;

    public PublisherInfoDto(Publisher publisher) {
        if (publisher != null) {
            this.id = publisher.getId();
            this.name = publisher.getName();
            this.country = publisher.getCountry();
            books = new ArrayList<>();
            List<Book> tempBooks = publisher.getBooks();
            for (Book b : tempBooks) {
                BookShortInfoDto book = new BookShortInfoDto(b.getId(), b.getTitle(), b.getIsbn(), b.getGenre());
                books.add(book);
            }
        }
    }


}
