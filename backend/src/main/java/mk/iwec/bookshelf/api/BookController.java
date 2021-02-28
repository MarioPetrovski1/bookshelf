package mk.iwec.bookshelf.api;

import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.dto.BookInfoDto;
import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.mapper.BookMapper;
import mk.iwec.bookshelf.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(Endpoints.BOOKS)
public class BookController {

    @Autowired
    private BookServiceImpl service;

    @Autowired
    private BookMapper mapper;

    @GetMapping("/{id}")
    public BookInfoDto findById(@PathVariable(value = "id") Integer id) {
        return new BookInfoDto(mapper.dtoToEntity(service.findById(id)));
    }

    @GetMapping
    public List<BookInfoDto> findAll() {
        return new ArrayList<BookInfoDto>(mapper.mapList(service.findAll(), BookInfoDto.class));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookInfoDto create(@RequestBody BookDto bookDto) {
        return new BookInfoDto(mapper.dtoToEntity(service.create(bookDto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookDto update(@PathVariable(value = "id") Integer id, @RequestBody BookDto bookDto) {
        return service.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(value = "id") Integer id) {
        service.deleteById(id);
    }
}
