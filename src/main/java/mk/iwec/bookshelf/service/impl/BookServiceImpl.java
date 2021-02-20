package mk.iwec.bookshelf.service.impl;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.infrastucture.exception.ResourceNotFoundException;
import mk.iwec.bookshelf.mapper.BookMapper;
import mk.iwec.bookshelf.repository.BookRepository;
import mk.iwec.bookshelf.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class BookServiceImpl implements GenericService<BookDto, Integer> {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookDto findById(Integer id) {
        Book entity = repository.findById(id).orElseThrow(() -> {
            log.error("Resource Book with id {} is not found", id);
            return new ResourceNotFoundException("Resource Book not found");
        });

        return bookMapper.entityToDto(entity);
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("Execute findAll Book");
        return bookMapper.mapList(repository.findAll(), BookDto.class);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Execute create Book with parameters ", bookDto);
        Book transientBook = bookMapper.dtoToEntity(bookDto);
        Book persistedBook = repository.save(transientBook);

        return bookMapper.entityToDto(persistedBook);
    }

    @Override
    public BookDto update(Integer id, BookDto bookDto) {
        log.debug("Execute update Book with parameters {}", bookDto);
        Book persistedEntity = bookMapper.dtoToEntity(findById(id));
        bookMapper.mapRequestedFieldForUpdate(persistedEntity, bookDto);
        return bookMapper.entityToDto(repository.saveAndFlush(persistedEntity));
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Execute deleteById Book with parameters {}", id);
        repository.deleteById(id);
    }
}
