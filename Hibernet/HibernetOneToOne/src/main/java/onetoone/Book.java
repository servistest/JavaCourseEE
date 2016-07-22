package onetoone;

import javax.persistence.*;
import java.io.Serializable;
import onetoone.Author;

/**
 * @author Artem Pronchakov | email/xmpp: artem.pronchakov@calisto.email
 */
@Entity
@Table(name="jc_books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long bookId;

    @Column(name ="book_name")
    private String name;


    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(){

    }
    public Book(String name, Author author) {
        this.name = name;
        this.author=author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;

    }
    public Long getId() {
        return bookId;
    }

    public void setId(Long id) {
        this.bookId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(bookId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author.getAuthor()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
