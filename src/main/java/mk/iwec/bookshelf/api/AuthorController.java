package mk.iwec.bookshelf.api;

import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.dto.AuthorDto;
import mk.iwec.bookshelf.dto.AuthorInfoDto;
import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.mapper.AuthorMapper;
import mk.iwec.bookshelf.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Endpoints.AUTHORS)
public class AuthorController {

    @Autowired
    private AuthorServiceImpl service;

    @Autowired
    private AuthorMapper mapper;

    @GetMapping("/{id}")
    public AuthorInfoDto findById(@PathVariable(value = "id") Integer id) {
        return new AuthorInfoDto(mapper.dtoToEntity(service.findById(id)));
    }

    @GetMapping
    public List<AuthorInfoDto> findAll() {
        return new ArrayList<AuthorInfoDto>(mapper.mapList(service.findAll(), AuthorInfoDto.class));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AuthorDto create(@RequestBody AuthorDto authorDto) {
        return service.create(authorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorDto update(@PathVariable(value = "id") Integer id, @RequestBody AuthorDto authorDto) {
        return service.update(id, authorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(value = "id") Integer id) {
        service.deleteById(id);
    }


}
