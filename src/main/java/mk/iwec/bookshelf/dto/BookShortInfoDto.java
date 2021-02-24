package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookShortInfoDto {

    private Integer id;

    private String title;

    private String isbn;

    private Book.Genre genre;
}
