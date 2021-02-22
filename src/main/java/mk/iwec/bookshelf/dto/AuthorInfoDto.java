package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Author;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfoDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private Author.Sex sex;

    private Map<String, Object> books;

    public AuthorInfoDto(Author author) {
        if (author != null) {
            this.id = author.getId();
            this.firstName = author.getFirstName();
            this.lastName = author.getLastName();
            this.sex = author.getSex();
            books = new HashMap<>();
            author.getBooks().forEach(book -> books.put("id", book.getId()));
            author.getBooks().forEach(book -> books.put("title", book.getTitle()));
            author.getBooks().forEach(book -> books.put("isbn", book.getIsbn()));
            author.getBooks().forEach(book -> books.put("genre", book.getGenre()));
        }
    }


}
