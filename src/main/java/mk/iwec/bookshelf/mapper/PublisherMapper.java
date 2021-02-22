package mk.iwec.bookshelf.mapper;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.dto.PublisherDto;
import mk.iwec.bookshelf.infrastucture.mapper.GeneralMapper;

public interface PublisherMapper extends GeneralMapper<PublisherDto , Publisher> {

    public void mapRequestedFieldForUpdate(Publisher entity, PublisherDto dto);
}
