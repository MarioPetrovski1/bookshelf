package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfoDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private Map<String,Object> bookNames;

    public AuthorInfoDto(Author author) {
        if(author != null) {
            this.id = author.getId();
            this.firstName = author.getFirstName();
            this.lastName = author.getLastName();
            bookNames = new HashMap<>();
            author.getBooks().forEach(book -> bookNames.put("id", book.getId()));
            author.getBooks().forEach(book -> bookNames.put("title",book.getTitle()));
            author.getBooks().forEach(book -> bookNames.put("isbn", book.getIsbn()));
            author.getBooks().forEach(book -> bookNames.put("genre",book.getGenre()));
        }
    }


}
