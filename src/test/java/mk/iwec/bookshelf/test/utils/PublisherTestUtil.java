package mk.iwec.bookshelf.test.utils;

import mk.iwec.bookshelf.domain.Publisher;
import mk.iwec.bookshelf.dto.PublisherDto;

public class PublisherTestUtil {
    public static Publisher createMockPublisherEntity() {
        Publisher mock = new Publisher();
        mock.setId(1);
        mock.setName("Mock Name");
        mock.setCountry("Mock country");
        return mock;
    }

    public static PublisherDto createMockPublisherDto(String name,String country) {
        PublisherDto mock = new PublisherDto();
        mock.setName(name);
        mock.setCountry(country);
        return mock;
    }

}
