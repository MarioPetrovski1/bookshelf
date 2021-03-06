package mk.iwec.bookshelf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.dto.BookShortInfoWithPublisher;
import mk.iwec.bookshelf.dto.PublisherDto;
import mk.iwec.bookshelf.mapper.BookMapper;
import mk.iwec.bookshelf.mapper.PublisherMapper;
import mk.iwec.bookshelf.repository.AuthorRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.infrastucture.exception.ResourceNotFoundException;
import mk.iwec.bookshelf.repository.PublisherRepository;
import mk.iwec.bookshelf.service.GenericService;

@Service
@Slf4j
@Transactional
public class PublisherServiceImpl implements GenericService<PublisherDto, Integer> {

    @Autowired
    private PublisherRepository repository;

    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public PublisherDto findById(Integer id) {
        Publisher entity = repository.findById(id).orElseThrow(() -> {
            log.error("Resource Publisher with id {} is not found", id);
            return new ResourceNotFoundException("Resource Publisher not found");
        });

        return publisherMapper.entityToDto(entity);
    }

    @Override
    public List<PublisherDto> findAll() {
        log.debug("Execute findAll Publisher");
        return publisherMapper.mapList(repository.findAll(), PublisherDto.class);
    }

    @Override
    public PublisherDto create(PublisherDto publisherDto) {
        log.debug("Execute create Publisher with parameters ", publisherDto);
        Publisher transientPublisher = publisherMapper.dtoToEntity(publisherDto);
        Publisher persistedPublisher = repository.save(transientPublisher);
        return publisherMapper.entityToDto(persistedPublisher);
    }

    @Override
    public PublisherDto update(Integer id, PublisherDto publisherDto) {
        log.debug("Execute update Publisher with parameters {}", publisherDto);
        Publisher persistedEntity = publisherMapper.dtoToEntity(publisherDto);
        publisherMapper.mapRequestedFieldForUpdate(persistedEntity, publisherDto);
        return publisherMapper.entityToDto(repository.saveAndFlush(persistedEntity));
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("Execute deleteById Publisher with parameters {}", id);
        repository.deleteById(id);
    }

    public PublisherDto addNewBook(Integer id, BookDto book) {
        Publisher entity = repository.findById(id).orElseThrow(() -> {
            log.error("Resource Publisher with id {} is not found", id);
            return new ResourceNotFoundException("Resource Publisher not found");
        });
        checkForAuthors(book, authorRepository, log);

        List<BookDto> books = bookMapper.mapList(entity.getBooks(), BookDto.class);
        books.add(book);
        entity.setBooks(bookMapper.mapList(books, Book.class));
        return publisherMapper.entityToDto(repository.saveAndFlush(entity));

    }

    public static void checkForAuthors(BookDto book, AuthorRepository authorRepository, Logger log) {
        List<Author> persistedAuthors = new ArrayList<>();

        book.getAuthors().stream().filter(author -> author.getId() != null).forEach(author -> {
            Author tempAuthor = authorRepository.findById(author.getId()).orElseThrow(() -> {
                log.error("Resource Author with id {} is not found", author.getId());
                return new ResourceNotFoundException("Resource Author not found");
            });

            if (tempAuthor != null) {
                persistedAuthors.add(tempAuthor);
            }

            if (persistedAuthors.size() > 0) {
                book.setAuthors(persistedAuthors);
            }
        });
        

    }
}
