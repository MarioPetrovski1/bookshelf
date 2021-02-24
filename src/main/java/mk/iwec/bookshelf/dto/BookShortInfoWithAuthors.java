package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Book;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookShortInfoWithAuthors extends BookShortInfoDto {

    private List<AuthorShortInfoDto> authors;

    @Builder
    public BookShortInfoWithAuthors(Integer id, String title, String isbn, Book.Genre genre, List<AuthorShortInfoDto> authors) {
        super(id, title, isbn, genre);
        this.authors = authors;
    }
}
