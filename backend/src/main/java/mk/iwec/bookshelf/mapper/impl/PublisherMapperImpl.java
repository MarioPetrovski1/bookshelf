package mk.iwec.bookshelf.mapper.impl;

import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.dto.PublisherDto;
import mk.iwec.bookshelf.infrastucture.mapper.AbstractGeneralMapper;
import mk.iwec.bookshelf.mapper.PublisherMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapperImpl extends AbstractGeneralMapper implements PublisherMapper {


    @Autowired
    public PublisherMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public PublisherDto entityToDto(Publisher publisher) {
        return this.modelMapper.map(publisher, PublisherDto.class);
    }

    @Override
    public Publisher dtoToEntity(PublisherDto publisherDto) {
        return this.modelMapper.map(publisherDto, Publisher.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(Publisher entity, PublisherDto dto) {
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setBooks(dto.getBooks());
    }
}
