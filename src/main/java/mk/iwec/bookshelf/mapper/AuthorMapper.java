package mk.iwec.bookshelf.mapper;

import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.dto.AuthorDto;
import mk.iwec.bookshelf.infrastucture.mapper.GeneralMapper;

public interface AuthorMapper extends GeneralMapper<AuthorDto, Author> {

    public void mapRequestedFieldForUpdate(Author entity, AuthorDto dto);
}
