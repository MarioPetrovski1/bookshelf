package mk.iwec.bookshelf.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseObject {

    @NonNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NonNull
    @Column(name = "isbn", nullable = false, length = 100)
    private String isbn;

    @NonNull
    @Column(name = "category")
    private String category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
    private Publisher publisher;



}
