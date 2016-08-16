package edu.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String authorName;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "jc_books_author",
            joinColumns=@JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> bookList;

    public  Author(){
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String authorName, Set<Book> bookList) {
        this.authorName = authorName;
        this.bookList = bookList;
    }

    public Set<Book> getBookList() {
        return bookList;
    }


    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public void addBook(Book book){
        if(bookList==null){
            bookList=new HashSet<Book>();
        }
        bookList.add(book);
    }

    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(authorId);
        sb.append(", name='").append(authorName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
