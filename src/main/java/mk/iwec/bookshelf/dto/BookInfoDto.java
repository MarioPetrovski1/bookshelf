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

    private Map<String,String> authors;


    public BookInfoDto(Book book) {
        if(book != null) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.isbn = book.getIsbn();
            this.genre = book.getGenre();
            authors = new HashMap<>();
            book.getAuthors().forEach(author -> authors.put("fullname",author.getFirstName() + " " +author.getLastName()));
        }
    }

}
