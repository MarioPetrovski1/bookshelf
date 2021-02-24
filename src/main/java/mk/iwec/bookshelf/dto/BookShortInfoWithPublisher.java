package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.iwec.bookshelf.domain.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookShortInfoWithPublisher extends BookShortInfoDto {

    private PublisherShortInfoDto publisher;

    @Builder
    public BookShortInfoWithPublisher(Integer id, String title, String isbn, Book.Genre genre, PublisherShortInfoDto publisher) {
        super(id, title, isbn, genre);
        this.publisher = publisher;
    }
}
