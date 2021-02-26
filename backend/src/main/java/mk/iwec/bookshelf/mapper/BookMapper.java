package mk.iwec.bookshelf.mapper;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.infrastucture.mapper.GeneralMapper;

public interface BookMapper extends GeneralMapper<BookDto, Book> {

    public void mapRequestedFieldForUpdate(Book entity, BookDto dto);
}
