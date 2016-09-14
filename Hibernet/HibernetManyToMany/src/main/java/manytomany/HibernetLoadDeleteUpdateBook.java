package manytomany;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by Admin on 19.07.2016.
 */
public class HibernetLoadDeleteUpdateBook {

    private final static Logger log= LoggerFactory.getLogger(HibernetLoadDeleteUpdateBook.class);

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Serializable id=null;

    public static void init(){
        Configuration configuration=new Configuration();
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);

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
       Book book1= new Book();
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

    public static void createAuthorandBookList(){
        log.info("=============createAuthorAndBookList=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Author author=new Author("Rokosovskij12");
        session.save(author);
        //log.info("Save  Author ={}",author);

        Book book=new Book();
        book.setName("Russia12");
        book.setAuthor(author);
        session.save(book);

        Book book2=new Book();
        book2.setName("Russia101");
        book2.setAuthor(author);
        session.save(book2);
//
//        Book book3=new Book();
//        book3.setName("Russia102");
//        book3.setAuthor(author);
//        session.save(book3);

        session.delete(book);
        session.delete(book2);

        //id=book.getId();

        log.info("Save  Book = {}",book);

//        List<Book> bookList=session.createQuery("from Book").list();
//
//        for (Book b: bookList){
//            log.info("bookList {}",b);
//        }
//        List<Author> authorList=session.createQuery("from Author").list();
//
//        for (Author a: authorList){
//            log.info("authorList {}",a);
//        }

        session.getTransaction().commit();
        session.close();
    }
    public static void deleteAuthor(){
        log.info("============deleteBook=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book=session.get(Book.class,id);
        session.delete(book);

        session.getTransaction().commit();
        session.close();


    }
    public static void main(String[] args) {
        init();
        createAuthorandBookList();
      //  deleteAuthor();

//        createAuthor();
//        getAuthor();
//        loadAuthor();
//        getVsLoad();
//        updateBook();
//        deleteBook();
        destroy();
    }
}


