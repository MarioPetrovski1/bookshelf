package mk.iwec.bookshelf.test.integration.repository;


import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.repository.PublisherRepository;
import mk.iwec.bookshelf.test.utils.PublisherTestUtil;
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
public class PublisherRepositoryTest {
    @Autowired
    private PublisherRepository repository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void getAllPublishers() {
        Publisher mock = PublisherTestUtil.createMockPublisherEntity();
        repository.save(mock);

        List<Publisher> publisherList = repository.findAll();
        assertThat(publisherList).isNotEmpty();
    }
}
