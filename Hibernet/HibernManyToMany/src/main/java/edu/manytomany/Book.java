package edu.manytomany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private String nameBook;

    //@ManyToOne(targetEntity = Author.class)
    @ManyToMany( cascade = {CascadeType.ALL},fetch = FetchType.LAZY )
    @JoinTable(name = "jc_books_author",
            joinColumns=@JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authorList;


    public Book(){

    }

    public Book(String nameBook) {
        this.nameBook = nameBook;
    }

    public Book(String nameBook, Set<Author> authorList) {
        this.nameBook = nameBook;
        this.authorList = authorList;
    }

    public void addAuthor(Author author){
        if (authorList ==null){
            authorList=new HashSet<Author>();
        }
        authorList.add(author);
    }
    public void removeAuthor(Author author){
        if (authorList !=null){
            authorList.remove(author);
        }
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Set<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Set<Author> authorList) {
        this.authorList = authorList;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(bookId);
        sb.append(", name='").append(nameBook).append('\'');
        sb.append('}');
        return sb.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return nameBook != null ? nameBook.equals(book.nameBook) : book.nameBook == null;

    }

    @Override
    public int hashCode() {
        return nameBook != null ? nameBook.hashCode() : 0;
    }
}
