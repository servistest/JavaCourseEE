package edu.manytomany;



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
    private static Serializable idBook=null;

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



    public static void createListAuhor (){
        log.info("============createListAuhor=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Author pushkin=new Author("Pushkin11");
        id=session.save(pushkin);

        Author lermontov=new Author("Lermontov11");
        session.save(lermontov);

        Book book1=new Book("Ruslan and Ludmila11");
        book1.addAuthor(pushkin);
        book1.addAuthor(lermontov);

        idBook=session.save(book1);

//        book1.removeAuthor(pushkin);
//        session.delete(pushkin);

        log.info("Book1 = {} {}",book1.toString(),book1.getAuthorList());
        session.getTransaction().commit();
        session.close();


    };

    public static void deleteAuthor(){
        log.info("============deleteAuthor=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Book book =session.load(Book.class,idBook);
        Author pushkin=session.load(Author.class,id);
        //session.remove(book);
        book.removeAuthor(pushkin);
        //or
        //  session.delete(pushkin);
        session.getTransaction().commit();
        session.close();
    }

    public static void createListBook(){
        log.info("============createListBook=========");
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Book krepost=new Book("Brestskaya Krepost8");
      //  session.save(krepost);
        Book vojna=new Book("Vojna8");
       // session.save(vojna);
        Author smirnov=new Author("Smirnov8");
        smirnov.addBook(krepost);
        smirnov.addBook(vojna);

        session.save(smirnov);

        log.info("Smirnov  listBook = {}",smirnov.getBookList());
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        init();
        createListAuhor();
        deleteAuthor();
        createListBook();
        destroy();
    }
}


