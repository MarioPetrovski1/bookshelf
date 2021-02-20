package mk.iwec.bookshelf.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.service.impl.PublisherServiceImpl;

@RestController
@RequestMapping(Endpoints.PUBLISHERS)
public class PublisherController {

	@Autowired
	private PublisherServiceImpl service;

	@GetMapping("/{id}")
	public Publisher findById(@PathVariable(value = "id") Integer id) {
		return service.findById(id);
	}

	@GetMapping
	public List<Publisher> findAll() {
		return service.findAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Publisher create(@RequestBody Publisher entity) {
		return service.create(entity);
	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Publisher update(@PathVariable(value = "id") Integer id, @RequestBody Publisher entity) {
		return service.update(id, entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable(value = "id") Integer id) {
		service.deleteById(id);
	}
}