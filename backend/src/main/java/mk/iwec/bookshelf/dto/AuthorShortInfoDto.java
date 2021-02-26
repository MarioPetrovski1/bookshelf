package mk.iwec.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorShortInfoDto {

    private Integer id;

    private String firstName;

    private String LastName;

}
