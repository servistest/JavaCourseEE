package edu.javacourse.hibernate.servicebook;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Admin on 19.07.2016.
 */
public class HibernetAnnotationsBook {
    private final static Logger log= LoggerFactory.getLogger(HibernetAnnotationsBook.class);

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

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

    public static void criteriaBook(){
        Session s=sessionFactory.getCurrentSession();
        s.beginTransaction();

        List<Book> books = s.createQuery("from Book ").list();
        for(Book book2:books){
            log.info(" All Books {} :",book2);
        }

        Criteria criteria=s.createCriteria(Book.class)
                .add(Restrictions.or(
                        Restrictions.like("name","Hi%").ignoreCase(),
                        Restrictions.or(
                                Restrictions.like("name","Gr%").ignoreCase(),
                                Restrictions.like("name","S%").ignoreCase()
                        )
                )).addOrder(Order.asc("name"));

        List<Book> listBook=criteria.list();
        for(Book book:listBook){
            log.info("Books with Hi% {} :",book);
        }
         s.getTransaction().commit();
        s.close();
    }


    public static void criteriaBook2(){
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria=session.createCriteria(Book.class).add(Restrictions.eq("bookId",new Long(3)));
        criteria.add(Restrictions.like("name","M%").ignoreCase());
         List<Book> listBook=criteria.list();
        for (Book book:listBook) {
            log.info("Book id 3 + M% {}",book);
        }

        session.close();
    }
    public static void main(String[] args) {
        init();
        criteriaBook();
        criteriaBook2();
        destroy();
    }
}
