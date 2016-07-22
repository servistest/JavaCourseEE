package onetoone;
import onetoone.Book;

import javax.persistence.*;

/**
 * Created by Admin on 19.07.2016.
 */
@Entity
@Table(name="jc_author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private int authorId;

    @Column(name="author_name")
    private String author;

   // @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, targetEntity = Book.class)
    @OneToOne(mappedBy = "author")
    private Book book;


    public Author(){
    }

    public Author(String author){
        this.author=author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(authorId);
        sb.append(", name='").append(author).append('\'');
        sb.append(", book='").append(book.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
