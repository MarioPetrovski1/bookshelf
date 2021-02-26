package mk.iwec.bookshelf.test.utils;

import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.dto.AuthorDto;




public class AuthorTestUtil {
    public static Author createMockAuthorEntity() {
        Author mock = new Author();
        mock.setId(1);
        mock.setFirstName("Mock FirstName");
        mock.setLastName("Mock LastName");
        return mock;
    }

    public static AuthorDto createMockAuthorDto(String firstName, String lastName) {
        AuthorDto mock = new AuthorDto();
        mock.setFirstName(firstName);
        mock.setLastName(lastName);
        return mock;
    }

}
