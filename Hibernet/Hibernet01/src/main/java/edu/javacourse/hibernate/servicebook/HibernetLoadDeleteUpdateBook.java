package edu.javacourse.hibernate.servicebook;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.javacourse.hibernate.servicebook.Book;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 19.07.2016.
 */
public class HibernetLoadDeleteUpdateBook {

    private final static Logger log= LoggerFactory.getLogger(HibernetAnnotationsBook.class);

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Serializable id=null;

    public static void init(){
        Configuration configuration=new Configuration();
        configuration.addAnnotatedClass(edu.javacourse.hibernate.servicebook.Author.class);
        configuration.addAnnotatedClass(edu.javacourse.hibernate.servicebook.Book.class);

        configuration.configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory=configuration.buildSessionFactory(serviceRegistry);
    }
    public static void destroy(){
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    public static void createAuthor(){
        log.info("=============createAuthor=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Author author=new Author("Pushkin");
        session.save(author);
        log.info("Save author {}",author);
        session.getTransaction().commit();
        session.close();
        id=author.getAuthorId();
    }


    public static void getAuthor(){
        log.info("=============GetAuthor=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Author author=session.get(Author.class,id);
        log.info("Get author {}",author);

        session.getTransaction().commit();
        session.close();
    }

    public static void loadAuthor(){
        log.info("=============LoadAuthor=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Author author=session.load(Author.class,id);
        log.info("Load author {}",author);
        session.getTransaction().commit();
        session.close();

    }
    public static void getVsLoad(){
        log.info("=============getVsLoad=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Author author=session.load(Author.class,id);
       Book book1= new edu.javacourse.hibernate.servicebook.Book();
        book1.setName("Piramida");
        book1.setAuthor(author);
        session.save(book1);
        log.info("Save new Book1 {} ",book1);

       Book book2=new Book();
        book2.setName("Italya");
        book2.setAuthor(author);
        id=session.save(book2);
        log.info("Load author {}",author);
        log.info("Save new Book {} ",book2);
        session.getTransaction().commit();
        session.close();

    }
    public static void updateBook(){
        log.info("=============updateBook=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

       Book book=session.load(Book.class,id);
        book.setName("UPDATE BOOK");
        session.saveOrUpdate(book);

        log.info("Update Book {}",book);
        session.getTransaction().commit();
        session.close();
    }
    public static void deleteBook(){
        log.info("=============deleteBook=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

       Book book=session.load(Book.class,id);
        session.delete(book);
        log.info("Delete Book {}",book);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        init();

        createAuthor();
        getAuthor();
        loadAuthor();
        getVsLoad();
        updateBook();
        deleteBook();

        destroy();
    }
}


