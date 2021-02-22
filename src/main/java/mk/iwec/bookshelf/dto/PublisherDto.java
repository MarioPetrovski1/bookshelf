package mk.iwec.bookshelf.dto;

import lombok.Data;
import mk.iwec.bookshelf.domain.Book;

import java.util.List;

@Data
public class PublisherDto {

    private Integer id;

    private String name;

    private String country;

    private List<Book> books;

}
