package mk.iwec.bookshelf.dto;

import lombok.Data;
import mk.iwec.bookshelf.domain.Book;

import java.util.List;

@Data
public class AuthorDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private List<Book> books;



}
