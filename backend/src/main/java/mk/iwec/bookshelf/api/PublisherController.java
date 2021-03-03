package mk.iwec.bookshelf.api;

import java.util.ArrayList;
import java.util.List;

import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.dto.PublisherDto;
import mk.iwec.bookshelf.dto.PublisherInfoDto;
import mk.iwec.bookshelf.mapper.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.service.impl.PublisherServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(Endpoints.PUBLISHERS)
public class PublisherController {

    @Autowired
    private PublisherServiceImpl service;

    @Autowired
    private PublisherMapper mapper;

    @GetMapping("/{id}")
    public PublisherInfoDto findById(@PathVariable(value = "id") Integer id) {
        return new PublisherInfoDto(mapper.dtoToEntity(service.findById(id)));
    }

    @GetMapping
    public List<PublisherInfoDto> findAll() {
        return new ArrayList<PublisherInfoDto>(mapper.mapList(service.findAll(), PublisherInfoDto.class));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PublisherDto create(@RequestBody PublisherDto publisherDto) {
        return service.create(publisherDto);
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public PublisherInfoDto addNewBook(@PathVariable(value = "id") Integer id, @RequestBody BookDto book) {
        return new PublisherInfoDto(mapper.dtoToEntity(service.addNewBook(id, book)));
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PublisherDto update(@PathVariable(value = "id") Integer id, @RequestBody PublisherDto publisherDto) {
        return service.update(id, publisherDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(value = "id") Integer id) {
        service.deleteById(id);
    }
}