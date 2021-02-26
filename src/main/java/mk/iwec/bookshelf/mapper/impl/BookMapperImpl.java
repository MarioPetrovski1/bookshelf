package mk.iwec.bookshelf.mapper.impl;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.infrastucture.mapper.AbstractGeneralMapper;
import mk.iwec.bookshelf.mapper.BookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl extends AbstractGeneralMapper implements BookMapper {

    @Autowired
    public BookMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public BookDto entityToDto(Book book) {
        return this.modelMapper.map(book, BookDto.class);
    }

    @Override
    public Book dtoToEntity(BookDto bookDto) {
        return this.modelMapper.map(bookDto, Book.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(Book entity, BookDto dto) {
        entity.setTitle(dto.getTitle());
        entity.setIsbn(dto.getIsbn());
        entity.setCategory(dto.getCategory());
        entity.setAuthors(dto.getAuthors());
        entity.setPublisher(dto.getPublisher());
    }
}
