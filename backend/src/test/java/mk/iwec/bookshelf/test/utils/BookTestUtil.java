package mk.iwec.bookshelf.test.utils;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.dto.BookDto;

public class BookTestUtil {
    public static Book createMockBookEntity() {
        Book mock = new Book();
        mock.setId(1);
        mock.setTitle("Mock Title");
        mock.setIsbn("Mock isbn");
        return mock;
    }

    public static BookDto createMockBookDto(String title, String isbn) {
        BookDto mock = new BookDto();
        mock.setTitle(title);
        mock.setIsbn(isbn);
        return mock;
    }

}
