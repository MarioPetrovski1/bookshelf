package mk.iwec.bookshelf.service.impl;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.dto.AuthorDto;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.infrastucture.exception.ResourceNotFoundException;
import mk.iwec.bookshelf.mapper.AuthorMapper;
import mk.iwec.bookshelf.mapper.BookMapper;
import mk.iwec.bookshelf.repository.AuthorRepository;
import mk.iwec.bookshelf.repository.PublisherRepository;
import mk.iwec.bookshelf.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AuthorServiceImpl implements GenericService<AuthorDto, Integer> {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private PublisherRepository publisherRepository;


    @Override
    public AuthorDto findById(Integer id) {
        Author entity = repository.findById(id).orElseThrow(() -> {
            log.error("Resource Author with id {} is not found", id);
            return new ResourceNotFoundException("Resource Author not found");
        });

        return authorMapper.entityToDto(entity);
    }

    @Override
    public List<AuthorDto> findAll() {
        log.debug("Execute findAll Author");
        return authorMapper.mapList(repository.findAll(), AuthorDto.class);
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        log.debug("Execute create Author with parameters ", authorDto);
        Author transientAuthor = authorMapper.dtoToEntity(authorDto);
        Author persistedEntity = repository.save(transientAuthor);
        return authorMapper.entityToDto(persistedEntity);
    }

    @Override
    public AuthorDto update(Integer id, AuthorDto authorDto) {
        log.debug("Execute update Author with parameters {}", authorDto);
        Author persistedEntity = authorMapper.dtoToEntity(findById(id));
        authorMapper.mapRequestedFieldForUpdate(persistedEntity, authorDto);
        return authorMapper.entityToDto(repository.saveAndFlush(persistedEntity));
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Execute deleteById Author with parameters {}", id);
        repository.deleteById(id);
    }

    public AuthorDto addNewBook(Integer id, BookDto bookDto) {
        Author entity = repository.findById(id).orElseThrow(() -> {
            log.error("Resource Author with id {} is not found", id);
            return new ResourceNotFoundException("Resource Author not found");
        });

        if (bookDto.getPublisher().getId() != null) {
            Publisher publisher = publisherRepository.findById(bookDto.getPublisher().getId()).orElseThrow(() -> {
                log.error("Resource Publisher with id {} is not found", id);
                return new ResourceNotFoundException("Resource Publisher not found");
            });
            bookDto.setPublisher(publisher);
        }


        entity.addBook(bookMapper.dtoToEntity(bookDto));
        return authorMapper.entityToDto(repository.saveAndFlush(entity));


    }
}
