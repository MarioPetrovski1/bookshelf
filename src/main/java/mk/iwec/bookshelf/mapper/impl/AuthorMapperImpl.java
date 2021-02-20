package mk.iwec.bookshelf.mapper.impl;

import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.dto.AuthorDto;
import mk.iwec.bookshelf.infrastucture.mapper.AbstractGeneralMapper;
import mk.iwec.bookshelf.mapper.AuthorMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl extends AbstractGeneralMapper implements AuthorMapper {

    @Autowired
    public AuthorMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public AuthorDto entityToDto(Author author) {
        return this.modelMapper.map(author , AuthorDto.class);
    }

    @Override
    public Author dtoToEntity(AuthorDto authorDto) {
        return this.modelMapper.map(authorDto , Author.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(Author entity, AuthorDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setSex(dto.getSex());
        entity.setBooks(dto.getBooks());
    }
}
