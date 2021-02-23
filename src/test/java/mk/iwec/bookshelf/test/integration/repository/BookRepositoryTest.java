package mk.iwec.bookshelf.test.integration.repository;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.domain.Book;
import mk.iwec.bookshelf.repository.BookRepository;
import mk.iwec.bookshelf.test.utils.BookTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void getAllBooks() {
        Book mock = BookTestUtil.createMockBookEntity();
        repository.save(mock);

        List<Book> authorList = repository.findAll();
        assertThat(authorList).isNotEmpty();
    }


    // TODO: Create remaining tests

}
