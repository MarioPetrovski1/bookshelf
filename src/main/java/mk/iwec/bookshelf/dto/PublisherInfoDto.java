package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Publisher;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherInfoDto {

    private Integer id;

    private String name;

    private String country;

    private Map<String, Object> books;

    public PublisherInfoDto(Publisher publisher) {
        if (publisher != null) {
            this.id = publisher.getId();
            this.name = publisher.getName();
            this.country = publisher.getCountry();
            books = new HashMap<>();
            publisher.getBooks().forEach(book -> books.put("id", book.getId()));
            publisher.getBooks().forEach(book -> books.put("title", book.getTitle()));
            publisher.getBooks().forEach(book -> books.put("isbn", book.getIsbn()));
            publisher.getBooks().forEach(book -> books.put("genre", book.getGenre()));
        }
    }


}
