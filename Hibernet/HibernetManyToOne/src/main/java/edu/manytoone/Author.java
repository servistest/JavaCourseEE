package edu.manytoone;

import javax.persistence.*;
import java.util.List;

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
    @OrderBy(value = "name")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "author")
    private List<Book> bookList;

    public  Author(){
    }

    public Author(String author){
        this.author=author;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
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
        sb.append('}');
        return sb.toString();
    }
}
