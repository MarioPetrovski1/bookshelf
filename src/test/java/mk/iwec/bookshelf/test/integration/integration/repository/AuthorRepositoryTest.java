package mk.iwec.bookshelf.test.integration.integration.repository;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.repository.AuthorRepository;
import mk.iwec.bookshelf.test.integration.utils.AuthorTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(authorRepository).isNotNull();
    }

    @Test
    public void getAllAuthors() {
        Author mock = AuthorTestUtil.createMockAuthorEntity();
        authorRepository.save(mock);

        List<Author> authorList = authorRepository.findAll();
        assertThat(authorList).isNotEmpty();
    }


    // TODO: Create remaining tests




}
